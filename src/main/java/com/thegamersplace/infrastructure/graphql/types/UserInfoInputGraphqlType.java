package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class UserInfoInputGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String login;
    private String name;
    private String imageName;
    private String city;
    private boolean blocked;
    private boolean admin;

    public UserInfoInputGraphqlType() {
    }

    public UserInfoInputGraphqlType(String login, String name, String imageName, String city, boolean blocked, boolean admin) {
        this.login = login;
        this.name = name;
        this.imageName = imageName;
        this.city = city;
        this.blocked = blocked;
        this.admin = admin;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public boolean getBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean getAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserInfoInputGraphqlType that = (UserInfoInputGraphqlType) obj;
        return Objects.equals(login, that.login)
            && Objects.equals(name, that.name)
            && Objects.equals(imageName, that.imageName)
            && Objects.equals(city, that.city)
            && Objects.equals(blocked, that.blocked)
            && Objects.equals(admin, that.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, imageName, city, blocked, admin);
    }


    public static UserInfoInputGraphqlType.Builder builder() {
        return new UserInfoInputGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private String login;
        private String name;
        private String imageName;
        private String city;
        private boolean blocked;
        private boolean admin;

        public Builder() {
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setImageName(String imageName) {
            this.imageName = imageName;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setBlocked(boolean blocked) {
            this.blocked = blocked;
            return this;
        }

        public Builder setAdmin(boolean admin) {
            this.admin = admin;
            return this;
        }


        public UserInfoInputGraphqlType build() {
            return new UserInfoInputGraphqlType(login, name, imageName, city, blocked, admin);
        }

    }
}
