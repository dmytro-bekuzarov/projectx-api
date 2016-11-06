package com.sind.projectx.rest.interceptor;

import com.sind.projectx.domain.user.User;
import com.sind.projectx.repository.CurrentUserHolder;
import com.sind.projectx.rest.exception.ForbiddenException;
import com.sind.projectx.rest.util.request.Headers;
import com.sind.projectx.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dmytro Bekuzarov
 */
@Component
public class CurrentUserInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String email = request.getHeader(Headers.USER_EMAIL);
        if (StringUtils.isEmpty(email)) {
            throw new ForbiddenException();
        }
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new ForbiddenException();
        }
        CurrentUserHolder.setUser(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        CurrentUserHolder.setUser(null);
    }

}
