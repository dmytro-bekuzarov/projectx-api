package com.sind.projectx.service.food.menu;

import com.sind.projectx.domain.food.menu.Menu;
import com.sind.projectx.repository.food.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Dmytro Bekuzarov
 */
@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public boolean exists(String menuId) {
        return menuRepository.exists(menuId);
    }

    public Menu findById(String menuId) {
        return menuRepository.findOne(menuId);
    }

    public Menu findByPlaceId(String placeId) {
        return menuRepository.findByPlaceId(placeId);
    }

    public Menu add(Menu menu) {
        menu.setId(UUID.randomUUID().toString());
        return menuRepository.insert(menu);
    }

    public Menu update(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteById(String menuId) {
        menuRepository.delete(menuId);
    }
}
