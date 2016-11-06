package com.sind.projectx.service.place;

import com.sind.projectx.domain.place.PlaceNetwork;
import com.sind.projectx.repository.place.PlaceNetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Dmytro Bekuzarov
 */
@Service
public class PlaceNetworkService {

    @Autowired
    private PlaceNetworkRepository placeNetworkRepository;

    public PlaceNetwork add(PlaceNetwork placeNetwork) {
        placeNetwork.setId(UUID.randomUUID().toString());
        return placeNetworkRepository.insert(placeNetwork);
    }

    public boolean exists(String placeNetworkId) {
        return placeNetworkRepository.exists(placeNetworkId);
    }

    public PlaceNetwork update(PlaceNetwork placeNetwork) {
        return placeNetworkRepository.save(placeNetwork);
    }

    public PlaceNetwork findByIdAndOrganizationId(String placeNetworkId, String organizationId) {
        return placeNetworkRepository.findByIdAndOrganizationId(placeNetworkId, organizationId);
    }

    public void deleteById(String placeNetworkId) {
        placeNetworkRepository.delete(placeNetworkId);
    }
}
