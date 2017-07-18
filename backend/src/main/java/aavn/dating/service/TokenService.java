package aavn.dating.service;

import aavn.dating.entity.User;

/**
 * Created by dhvy on 6/13/2017.
 */
public interface TokenService
{
    String getToken(String email, String password);

    User getTokenFromGoogle(String accessToken);
}
