package ch17.assib.fortnite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Assib Pajman
 *
 */
@Controller
public class HomeController {

    @GetMapping("/")
    private String homepage(Model datamodel) {
        return "home";
    }
}
