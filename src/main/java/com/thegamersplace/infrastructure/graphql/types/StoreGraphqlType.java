package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-26T20:49:20+0200"
)
public class StoreGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String name;
    @jakarta.validation.constraints.NotNull
    private String slug;

    public StoreGraphqlType() {
    }

    public StoreGraphqlType(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final StoreGraphqlType that = (StoreGraphqlType) obj;
        return Objects.equals(name, that.name)
            && Objects.equals(slug, that.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, slug);
    }


    public static StoreGraphqlType.Builder builder() {
        return new StoreGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-26T20:49:20+0200"
    )
    public static class Builder {

        private String name;
        private String slug;

        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSlug(String slug) {
            this.slug = slug;
            return this;
        }


        public StoreGraphqlType build() {
            return new StoreGraphqlType(name, slug);
        }

    }
}
