package com.sind.projectx.domain;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Dmytro Bekuzarov
 */
public class Location {

    @NotBlank
    private Double latitude;
    @NotBlank
    private Double longitude;

    public Location() {
    }

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
