package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

/**
 * Videogame types
 */
@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class RequirementGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String minimum;
    private String recommended;

    public RequirementGraphqlType() {
    }

    public RequirementGraphqlType(String minimum, String recommended) {
        this.minimum = minimum;
        this.recommended = recommended;
    }

    public String getMinimum() {
        return minimum;
    }
    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getRecommended() {
        return recommended;
    }
    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final RequirementGraphqlType that = (RequirementGraphqlType) obj;
        return Objects.equals(minimum, that.minimum)
            && Objects.equals(recommended, that.recommended);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minimum, recommended);
    }


    public static RequirementGraphqlType.Builder builder() {
        return new RequirementGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private String minimum;
        private String recommended;

        public Builder() {
        }

        public Builder setMinimum(String minimum) {
            this.minimum = minimum;
            return this;
        }

        public Builder setRecommended(String recommended) {
            this.recommended = recommended;
            return this;
        }


        public RequirementGraphqlType build() {
            return new RequirementGraphqlType(minimum, recommended);
        }

    }
}
