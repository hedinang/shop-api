package com.example.shop_api.repository.mongo;

import com.example.shop_api.model.entity.SimPackage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DaySimRepository extends MongoRepository<SimPackage, String> {

}
