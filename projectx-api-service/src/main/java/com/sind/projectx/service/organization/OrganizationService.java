package com.sind.projectx.service.organization;

import com.sind.projectx.domain.organization.Organization;
import com.sind.projectx.repository.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Dmytro Bekuzarov
 */
@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization findById(String organizationId) {
        return organizationRepository.findOne(organizationId);
    }

    public Organization add(Organization organization) {
        organization.setId(UUID.randomUUID().toString());
        return organizationRepository.insert(organization);
    }

    public Organization update(Organization organization) {
        return organizationRepository.save(organization);
    }

    public boolean exists(String organizationId) {
        return organizationRepository.exists(organizationId);
    }

    public void deleteById(String organizationId) {
        organizationRepository.delete(organizationId);
    }
}
