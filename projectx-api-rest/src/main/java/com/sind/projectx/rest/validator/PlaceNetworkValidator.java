package com.sind.projectx.rest.validator;

import com.sind.projectx.domain.place.PlaceNetwork;
import com.sind.projectx.domain.user.OrganizationMembership;
import com.sind.projectx.rest.exception.BadRequestException;
import com.sind.projectx.rest.exception.FieldMissingException;
import com.sind.projectx.rest.exception.ForbiddenException;
import com.sind.projectx.service.place.PlaceNetworkService;
import com.sind.projectx.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Dmytro Bekuzarov
 */
@Component
public class PlaceNetworkValidator {

    @Autowired
    private PlaceNetworkService placeNetworkService;
    @Autowired
    private UserService userService;

    public void checkAccess(String placeNetworkId, String userId) {
        OrganizationMembership membership = userService.findMembershipByUserId(userId);
        if (membership == null) {
            throw new ForbiddenException();
        }
        PlaceNetwork placeNetwork
                = placeNetworkService.findByIdAndOrganizationId(placeNetworkId, membership.getOrganizationId());
        if (placeNetwork == null) {
            throw new ForbiddenException();
        }
    }

    public void validateForUpdate(PlaceNetwork placeNetwork) {
        validateExists(placeNetwork.getId());
    }

    private void validateExists(String placeNetworkId) {
        if (StringUtils.isBlank(placeNetworkId)) {
            throw new FieldMissingException("id");
        }
        boolean exists = placeNetworkService.exists(placeNetworkId);
        if (!exists) {
            throw new BadRequestException("error.invalid.place.network.id");
        }
    }

    public void validateForDelete(String placeNetworkId, String organizationId) {
        PlaceNetwork network = placeNetworkService.findByIdAndOrganizationId(placeNetworkId, organizationId);
        if (network == null) {
            throw new BadRequestException("error.invalid.place.network.id");
        }
    }
}
