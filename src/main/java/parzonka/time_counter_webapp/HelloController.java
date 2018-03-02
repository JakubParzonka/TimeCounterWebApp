package parzonka.time_counter_webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {

    @RequestMapping(value = "/")
    public String welcomePage(Model model) {
        System.out.println("xD");
        model.addAttribute("welcome", "Hello user!");
        return "welcome";
    }


}
