package com.sind.projectx.repository.food.category;

import com.sind.projectx.domain.food.category.FoodCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro Bekuzarov
 */
@Repository
public interface FoodCategoryRepository extends MongoRepository<FoodCategory, String> {



}
