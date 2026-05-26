package com.thegamersplace.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "properties")
public class Properties {
  // Necesario para usarlo en JWTFilter
    public static String CLIENT_HOST;

    private String clientHost;
    @Setter
    private String jwtSecretKey;
    @Setter
    private Long jwtValidity;
    @Setter
    private String rutaImagenes;


    public void setClientHost(String clientHost) {
      Properties.CLIENT_HOST = clientHost;
      this.clientHost = clientHost;
    }

}
