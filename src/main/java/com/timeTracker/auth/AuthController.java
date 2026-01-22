package com.timeTracker.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
        private final MagicLinkService magicLinkService;

    public AuthController(MagicLinkService magicLinkService) {
        this.magicLinkService = magicLinkService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String sendLink(@RequestParam String email, Model model) {
        System.out.println("login");
        String link = magicLinkService.createMagicLink(email);
        System.out.println("MAGIC LINK: " + link);
        model.addAttribute("link", link);
        model.addAttribute("sent", true);
        return "login";
    }
}
