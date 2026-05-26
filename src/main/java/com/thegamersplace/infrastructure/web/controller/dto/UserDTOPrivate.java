package com.thegamersplace.infrastructure.web.controller.dto;



import com.thegamersplace.domain.entity.User;
import com.thegamersplace.domain.entity.UserRole;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTOPrivate {
  private String id;

  @NotEmpty
  private String login;
  private UserRole authority;
  @NotEmpty

  private String password;

  public UserDTOPrivate() {
  }

  public UserDTOPrivate(User user) {
    this.id = user.getId().toString();
    this.login = user.getLogin();
    this.authority = user.getRole();
  }

}
