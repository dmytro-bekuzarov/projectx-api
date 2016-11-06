package com.sind.projectx.service.food.menu;

import com.sind.projectx.domain.food.menu.MenuItem;
import com.sind.projectx.repository.food.menu.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmytro Bekuzarov
 */
@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItem findById(String menuItemId) {
        return menuItemRepository.findOne(menuItemId);
    }

    public MenuItem add(MenuItem menuItem) {
        menuItem.setId(UUID.randomUUID().toString());
        return menuItemRepository.insert(menuItem);
    }

    public MenuItem update(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public void deleteById(String menuItemId) {
        menuItemRepository.delete(menuItemId);
    }

    public List<MenuItem> findByIds(Collection<String> menuItems) {
        List<MenuItem> list = new ArrayList<>();
        for (MenuItem foodCategory : menuItemRepository.findAll(menuItems)) {
            list.add(foodCategory);
        }
        return list;
    }

    public List<String> findExistingIds(Collection<String> menuItems) {
        List<MenuItem> categories = findByIds(menuItems);
        return categories.stream().map(MenuItem::getId).collect(toList());
    }
}
