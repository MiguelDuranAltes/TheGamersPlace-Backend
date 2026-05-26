package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-26T20:49:20+0200"
)
public class RatingGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String title;
    private int count;
    private double percent;

    public RatingGraphqlType() {
    }

    public RatingGraphqlType(String title, int count, double percent) {
        this.title = title;
        this.count = count;
        this.percent = percent;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public double getPercent() {
        return percent;
    }
    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final RatingGraphqlType that = (RatingGraphqlType) obj;
        return Objects.equals(title, that.title)
            && Objects.equals(count, that.count)
            && Objects.equals(percent, that.percent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, count, percent);
    }


    public static RatingGraphqlType.Builder builder() {
        return new RatingGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-26T20:49:20+0200"
    )
    public static class Builder {

        private String title;
        private int count;
        private double percent;

        public Builder() {
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCount(int count) {
            this.count = count;
            return this;
        }

        public Builder setPercent(double percent) {
            this.percent = percent;
            return this;
        }


        public RatingGraphqlType build() {
            return new RatingGraphqlType(title, count, percent);
        }

    }
}
