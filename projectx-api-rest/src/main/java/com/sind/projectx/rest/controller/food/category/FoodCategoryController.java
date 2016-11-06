package com.sind.projectx.rest.controller.food.category;

import com.sind.projectx.domain.food.category.FoodCategory;
import com.sind.projectx.domain.user.UserRole;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import com.sind.projectx.service.food.category.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dmytro Bekuzarov
 */
@RestController
@RequestMapping("/food/categories")
@AccessRestrictions(roles = {UserRole.MANAGER, UserRole.ADMIN, UserRole.USER})
@Validated
public class FoodCategoryController {

    @Autowired
    private FoodCategoryService foodCategoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<FoodCategory> findAll() {
        return foodCategoryService.findAll();
    }

}
