package com.sind.projectx.rest.validator;

import com.sind.projectx.domain.User;
import com.sind.projectx.rest.exception.FieldMissingException;
import com.sind.projectx.rest.exception.IdNotFoundException;
import com.sind.projectx.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Dmytro Bekuzarov
 */
@Component
public class UserValidator {

    @Autowired
    private UserService userService;

    public void validateForUpdate(User user) {
        if (StringUtils.isBlank(user.getId())) {
            throw new FieldMissingException("id");
        }
        boolean exists = userService.exists(user.getId());
        if (!exists) {
            throw new IdNotFoundException();
        }
    }
}
