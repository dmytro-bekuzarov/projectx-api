package com.sind.projectx.rest.validator;

import com.sind.projectx.domain.Place;
import com.sind.projectx.rest.exception.FieldMissingException;
import com.sind.projectx.rest.exception.IdNotFoundException;
import com.sind.projectx.service.PlaceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Dmytro Bekuzarov
 */
@Component
public class PlaceValidator {

    @Autowired
    private PlaceService placeService;

    public void validateForUpdate(Place place) {
        if (StringUtils.isBlank(place.getId())) {
            throw new FieldMissingException("id");
        }
        boolean exists = placeService.exists(place.getId());
        if (!exists) {
            throw new IdNotFoundException();
        }
    }
}
