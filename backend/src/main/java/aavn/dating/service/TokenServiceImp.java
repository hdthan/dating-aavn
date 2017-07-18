package aavn.dating.service;

import aavn.dating.entity.User;
import aavn.dating.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dhvy on 6/13/2017.
 */
@Service
public class TokenServiceImp implements TokenService{

    @Value("${security.token.secret.key}")
    private String tokenKey;

//    @Autowired
//    private UserDetailsService userDetailsService;


    @Autowired
    private UserService userService;

    @Override
    public String getToken(String email, String password) {

        return null;
    }

    @Override
    public User getTokenFromGoogle(String accessToken) {
        String CLIENT_ID_1 = "559851266325-dhv20id67ppqeettk9hm2muiiv3cm9gi.apps.googleusercontent.com";
        String CLIENT_ID_2 = "958511244462-17uaib7jo4e4g29u2rrug9m1lptpbmg6.apps.googleusercontent.com";
        String CLIENT_ID_3 = "224315945436-ot3j6qq3l3r4k81jbsacfsf6godecof3.apps.googleusercontent.com";

        HttpTransport transport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2,CLIENT_ID_3))
                .build();
        GoogleIdToken idToken = null;

        try {
            idToken = verifier.verify(accessToken);
            System.out.println("Id Oken" + idToken);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            System.out.println("Email" + payload.getEmail());
            User user = userService.findByEmail(payload.getEmail());
            if (user != null) {
                return user;
            } else {
                User newUser = userService.save(this.prepareNewUser( payload.getEmail(),
                        payload.get("name").toString(),
                        "male",
                        payload.get("picture").toString()
                ));
                System.out.println("new User " + newUser.toString());
                return newUser;

            }
        }
        else {
            System.out.println("exception here");
        }
        return null;
    }



    private User prepareNewUser(String email, String name, String gender, String avatar){
        User user = new User();

        user.setEmail(email);
        user.setName(name);
        user.setGender(gender);
        user.setAvaImg(avatar);

        return user;
    }


}
