package parzonka.time_counter_webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CounterController {

    @RequestMapping(value = "/counter", method = RequestMethod.GET)
    public String getMainCounterPage(Model model){
        return "counter";
    }

    @RequestMapping(value = "paramTi", method = RequestMethod.GET)
    public String showTiParametersPage(Model model){
        return "paramTi";
    }
    @RequestMapping(value = "paramF", method = RequestMethod.GET)
    public String showFreqParametersPage(Model model){
        return "paramF";
    }

}
