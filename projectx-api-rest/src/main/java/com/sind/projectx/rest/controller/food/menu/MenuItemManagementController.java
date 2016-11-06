package com.sind.projectx.rest.controller.food.menu;

import com.sind.projectx.domain.food.menu.MenuItem;
import com.sind.projectx.domain.user.UserRole;
import com.sind.projectx.repository.CurrentUserHolder;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import com.sind.projectx.rest.validator.MenuItemValidator;
import com.sind.projectx.service.food.menu.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author Dmytro Bekuzarov
 */
@RestController
@RequestMapping("/menu-items")
@AccessRestrictions(roles = UserRole.MANAGER)
@Validated
public class MenuItemManagementController {

    @Autowired
    private MenuItemService menuItemService;
    @Autowired
    private MenuItemValidator menuItemValidator;

    @RequestMapping(value = "/{placeNetworkId}", method = POST)
    public MenuItem add(@PathVariable String placeNetworkId, @Valid @RequestBody MenuItem menuItem) {
        menuItemValidator.validateForAdd(menuItem, placeNetworkId, CurrentUserHolder.getUser().getId());
        return menuItemService.add(menuItem);
    }

    @RequestMapping(value = "", method = PUT)
    public MenuItem update(@Valid @RequestBody MenuItem menuItem) {
        menuItemValidator.validateForUpdate(menuItem, CurrentUserHolder.getUser().getId());
        return menuItemService.update(menuItem);
    }

    @RequestMapping(value = "/{menuItemId}", method = DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String menuItemId) {
        menuItemValidator.validateForDelete(menuItemId, CurrentUserHolder.getUser().getId());
        menuItemService.deleteById(menuItemId);
    }

}

