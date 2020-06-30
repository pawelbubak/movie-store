package com.take.store.config.security.jwt;

import com.take.store.config.security.service.UserDetailsServiceImpl;
import com.take.store.config.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationConverter implements AuthenticationConverter {
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication convert(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);
        if (!StringUtils.hasText(header)) {
            return null;
        }

        header = header.trim();
        if (!StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME)) {
            return null;
        }
        header = header.substring(7);
        String username = jwtUtils.getUsernameFromToken(header);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtUtils.validateToken(header, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                return authenticationToken;
            }
        }
        return null;
    }
}
