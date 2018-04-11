package com.locaweb.tweets.listagem.gateways.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HomeController {

    @GetMapping(value = "/")
    public RedirectView home() {
        return new RedirectView("swagger-ui.html");
    }

}
