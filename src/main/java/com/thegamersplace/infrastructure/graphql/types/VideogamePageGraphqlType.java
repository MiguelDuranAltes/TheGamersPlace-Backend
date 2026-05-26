package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class VideogamePageGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private java.util.List<VideogameCardGraphqlType> content;
    @jakarta.validation.constraints.NotNull
    private PageInfoGraphqlType pageInfo;

    public VideogamePageGraphqlType() {
    }

    public VideogamePageGraphqlType(java.util.List<VideogameCardGraphqlType> content, PageInfoGraphqlType pageInfo) {
        this.content = content;
        this.pageInfo = pageInfo;
    }

    public java.util.List<VideogameCardGraphqlType> getContent() {
        return content;
    }
    public void setContent(java.util.List<VideogameCardGraphqlType> content) {
        this.content = content;
    }

    public PageInfoGraphqlType getPageInfo() {
        return pageInfo;
    }
    public void setPageInfo(PageInfoGraphqlType pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final VideogamePageGraphqlType that = (VideogamePageGraphqlType) obj;
        return Objects.equals(content, that.content)
            && Objects.equals(pageInfo, that.pageInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, pageInfo);
    }


    public static VideogamePageGraphqlType.Builder builder() {
        return new VideogamePageGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private java.util.List<VideogameCardGraphqlType> content;
        private PageInfoGraphqlType pageInfo;

        public Builder() {
        }

        public Builder setContent(java.util.List<VideogameCardGraphqlType> content) {
            this.content = content;
            return this;
        }

        public Builder setPageInfo(PageInfoGraphqlType pageInfo) {
            this.pageInfo = pageInfo;
            return this;
        }


        public VideogamePageGraphqlType build() {
            return new VideogamePageGraphqlType(content, pageInfo);
        }

    }
}
