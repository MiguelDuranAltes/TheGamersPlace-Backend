package com.thegamersplace.infrastructure.security;

import com.thegamersplace.infrastructure.config.Properties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JWTFilter extends GenericFilterBean {
  private final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

  private final TokenProvider tokenProvider;

  public JWTFilter(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    String jwt = resolveToken(httpServletRequest);
    try {
      if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {
        Authentication authentication = this.tokenProvider.getAuthentication(jwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
      filterChain.doFilter(servletRequest, servletResponse);
    } catch (ExpiredJwtException eje) {
      logger.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
      ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", resolveClientHost());
    } catch (SignatureException eje) {
      logger.info("Signature Exception: {}", eje.getMessage());
      ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", resolveClientHost());
    }
  }

  private String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  private String resolveClientHost() {
    if (!StringUtils.hasText(Properties.CLIENT_HOST)) {
      return "http://localhost:1234";
    }
    return Properties.CLIENT_HOST.split(",")[0].trim();
  }
}
