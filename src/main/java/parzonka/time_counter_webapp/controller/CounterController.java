package parzonka.time_counter_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import parzonka.time_counter_webapp.CommandAddresses;
import parzonka.time_counter_webapp.model.MessageWrapper;
import parzonka.time_counter_webapp.model.ParameterWrapper;
import parzonka.time_counter_webapp.service.CounterService;

@Controller
public class CounterController {

    @Autowired
    CounterService counterService;

    @RequestMapping(value = "/counter", method = RequestMethod.GET)
    public String getMainCounterPage(Model model) {
        return "counter";
    }

    @RequestMapping(value = "paramTI", method = RequestMethod.GET)
    public String showTiParametersPage(Model model) {
        return "paramTI";
    }

    @RequestMapping(value = "paramF", method = RequestMethod.GET)
    public String showFreqParametersPage(Model model) {
        return "paramF";
    }

    @RequestMapping(value = "saveParams", method = RequestMethod.POST)
    public @ResponseBody
    String saveParams(@RequestBody ParameterWrapper parameterWrapper) {
        if (parameterWrapper != null) {
            counterService.saveStartingParams(parameterWrapper);
            return "Success!";
        } else {
            return "Failure saving params";
        }
    }

    @RequestMapping(value = "/startMeasurement", method = RequestMethod.POST)
    public @ResponseBody
    String startMeasurement() {
        counterService.startMeasrumentProcess();
        return counterService.readData();
    }

    @RequestMapping(value = "readSregParams", method = RequestMethod.POST)
    public @ResponseBody
    String readSregParams() {
        counterService.sendData(new MessageWrapper(CommandAddresses.getReadSregAddr().toByteArray()));
        return "Data from counter: " + counterService.readData();
    }

}
