package com.example.demo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Lunar;

@Repository
public interface LunarRepository extends MongoRepository<Lunar, ObjectId> {
	Lunar findByDate(int date);
}
