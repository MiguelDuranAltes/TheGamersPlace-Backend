package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-26T20:49:20+0200"
)
public class ChoiceGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int like;
    private int dislike;

    public ChoiceGraphqlType() {
    }

    public ChoiceGraphqlType(int like, int dislike) {
        this.like = like;
        this.dislike = dislike;
    }

    public int getLike() {
        return like;
    }
    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }
    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ChoiceGraphqlType that = (ChoiceGraphqlType) obj;
        return Objects.equals(like, that.like)
            && Objects.equals(dislike, that.dislike);
    }

    @Override
    public int hashCode() {
        return Objects.hash(like, dislike);
    }


    public static ChoiceGraphqlType.Builder builder() {
        return new ChoiceGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-26T20:49:20+0200"
    )
    public static class Builder {

        private int like;
        private int dislike;

        public Builder() {
        }

        public Builder setLike(int like) {
            this.like = like;
            return this;
        }

        public Builder setDislike(int dislike) {
            this.dislike = dislike;
            return this;
        }


        public ChoiceGraphqlType build() {
            return new ChoiceGraphqlType(like, dislike);
        }

    }
}
