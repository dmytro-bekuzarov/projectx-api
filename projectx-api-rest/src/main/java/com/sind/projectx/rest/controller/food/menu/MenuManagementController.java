package com.sind.projectx.rest.controller.food.menu;

import com.sind.projectx.domain.food.menu.Menu;
import com.sind.projectx.domain.user.UserRole;
import com.sind.projectx.repository.CurrentUserHolder;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import com.sind.projectx.rest.validator.MenuValidator;
import com.sind.projectx.service.food.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Dmytro Bekuzarov
 */
@RestController
@RequestMapping("/menu")
@AccessRestrictions(roles = UserRole.MANAGER)
@Validated
public class MenuManagementController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuValidator menuValidator;

    @RequestMapping(value = "", method = POST)
    public Menu add(@Valid Menu menu) {
        menuValidator.validateForAdd(menu, CurrentUserHolder.getUser().getId());
        return menuService.add(menu);
    }

    @RequestMapping(value = "", method = PUT)
    public Menu update(@Valid Menu menu) {
        menuValidator.validateForUpdate(menu, CurrentUserHolder.getUser().getId());
        return menuService.update(menu);
    }

    @RequestMapping(value = "/{menuId}", method = DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String menuId) {
        menuValidator.validateForDelete(menuId, CurrentUserHolder.getUser().getId());
        menuService.deleteById(menuId);
    }
}
