package com.job.management.security.aspect;

import com.job.management.domain.enums.Role;
import com.job.management.security.annotation.RoleSecured;
import com.job.management.security.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
public class RoleSecurityAspect {

    @Autowired
    private JwtUtil jwtUtil;

    @Around("@annotation(roleSecured)")
    public Object around(ProceedingJoinPoint joinPoint, RoleSecured roleSecured) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Authorization header is missing or invalid");
        }

        String token = authorizationHeader.substring(7);
        for (Role role : roleSecured.roles()) {
            if (jwtUtil.hasRole(token, role.name())) {
                return joinPoint.proceed();
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have the required role");
    }
}