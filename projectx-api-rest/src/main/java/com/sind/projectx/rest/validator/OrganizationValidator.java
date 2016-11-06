package com.sind.projectx.rest.validator;

import com.sind.projectx.domain.organization.Organization;
import com.sind.projectx.rest.exception.BadRequestException;
import com.sind.projectx.rest.exception.FieldMissingException;
import com.sind.projectx.service.organization.OrganizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Dmytro Bekuzarov
 */
@Component
public class OrganizationValidator {

    @Autowired
    private OrganizationService organizationService;

    public void validateForUpdate(Organization organization) {
        validateExists(organization.getId());
    }

    public void validateForDelete(String organizationId) {
        validateExists(organizationId);
    }

    private void validateExists(String organizationId) {
        if (StringUtils.isEmpty(organizationId)) {
            throw new FieldMissingException("id");
        }
        boolean exists = organizationService.exists(organizationId);
        if (!exists) {
            throw new BadRequestException("error.invalid.organization.id");
        }
    }

}
