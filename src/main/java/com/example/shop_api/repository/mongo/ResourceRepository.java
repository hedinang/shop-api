package com.example.shop_api.repository.mongo;

import com.example.shop_api.model.entity.Resource;
import com.example.shop_api.repository.mongo.custom.CustomResourceRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResourceRepository extends MongoRepository<Resource, String>, CustomResourceRepository {
    void deleteByRequestUuidAndContentType(String requestUuid, String contentType);

    long countByRequestUuid(String requestUuid);

    List<Resource> findAllByRequestUuidOrderByChunkIndexAsc(String requestUuid);

    Resource findFirstByResourceId(String resourceId);
}
