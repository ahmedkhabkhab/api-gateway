package com.bookshop.apigateway.user;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import java.util.List;

public class UserController {

    @GetMapping("user")
    public Mono<User> getUser() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(auth -> (OidcUser)auth.getPrincipal())
                .map(oidcUser -> new User(
                        oidcUser.getPreferredUsername(),
                        oidcUser.getName(),
                        oidcUser.getFamilyName(),
                        List.of("employee", "customer"))); // hardcoded for now, we'll update them later
    }

}
