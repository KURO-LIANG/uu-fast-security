package com.uu.modules.sys.shiro;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author crazySea
 * @email 960236576@qq.com
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
