package com.sind.projectx.repository.place;

import com.sind.projectx.domain.place.PlaceNetwork;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dmytro Bekuzarov
 */
@Repository
public interface PlaceNetworkRepository extends MongoRepository<PlaceNetwork, String> {

    PlaceNetwork findByIdAndOrganizationId(String id, String organizationId);
}
