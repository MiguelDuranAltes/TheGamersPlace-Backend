package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class ImageGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String alias;
    @jakarta.validation.constraints.NotNull
    private String url;

    public ImageGraphqlType() {
    }

    public ImageGraphqlType(String alias, String url) {
        this.alias = alias;
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ImageGraphqlType that = (ImageGraphqlType) obj;
        return Objects.equals(alias, that.alias)
            && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, url);
    }


    public static ImageGraphqlType.Builder builder() {
        return new ImageGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private String alias;
        private String url;

        public Builder() {
        }

        public Builder setAlias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }


        public ImageGraphqlType build() {
            return new ImageGraphqlType(alias, url);
        }

    }
}
