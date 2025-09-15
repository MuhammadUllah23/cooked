package com.example.cooked_backend.util;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.core.Authentication;

import java.util.Set;

public class SecurityUtils {
    /**
     * Creates an AuthorizationManager that requires all specified authorities.
     *
     * @param requiredAuthorities the authority strings to check (e.g., ROLE_USER, SUBSCRIPTION_PREMIUM)
     * @return AuthorizationManager for use in SecurityFilterChain.access()
    */
    public static AuthorizationManager<RequestAuthorizationContext> requireAllAuthorities(String... requiredAuthorities) {
        Set<String> required = Set.of(requiredAuthorities);

        return (authentication, context) -> {
            Authentication auth = authentication.get();
            boolean hasAll = required.stream()
                    .allMatch(req -> auth.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals(req))
                    );
            return new AuthorizationDecision(hasAll);
        };
    }
}
