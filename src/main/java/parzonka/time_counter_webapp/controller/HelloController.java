package parzonka.time_counter_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import parzonka.time_counter_webapp.Connection;

import java.io.IOException;


@Controller
public class HelloController {

    @Autowired
    Connection connection;

    @RequestMapping(value = "/test")
    public String welcomePage(Model model) throws IOException {
        model.addAttribute("welcome", "Hello user!");
        return "test";
    }
}
