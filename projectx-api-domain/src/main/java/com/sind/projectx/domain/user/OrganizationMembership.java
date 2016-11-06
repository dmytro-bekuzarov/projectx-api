package com.sind.projectx.domain.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Dmytro Bekuzarov
 */
@Document(collection = "organizationMemberships")
@CompoundIndexes({
        @CompoundIndex(name = "organizationMembershipIndex", def = "{'userId': 1, 'organizationId': 1}")
})
public class OrganizationMembership {

    @Id
    private String id;
    private String userId;
    private String organizationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}
