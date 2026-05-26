package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class PlatformGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String name;
    @jakarta.validation.constraints.NotNull
    private String slug;
    private RequirementGraphqlType requirements;

    public PlatformGraphqlType() {
    }

    public PlatformGraphqlType(String name, String slug, RequirementGraphqlType requirements) {
        this.name = name;
        this.slug = slug;
        this.requirements = requirements;
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

    public RequirementGraphqlType getRequirements() {
        return requirements;
    }
    public void setRequirements(RequirementGraphqlType requirements) {
        this.requirements = requirements;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final PlatformGraphqlType that = (PlatformGraphqlType) obj;
        return Objects.equals(name, that.name)
            && Objects.equals(slug, that.slug)
            && Objects.equals(requirements, that.requirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, slug, requirements);
    }


    public static PlatformGraphqlType.Builder builder() {
        return new PlatformGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private String name;
        private String slug;
        private RequirementGraphqlType requirements;

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

        public Builder setRequirements(RequirementGraphqlType requirements) {
            this.requirements = requirements;
            return this;
        }


        public PlatformGraphqlType build() {
            return new PlatformGraphqlType(name, slug, requirements);
        }

    }
}
