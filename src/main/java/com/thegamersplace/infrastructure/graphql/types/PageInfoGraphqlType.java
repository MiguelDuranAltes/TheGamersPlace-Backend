package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class PageInfoGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int totalElements;
    private int totalPages;
    private int currentPage;

    public PageInfoGraphqlType() {
    }

    public PageInfoGraphqlType(int totalElements, int totalPages, int currentPage) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    public int getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final PageInfoGraphqlType that = (PageInfoGraphqlType) obj;
        return Objects.equals(totalElements, that.totalElements)
            && Objects.equals(totalPages, that.totalPages)
            && Objects.equals(currentPage, that.currentPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalElements, totalPages, currentPage);
    }


    public static PageInfoGraphqlType.Builder builder() {
        return new PageInfoGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private int totalElements;
        private int totalPages;
        private int currentPage;

        public Builder() {
        }

        public Builder setTotalElements(int totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public Builder setTotalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }


        public PageInfoGraphqlType build() {
            return new PageInfoGraphqlType(totalElements, totalPages, currentPage);
        }

    }
}
