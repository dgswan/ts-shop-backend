package com.tsystems.tshop.security;

import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

public class JwtLogoutFilter extends LogoutFilter {

    public JwtLogoutFilter() {
        super(new HttpStatusReturningLogoutSuccessHandler(), new JwtLogoutHandler());
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGOUT_URL);
    }
}
