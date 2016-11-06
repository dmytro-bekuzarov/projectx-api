package com.sind.projectx.repository.organization;

import com.sind.projectx.domain.organization.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro Bekuzarov
 */
@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String>{
}
