package com.sind.projectx.rest.controller.organization;

import com.sind.projectx.domain.organization.Organization;
import com.sind.projectx.domain.user.UserRole;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import com.sind.projectx.service.organization.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Dmytro Bekuzarov
 */
@RestController
@RequestMapping("/organizations")
@AccessRestrictions(roles = {UserRole.USER, UserRole.ADMIN})
@Validated
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/{organizationId}", method = GET)
    public Organization findById(@PathVariable String organizationId) {
        return organizationService.findById(organizationId);
    }
}
