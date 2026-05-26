package com.thegamersplace.infrastructure.graphql.types;

import java.util.Objects;

@javax.annotation.processing.Generated(
    value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
    date = "2026-05-24T14:07:01+0200"
)
public class VideogameListGraphqlType implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @jakarta.validation.constraints.NotNull
    private String name;
    @jakarta.validation.constraints.NotNull
    private java.util.List<VideogameCardGraphqlType> videogames;
    private boolean publicView;

    public VideogameListGraphqlType() {
    }

    public VideogameListGraphqlType(String name, java.util.List<VideogameCardGraphqlType> videogames, boolean publicView) {
        this.name = name;
        this.videogames = videogames;
        this.publicView = publicView;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public java.util.List<VideogameCardGraphqlType> getVideogames() {
        return videogames;
    }
    public void setVideogames(java.util.List<VideogameCardGraphqlType> videogames) {
        this.videogames = videogames;
    }

    public boolean getPublicView() {
        return publicView;
    }
    public void setPublicView(boolean publicView) {
        this.publicView = publicView;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final VideogameListGraphqlType that = (VideogameListGraphqlType) obj;
        return Objects.equals(name, that.name)
            && Objects.equals(videogames, that.videogames)
            && Objects.equals(publicView, that.publicView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, videogames, publicView);
    }


    public static VideogameListGraphqlType.Builder builder() {
        return new VideogameListGraphqlType.Builder();
    }

    @javax.annotation.processing.Generated(
        value = "com.kobylynskyi.graphql.codegen.GraphQLCodegen",
        date = "2026-05-24T14:07:01+0200"
    )
    public static class Builder {

        private String name;
        private java.util.List<VideogameCardGraphqlType> videogames;
        private boolean publicView;

        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setVideogames(java.util.List<VideogameCardGraphqlType> videogames) {
            this.videogames = videogames;
            return this;
        }

        public Builder setPublicView(boolean publicView) {
            this.publicView = publicView;
            return this;
        }


        public VideogameListGraphqlType build() {
            return new VideogameListGraphqlType(name, videogames, publicView);
        }

    }
}
