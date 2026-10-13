package com.example.shop_api.repository.mongo;

import com.example.shop_api.model.entity.Sim;
import com.example.shop_api.repository.mongo.custom.CustomSimRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimRepository extends MongoRepository<Sim, String>, CustomSimRepository {

}
