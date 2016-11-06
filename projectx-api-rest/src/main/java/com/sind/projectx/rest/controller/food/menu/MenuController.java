package com.sind.projectx.rest.controller.food.menu;

import com.sind.projectx.domain.food.menu.Menu;
import com.sind.projectx.domain.user.UserRole;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import com.sind.projectx.service.food.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Dmytro Bekuzarov
 */
@RestController
@RequestMapping("/places/{placeId}/menu")
@AccessRestrictions(roles = {UserRole.MANAGER, UserRole.ADMIN, UserRole.USER})
@Validated
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "", method = GET)
    public Menu findByPlaceId(@PathVariable String placeId) {
        return menuService.findByPlaceId(placeId);
    }

}
