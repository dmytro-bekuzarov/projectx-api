package com.sind.projectx.rest.controller.place;

import com.sind.projectx.domain.place.PlaceNetwork;
import com.sind.projectx.domain.user.OrganizationMembership;
import com.sind.projectx.domain.user.User;
import com.sind.projectx.domain.user.UserRole;
import com.sind.projectx.repository.CurrentUserHolder;
import com.sind.projectx.rest.exception.ForbiddenException;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import com.sind.projectx.rest.validator.PlaceNetworkValidator;
import com.sind.projectx.service.place.PlaceNetworkService;
import com.sind.projectx.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author Dmytro Bekuzarov
 */
@RestController
@RequestMapping("/place-networks")
@AccessRestrictions(roles = UserRole.MANAGER)
@Validated
public class PlaceNetworkManagementController {

    @Autowired
    private PlaceNetworkService placeNetworkService;
    @Autowired
    private PlaceNetworkValidator placeNetworkValidator;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = POST)
    public PlaceNetwork add(@Valid @RequestBody PlaceNetwork placeNetwork) {
        placeNetwork.setOrganizationId(getUserOrganizationId());
        return placeNetworkService.add(placeNetwork);
    }

    @RequestMapping(value = "", method = PUT)
    public PlaceNetwork update(@Valid @RequestBody PlaceNetwork placeNetwork) {
        placeNetwork.setOrganizationId(getUserOrganizationId());
        placeNetworkValidator.validateForUpdate(placeNetwork);
        return placeNetworkService.update(placeNetwork);
    }

    @RequestMapping(value = "/{placeNetworkId}", method = DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String placeNetworkId) {
        placeNetworkValidator.validateForDelete(getUserOrganizationId(), placeNetworkId);
        placeNetworkService.deleteById(placeNetworkId);
    }

    private String getUserOrganizationId() {
        User user = CurrentUserHolder.getUser();
        OrganizationMembership membership = userService.findMembershipByUserId(user.getId());
        if (membership == null) {
            throw new ForbiddenException();
        }
        return membership.getOrganizationId();
    }

}
