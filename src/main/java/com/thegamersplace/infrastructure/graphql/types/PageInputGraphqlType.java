package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-26T20:49:20+0200"
)
public class PageInputGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int page;
    private int size;

    public PageInputGraphqlType() {
    }

    public PageInputGraphqlType(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final PageInputGraphqlType that = (PageInputGraphqlType) obj;
        return Objects.equals(page, that.page)
            && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size);
    }


    public static PageInputGraphqlType.Builder builder() {
        return new PageInputGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-26T20:49:20+0200"
    )
    public static class Builder {

        private int page;
        private int size;

        public Builder() {
        }

        public Builder setPage(int page) {
            this.page = page;
            return this;
        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }


        public PageInputGraphqlType build() {
            return new PageInputGraphqlType(page, size);
        }

    }
}
