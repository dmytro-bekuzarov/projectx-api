package com.sind.projectx.rest.controller.organization;

import com.sind.projectx.domain.organization.Organization;
import com.sind.projectx.domain.user.UserRole;
import com.sind.projectx.rest.exception.IdNotFoundException;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import com.sind.projectx.rest.validator.OrganizationValidator;
import com.sind.projectx.rest.validator.UserValidator;
import com.sind.projectx.service.organization.OrganizationService;
import com.sind.projectx.service.user.UserService;
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
@Validated
@RequestMapping("/organizations")
@AccessRestrictions(roles = UserRole.ADMIN)
public class OrganizationManagementController {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationValidator organizationValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "", method = POST)
    public Organization add(@Valid @RequestBody Organization organization) {
        return organizationService.add(organization);
    }

    @RequestMapping(value = "/{organizationId}", method = POST)
    @ResponseStatus(HttpStatus.OK)
    public void addUserToOrganization(@PathVariable String organizationId,
                                      @RequestParam String userId) {
        boolean exists = organizationService.exists(organizationId);
        if (!exists) {
            throw new IdNotFoundException();
        }
        userService.addUserToOrganization(organizationId, userId);
    }

    @RequestMapping(value = "", method = PUT)
    public Organization update(@Valid @RequestBody Organization organization) {
        organizationValidator.validateForUpdate(organization);
        return organizationService.update(organization);
    }

    @RequestMapping(value = "/{organizationId}", method = DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String organizationId) {
        organizationValidator.validateForDelete(organizationId);
        organizationService.deleteById(organizationId);
    }

}
