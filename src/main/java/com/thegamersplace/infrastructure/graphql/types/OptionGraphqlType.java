package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

/**
 * Recommendations
 */
@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class OptionGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private VideogameCardFeaturesGraphqlType Option1;
    private VideogameCardFeaturesGraphqlType Option2;

    public OptionGraphqlType() {
    }

    public OptionGraphqlType(VideogameCardFeaturesGraphqlType Option1, VideogameCardFeaturesGraphqlType Option2) {
        this.Option1 = Option1;
        this.Option2 = Option2;
    }

    public VideogameCardFeaturesGraphqlType getOption1() {
        return Option1;
    }
    public void setOption1(VideogameCardFeaturesGraphqlType Option1) {
        this.Option1 = Option1;
    }

    public VideogameCardFeaturesGraphqlType getOption2() {
        return Option2;
    }
    public void setOption2(VideogameCardFeaturesGraphqlType Option2) {
        this.Option2 = Option2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final OptionGraphqlType that = (OptionGraphqlType) obj;
        return Objects.equals(Option1, that.Option1)
            && Objects.equals(Option2, that.Option2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Option1, Option2);
    }


    public static OptionGraphqlType.Builder builder() {
        return new OptionGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private VideogameCardFeaturesGraphqlType Option1;
        private VideogameCardFeaturesGraphqlType Option2;

        public Builder() {
        }

        public Builder setOption1(VideogameCardFeaturesGraphqlType Option1) {
            this.Option1 = Option1;
            return this;
        }

        public Builder setOption2(VideogameCardFeaturesGraphqlType Option2) {
            this.Option2 = Option2;
            return this;
        }


        public OptionGraphqlType build() {
            return new OptionGraphqlType(Option1, Option2);
        }

    }
}
