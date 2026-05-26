package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-26T20:49:20+0200"
)
public class UserInputGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String login;
    @jakarta.validation.constraints.NotNull
    private String password;

    public UserInputGraphqlType() {
    }

    public UserInputGraphqlType(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserInputGraphqlType that = (UserInputGraphqlType) obj;
        return Objects.equals(login, that.login)
            && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }


    public static UserInputGraphqlType.Builder builder() {
        return new UserInputGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-26T20:49:20+0200"
    )
    public static class Builder {

        private String login;
        private String password;

        public Builder() {
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }


        public UserInputGraphqlType build() {
            return new UserInputGraphqlType(login, password);
        }

    }
}
