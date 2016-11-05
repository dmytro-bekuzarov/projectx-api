package com.sind.projectx.rest.controller;

import com.sind.projectx.repository.CurrentUserHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author Dmytro Bekuzarov
 */
@Component
public class ExceptionMessageResolver {

    @Resource(name = "messages")
    private MessageSource messageSource;

    public String getMessageText(String messageKey) {
        return getMessageText(messageKey, null);
    }

    public String getMessageText(String messageKey, String[] params) {
        try {
            Locale locale = new Locale(CurrentUserHolder.getUserLang());
            return messageSource.getMessage(messageKey, params, locale);
        } catch (Exception ex) {
            return null;
        }
    }

    public String getMessageText(FieldError fieldError) {
        String message = getMessageText(fieldError.getDefaultMessage(), null);
        if (StringUtils.isNotBlank(message)) {
            return message;
        }
        message = getMessage(fieldError);
        if (StringUtils.isNotBlank(message)) {
            return message;
        }
        return fieldError.getDefaultMessage();
    }

    private String getMessage(MessageSourceResolvable resolvable) {
        try {
            Locale locale = new Locale(CurrentUserHolder.getUserLang());
            return messageSource.getMessage(resolvable, locale);
        } catch (Exception ex) {
            return null;
        }
    }


}
