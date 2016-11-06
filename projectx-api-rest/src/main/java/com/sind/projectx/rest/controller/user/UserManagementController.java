package com.sind.projectx.rest.controller.user;

import com.sind.projectx.domain.user.User;
import com.sind.projectx.domain.user.UserRole;
import com.sind.projectx.rest.exception.IdNotFoundException;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import com.sind.projectx.rest.validator.UserValidator;
import com.sind.projectx.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Dmytro Bekuzarov
 */
@RestController
@RequestMapping("/users")
@AccessRestrictions(roles = UserRole.ADMIN)
@Validated
public class UserManagementController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "", method = POST)
    public User addUser(@Valid @RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "", method = PUT)
    public User updateUser(@Valid @RequestBody User user) {
        userValidator.validateForUpdate(user);
        return userService.update(user);
    }

    @RequestMapping(value = "/{userId}", method = DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userId) {
        boolean exists = userService.exists(userId);
        if (!exists) {
            throw new IdNotFoundException();
        }
        userService.deleteById(userId);
    }

}
