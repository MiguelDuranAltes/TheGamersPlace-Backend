package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class UserListGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private java.util.List<UserPublicGraphqlType> users;

    public UserListGraphqlType() {
    }

    public UserListGraphqlType(java.util.List<UserPublicGraphqlType> users) {
        this.users = users;
    }

    public java.util.List<UserPublicGraphqlType> getUsers() {
        return users;
    }
    public void setUsers(java.util.List<UserPublicGraphqlType> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserListGraphqlType that = (UserListGraphqlType) obj;
        return Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }


    public static UserListGraphqlType.Builder builder() {
        return new UserListGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private java.util.List<UserPublicGraphqlType> users;

        public Builder() {
        }

        public Builder setUsers(java.util.List<UserPublicGraphqlType> users) {
            this.users = users;
            return this;
        }


        public UserListGraphqlType build() {
            return new UserListGraphqlType(users);
        }

    }
}
