package com.thegamersplace.infrastructure.security;

import com.thegamersplace.domain.entity.User;
import com.thegamersplace.infrastructure.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
  private final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByLogin(login);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User " + login + " not found");
    }
    if(user.get().getBlocked()){
      throw new UsernameNotFoundException("User " + login + " not found");
    }

    logger.info("Loaded user {} with authority {}", login, user.get().getRole().name());
    GrantedAuthority authority = new SimpleGrantedAuthority(user.get().getRole().name());
    return new org.springframework.security.core.userdetails.User(login, user.get().getPassword(),
        Collections.singleton(authority));
  }
}
