package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-26T20:49:20+0200"
)
public class UserPublicGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String login;
    private boolean admin;
    private boolean blocked;

    public UserPublicGraphqlType() {
    }

    public UserPublicGraphqlType(String login, boolean admin, boolean blocked) {
        this.login = login;
        this.admin = admin;
        this.blocked = blocked;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public boolean getAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserPublicGraphqlType that = (UserPublicGraphqlType) obj;
        return Objects.equals(login, that.login)
            && Objects.equals(admin, that.admin)
            && Objects.equals(blocked, that.blocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, admin, blocked);
    }


    public static UserPublicGraphqlType.Builder builder() {
        return new UserPublicGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-26T20:49:20+0200"
    )
    public static class Builder {

        private String login;
        private boolean admin;
        private boolean blocked;

        public Builder() {
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setAdmin(boolean admin) {
            this.admin = admin;
            return this;
        }

        public Builder setBlocked(boolean blocked) {
            this.blocked = blocked;
            return this;
        }


        public UserPublicGraphqlType build() {
            return new UserPublicGraphqlType(login, admin, blocked);
        }

    }
}
