package com.sind.projectx.rest.interceptor;

import com.sind.projectx.domain.User;
import com.sind.projectx.repository.CurrentUserHolder;
import com.sind.projectx.rest.exception.ForbiddenException;
import com.sind.projectx.rest.util.annotation.AccessRestrictions;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dmytro Bekuzarov
 */
@Component
public class UserAccessInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod) handler;
        AccessRestrictions methodAnnotation = method.getMethodAnnotation(AccessRestrictions.class);
        AccessRestrictions classAnnotation = method.getMethod().getDeclaringClass().getAnnotation(AccessRestrictions.class);
        if (methodAnnotation == null && classAnnotation == null) {
            return true;
        }

        AccessRestrictions actualAnnotation = methodAnnotation != null ? methodAnnotation : classAnnotation;

        User user = CurrentUserHolder.getUser();

        if (!ArrayUtils.contains(actualAnnotation.roles(), user.getRole())) {
            throw new ForbiddenException();
        }

        return true;
    }
}