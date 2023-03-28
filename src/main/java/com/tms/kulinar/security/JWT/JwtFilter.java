package com.tms.kulinar.security.JWT;

import com.tms.kulinar.domain.User;
import com.tms.kulinar.service.UsersService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends GenericFilterBean {
    private String token;
    private String userLogin;
    private final JwtProvider jwtProvider;
    private final UsersService usersService;

    public JwtFilter(JwtProvider jwtProvider, UsersService usersService) {
        this.jwtProvider = jwtProvider;
        this.usersService = usersService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        token = ((HttpServletRequest) servletRequest).getHeader("Authorization");

        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtProvider.isValid(token)) {
                userLogin = jwtProvider.getLoginFromJwt(token);
                Optional<User> user = usersService.getUserByLogin(userLogin);
                if (user.isPresent()) {
                    UserDetails userDetails = org.springframework.security.core.userdetails.User
                            .withUsername(user.get().getLogin())
                            .password(user.get().getPassword())
                            .roles(usersService.getRole(user.get().getId()))
                            .build();
                    UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(userAuth);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
