package com.lambdaschool.usermodel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserDoesntHavePermissionException extends AuthenticationException {
    public UserDoesntHavePermissionException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserDoesntHavePermissionException(String msg) {
        super(msg);
    }
}
