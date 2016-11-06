package com.sind.projectx.service.user;

import com.sind.projectx.domain.user.OrganizationMembership;
import com.sind.projectx.domain.user.User;
import com.sind.projectx.repository.user.OrganizationMembershipRepository;
import com.sind.projectx.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Dmytro Bekuzarov
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrganizationMembershipRepository organizationMembershipRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User add(User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.insert(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void deleteById(String userId) {
        userRepository.delete(userId);
    }

    public boolean exists(String userId) {
        return userRepository.exists(userId);
    }

    public OrganizationMembership findMembershipByUserId(String userId) {
        return organizationMembershipRepository.findByUserId(userId);
    }

    public void addUserToOrganization(String organizationId, String userId){
        OrganizationMembership membership = new OrganizationMembership();
        membership.setId(UUID.randomUUID().toString());
        membership.setOrganizationId(organizationId);
        membership.setUserId(userId);
        organizationMembershipRepository.insert(membership);
    }

}
