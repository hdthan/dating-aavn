package aavn.dating.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by lmchuc on 6/2/2017.
 */
@Controller
public class RoutesController {

    @GetMapping(value = {"/", "/login", "/register", "/quiz", "/user-page", "/user-profile", "/user-profile/Profile", "/user-profile/Password", "/match-page/*", "/target-list"})



    public String indexPage() {
        return "index";
    }
}
