package com.sind.projectx.rest.validator;

import com.sind.projectx.rest.exception.InvalidFieldException;
import com.sind.projectx.service.food.category.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmytro Bekuzarov
 */
@Component
public class FoodCategoryValidator {

    @Autowired
    private FoodCategoryService foodCategoryService;

    public void validateCategories(List<String> ids) {
        List<String> categories = foodCategoryService.findExistingIds(ids);
        List<String> invalidCategories = new ArrayList<>(ids);
        invalidCategories.removeAll(categories);
        if (!invalidCategories.isEmpty()) {
            throw new InvalidFieldException("categories", invalidCategories.toString());
        }
    }


}
