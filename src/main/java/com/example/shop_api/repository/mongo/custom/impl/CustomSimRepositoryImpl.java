package com.example.shop_api.repository.mongo.custom.impl;

import com.example.shop_api.model.entity.Sim;
import com.example.shop_api.model.request.PageRequest;
import com.example.shop_api.model.request.search.SimSearch;
import com.example.shop_api.repository.mongo.custom.CustomSimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomSimRepositoryImpl implements CustomSimRepository {
    private final MongoTemplate mongoTemplate;

    public List<Sim> findBySearch(PageRequest<SimSearch> request) {
        Query query = new Query();
        SimSearch search = request.getSearch();

        if (search != null) {
            if (search.getTime() != null && !search.getTime().isEmpty()) {
                query.addCriteria(Criteria.where("time").is(search.getTime()));
            }

            if (search.getPack() != null && !search.getPack().isEmpty()) {
                query.addCriteria(Criteria.where("pack").is(search.getPack()));
            }

            if (search.getUnit() != null && !search.getUnit().isEmpty()) {
                query.addCriteria(Criteria.where("unit").is(search.getUnit()));
            }

            if (search.getType() != null && !search.getType().isEmpty()) {
                query.addCriteria(Criteria.where("type").is(search.getType()));
            }

            if (search.getBrand() != null && !search.getBrand().isEmpty()) {
                query.addCriteria(Criteria.where("brand").is(search.getBrand()));
            }

            if (search.getNation() != null && !search.getNation().isEmpty()) {
                query.addCriteria(Criteria.where("nation").is(search.getNation()));
            }
        }

        query.with(Sort.by(Sort.Direction.DESC, "created_at"));
        query.limit(request.getLimit());
        query.skip((long) (request.getPage() - 1) * request.getLimit());

        return mongoTemplate.find(query, Sim.class);
    }

    public Sim findBySimId(String simId){
        return null;
    }
}
