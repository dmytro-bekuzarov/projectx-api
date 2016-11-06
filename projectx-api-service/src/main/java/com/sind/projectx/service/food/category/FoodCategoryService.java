package com.sind.projectx.service.food.category;

import com.sind.projectx.domain.food.category.FoodCategory;
import com.sind.projectx.repository.food.category.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmytro Bekuzarov
 */
@Service
public class FoodCategoryService {

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    public List<FoodCategory> findAll() {
        return foodCategoryRepository.findAll();
    }

    public List<FoodCategory> findByIds(List<String> ids) {
        List<FoodCategory> list = new ArrayList<>();
        for (FoodCategory foodCategory : foodCategoryRepository.findAll(ids)) {
            list.add(foodCategory);
        }
        return list;
    }

    public List<String> findExistingIds(List<String> ids) {
        List<FoodCategory> categories = findByIds(ids);
        return categories.stream().map(FoodCategory::getId).collect(toList());
    }

}
