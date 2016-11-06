package com.sind.projectx.rest.validator;

import com.sind.projectx.domain.food.menu.Menu;
import com.sind.projectx.domain.food.menu.MenuSection;
import com.sind.projectx.rest.exception.IdNotFoundException;
import com.sind.projectx.rest.exception.InvalidFieldException;
import com.sind.projectx.service.food.menu.MenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Dmytro Bekuzarov
 */
@Component
public class MenuValidator {

    @Autowired
    private MenuService menuService;
    @Autowired
    private PlaceValidator placeValidator;
    @Autowired
    private MenuItemValidator menuItemValidator;

    public void validateForAdd(Menu menu, String userId) {
        validatePlaceId(menu.getPlaceId(), userId);
        validateMenuItems(menu);
    }

    public void validateForUpdate(Menu menu, String userId) {
        validateMenuId(menu.getId());
        validatePlaceId(menu.getPlaceId(), userId);
        validateMenuItems(menu);
    }

    private void validateMenuItems(Menu menu) {
        Set<String> menuItems = new HashSet<>();
        getMenuItems(menu.getSections(), menuItems);
        menuItemValidator.validateMenuItems(menuItems);
    }

    private void getMenuItems(List<MenuSection> sections, Set<String> menuItems) {
        for (MenuSection section : sections) {
            List<String> items = section.getItems();
            if (CollectionUtils.isNotEmpty(items)) {
                menuItems.addAll(items);
            }
            List<MenuSection> innerSections = section.getSections();
            if (CollectionUtils.isNotEmpty(items)) {
                getMenuItems(innerSections, menuItems);
            }
        }
    }


    private void validateMenuId(String menuId) {
        boolean menuExists = menuService.exists(menuId);
        if (!menuExists) {
            throw new InvalidFieldException("id", menuId);
        }
    }

    private void validatePlaceId(String placeId, String userId) {
        placeValidator.checkPlaceAccess(placeId, userId);
    }

    public void validateForDelete(String menuId, String userId) {
        Menu menu = menuService.findById(menuId);
        if (menu == null) {
            throw new IdNotFoundException();
        }
        validatePlaceId(menu.getPlaceId(), userId);
    }
}
