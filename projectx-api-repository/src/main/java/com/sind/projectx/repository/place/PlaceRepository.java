package com.sind.projectx.repository.place;

import com.sind.projectx.domain.place.Place;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dmytro Bekuzarov
 */
@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

    GeoResults<List<Place>> findByLocationNear(Point point, Distance distance);

}
