package com.sind.projectx.rest.controller.place;

import com.sind.projectx.domain.Place;
import com.sind.projectx.domain.UserRole;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import com.sind.projectx.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Dmytro Bekuzarov
 */
@RestController
@RequestMapping("/places")
@AccessRestrictions(roles = {UserRole.USER, UserRole.ADMIN})
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/search/near", method = GET)
    public GeoResults<List<Place>> findPlacesNearBy(@RequestParam double latitude, @RequestParam double longitude,
                                                    @RequestParam("distance") double distanceValue) {
        Point point = new Point(longitude, latitude);
        Distance distance = new Distance(distanceValue, Metrics.KILOMETERS);
        return placeService.findNearestPlaces(point, distance);
    }

}
