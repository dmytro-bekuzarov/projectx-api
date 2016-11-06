package com.sind.projectx.rest.validator;

import com.sind.projectx.domain.place.Place;
import com.sind.projectx.rest.exception.FieldMissingException;
import com.sind.projectx.rest.exception.IdNotFoundException;
import com.sind.projectx.service.place.PlaceService;
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
    @Autowired
    private PlaceNetworkValidator placeNetworkValidator;

    public void validateForAdd(Place place, String userId) {
        validateAccess(place.getPlaceNetworkId(), userId);
    }

    public void validateForUpdate(Place place, String userId) {
        validateExists(place.getId());
        validateAccess(place.getPlaceNetworkId(), userId);
    }

    public void validateForDelete(String placeId, String userId) {
        Place place = validateExists(placeId);
        validateAccess(place.getPlaceNetworkId(), userId);
    }

    private Place validateExists(String placeId) {
        if (StringUtils.isBlank(placeId)) {
            throw new FieldMissingException("id");
        }
        Place place = placeService.findById(placeId);
        if (place == null) {
            throw new IdNotFoundException();
        }
        return place;
    }

    public void checkPlaceAccess(String placeId, String userId) {
        if (StringUtils.isBlank(placeId)) {
            throw new FieldMissingException("placeId");
        }
        Place place = placeService.findById(placeId);
        validateAccess(place.getPlaceNetworkId(), userId);
    }

    private void validateAccess(String placeNetworkId, String userId) {
        placeNetworkValidator.checkAccess(placeNetworkId, userId);
    }

}
