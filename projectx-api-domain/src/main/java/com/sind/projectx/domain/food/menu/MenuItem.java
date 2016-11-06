package com.sind.projectx.domain.food.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmytro Bekuzarov
 */
@Document(collection = "menuItems")
public class MenuItem {

    @Id
    private String id;
    @NotBlank
    private String name;
    @JsonIgnore
    private String placeNetworkId;
    @NotEmpty
    private List<String> categories = new ArrayList<>();

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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
