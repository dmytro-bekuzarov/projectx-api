package com.sind.projectx.rest.validator;

import com.sind.projectx.domain.food.menu.MenuItem;
import com.sind.projectx.rest.exception.FieldMissingException;
import com.sind.projectx.rest.exception.IdNotFoundException;
import com.sind.projectx.rest.exception.InvalidFieldException;
import com.sind.projectx.service.food.menu.MenuItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Dmytro Bekuzarov
 */
@Component
public class MenuItemValidator {

    @Autowired
    private MenuItemService menuItemService;
    @Autowired
    private PlaceNetworkValidator placeNetworkValidator;
    @Autowired
    private FoodCategoryValidator foodCategoryValidator;

    public void validateForAdd(MenuItem menuItem, String placeNetworkId, String userId) {
        if (StringUtils.isEmpty(menuItem.getId())) {
            throw new FieldMissingException("id");
        }
        placeNetworkValidator.checkAccess(placeNetworkId, userId);
        foodCategoryValidator.validateCategories(menuItem.getCategories());
        menuItem.setPlaceNetworkId(placeNetworkId);
    }

    public void validateForUpdate(MenuItem menuItem, String userId) {
        if (StringUtils.isEmpty(menuItem.getId())) {
            throw new FieldMissingException("id");
        }
        MenuItem existingMenuItem = menuItemService.findById(menuItem.getId());
        if (existingMenuItem == null) {
            throw new IdNotFoundException();
        }
        placeNetworkValidator.checkAccess(existingMenuItem.getPlaceNetworkId(), userId);
        foodCategoryValidator.validateCategories(menuItem.getCategories());
        menuItem.setPlaceNetworkId(existingMenuItem.getPlaceNetworkId());
    }

    public void validateForDelete(String menuItemId, String userId) {
        MenuItem existingMenuItem = menuItemService.findById(menuItemId);
        if (existingMenuItem == null) {
            throw new IdNotFoundException();
        }
        placeNetworkValidator.checkAccess(existingMenuItem.getPlaceNetworkId(), userId);
    }

    public void validateMenuItems(Collection<String> menuItems) {
        List<String> items = menuItemService.findExistingIds(menuItems);
        List<String> invalidMenuItems = new ArrayList<>(menuItems);
        invalidMenuItems.removeAll(items);
        if (!invalidMenuItems.isEmpty()) {
            throw new InvalidFieldException("items", invalidMenuItems.toString());
        }
    }
}
