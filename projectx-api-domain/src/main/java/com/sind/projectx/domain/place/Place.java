package com.sind.projectx.domain.place;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sind.projectx.domain.common.Location;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * @author Dmytro Bekuzarov
 */
@Document(collection = "places")
public class Place {

    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String placeNetworkId;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    @JsonIgnore
    @NotNull(message = "error.invalid.location")
    private GeoJsonPoint location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceNetworkId() {
        return placeNetworkId;
    }

    public void setPlaceNetworkId(String placeNetworkId) {
        this.placeNetworkId = placeNetworkId;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    @Transient
    @JsonProperty("location")
    public Location getPlaceLocation() {
        if (location == null) {
            return null;
        }
        return new Location(location.getY(), location.getX());
    }

    @Transient
    public void setPlaceLocation(Location placeLocation) {
        try {
            location = new GeoJsonPoint(placeLocation.getLongitude(), placeLocation.getLatitude());
        } catch (Exception ex) {
            location = null;
        }
    }
}
