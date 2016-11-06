package com.sind.projectx.rest.controller.place;

import com.sind.projectx.domain.place.Place;
import com.sind.projectx.domain.user.UserRole;
import com.sind.projectx.repository.CurrentUserHolder;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import com.sind.projectx.rest.validator.PlaceValidator;
import com.sind.projectx.service.place.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Dmytro Bekuzarov
 */
@RestController
@RequestMapping("/places")
@AccessRestrictions(roles = UserRole.MANAGER)
@Validated
public class PlaceManagementController {

    @Autowired
    private PlaceService placeService;
    @Autowired
    private PlaceValidator placeValidator;

    @RequestMapping(value = "", method = POST)
    public Place addPlace(@Valid @RequestBody Place place) {
        placeValidator.validateForAdd(place, CurrentUserHolder.getUser().getId());
        return placeService.add(place);
    }

    @RequestMapping(value = "", method = PUT)
    public Place updatePlace(@Valid @RequestBody Place place) {
        placeValidator.validateForUpdate(place, CurrentUserHolder.getUser().getId());
        return placeService.update(place);
    }

    @RequestMapping(value = "/{placeId}", method = DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePlace(@PathVariable String placeId) {
        placeValidator.validateForDelete(placeId, CurrentUserHolder.getUser().getId());
        placeService.deleteById(placeId);
    }


}
