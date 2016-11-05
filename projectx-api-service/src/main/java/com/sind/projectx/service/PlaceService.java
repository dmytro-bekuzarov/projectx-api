package com.sind.projectx.service;

import com.sind.projectx.domain.Place;
import com.sind.projectx.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Dmytro Bekuzarov
 */
@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public GeoResults<List<Place>> findNearestPlaces(Point point, Distance distance) {
        return placeRepository.findByLocationNear(point, distance);
    }

    public Place add(Place place) {
        place.setId(UUID.randomUUID().toString());
        return placeRepository.insert(place);
    }

    public Place update(Place place) {
        return placeRepository.save(place);
    }

    public void deleteById(String placeId) {
        placeRepository.delete(placeId);
    }

    public boolean exists(String placeId) {
        return placeRepository.exists(placeId);
    }
}
