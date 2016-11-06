package com.sind.projectx.repository.food.menu;

import com.sind.projectx.domain.food.menu.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro Bekuzarov
 */
@Repository
public interface MenuRepository extends MongoRepository<Menu, String> {

    Menu findByPlaceId(String placeId);

}
