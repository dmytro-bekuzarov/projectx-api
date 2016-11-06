package com.sind.projectx.repository.user;

import com.sind.projectx.domain.user.OrganizationMembership;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro Bekuzarov
 */
@Repository
public interface OrganizationMembershipRepository extends MongoRepository<OrganizationMembership, String> {

    OrganizationMembership findByUserId(String userId);

}
