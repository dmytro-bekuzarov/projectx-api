package com.sind.projectx.rest.validator;

import com.sind.projectx.domain.user.User;
import com.sind.projectx.rest.exception.BadRequestException;
import com.sind.projectx.rest.exception.FieldMissingException;
import com.sind.projectx.service.user.UserService;
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
        validateExists(user.getId());
    }

    private void validateExists(String userId){
        if (StringUtils.isBlank(userId)) {
            throw new FieldMissingException("id");
        }
        boolean exists = userService.exists(userId);
        if (!exists) {
            throw new BadRequestException("error.invalid.user.id");
        }
    }
}
