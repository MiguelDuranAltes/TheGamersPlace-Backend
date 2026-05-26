package com.thegamersplace.infrastructure.repository;

import com.thegamersplace.domain.entity.Videogame;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;


@Repository
public class CustomVideogameRepositoryImpl implements CustomVideogameRepository{
    private final MongoTemplate mongoTemplate;

    public CustomVideogameRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Videogame> findByFilter(com.thegamersplace.infrastructure.graphql.types.VideogameFilterInputGraphqlType filter, Pageable pageable) {
        Query query = new Query();

        if (filter.getName() != null) {
            query.addCriteria(Criteria.where("name").regex(filter.getName(), "i"));
        }

        if (filter.getPlatforms() != null && !filter.getPlatforms().isEmpty()) {
            query.addCriteria(Criteria.where("platforms.name").all(filter.getPlatforms()));
        }

        if (filter.getGenres() != null && !filter.getGenres().isEmpty()) {
            query.addCriteria(Criteria.where("genres").all(filter.getGenres()));
        }

        if (filter.getTags() != null && !filter.getTags().isEmpty()) {
            query.addCriteria(Criteria.where("tags").all(filter.getTags()));
        }

        if (filter.getRating() != null) {
            query.addCriteria(Criteria.where("rating").gte(filter.getRating()));
        }

        if (filter.getReleaseYear() != null) {
            query.addCriteria(Criteria.where("released").regex("^" + filter.getReleaseYear(), "i"));
        }

        if (filter.getOrderByRatingDesc() != null) {
            Sort.Direction direction = filter.getOrderByRatingDesc() ? Sort.Direction.DESC : Sort.Direction.ASC;
            query.with(Sort.by(direction, "rating"));
        }

        if(filter.getOrderByReleaseDateDesc() != null) {
            Sort.Direction direction = filter.getOrderByReleaseDateDesc() ? Sort.Direction.DESC : Sort.Direction.ASC;
            query.with(Sort.by(direction, "released"));
        }


        query.with(pageable);

        List<Videogame> videogames = mongoTemplate.find(query, Videogame.class);
        long total = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Videogame.class);

        return new PageImpl<>(videogames, pageable, total);
    }


    @Override
    public List<String> getAllGenres() {
        return mongoTemplate.query(Videogame.class)
                .distinct("genres")
                .as(String.class)
                .all();
    }

    @Override
    public List<String> getAllPlatforms() {
        return mongoTemplate.query(Videogame.class)
                .distinct("platforms.name") // Filtramos solo el nombre de la plataforma
                .as(String.class)
                .all();
    }

    @Override
    public List<String> getAllTags() {
        return mongoTemplate.query(Videogame.class)
                .distinct("tags")
                .as(String.class)
                .all();
    }

    public List<Videogame> getRandomVideogames(int minRatings, int count) {
    // Paso 1: Crear una operación para agregar un campo con la suma total de valoraciones
        AggregationOperation addTotalRatings = context ->
                new Document("$addFields",
                        new Document("totalRatings",
                                new Document("$sum", "$ratings.count")
                        )
                );

        // Paso 2: Crear una operación para filtrar documentos con al menos minRatings valoraciones
        MatchOperation matchMinRatings = Aggregation.match(
                Criteria.where("totalRatings").gte(minRatings)
        );

        // Paso 3: Crear una operación para tomar una muestra aleatoria
        SampleOperation sampleRandomDocs = Aggregation.sample(count);

        // Combinar todas las operaciones en una agregación
        Aggregation aggregation = Aggregation.newAggregation(
                addTotalRatings,
                matchMinRatings,
                sampleRandomDocs
        );

        // Ejecutar la agregación y devolver los resultados
        return mongoTemplate.aggregate(aggregation, "videogame", Videogame.class)
                .getMappedResults();    }
}
