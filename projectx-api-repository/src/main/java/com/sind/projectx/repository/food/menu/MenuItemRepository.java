package com.sind.projectx.repository.food.menu;

import com.sind.projectx.domain.food.menu.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro Bekuzarov
 */
@Repository
public interface MenuItemRepository extends MongoRepository<MenuItem, String> {
}
