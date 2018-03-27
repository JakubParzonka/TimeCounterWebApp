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
import parzonka.time_counter_webapp.model.ParameterCtrl;
import parzonka.time_counter_webapp.model.ParameterS;
import parzonka.time_counter_webapp.model.ParameterWrapper;
import parzonka.time_counter_webapp.service.CounterService;
import parzonka.time_counter_webapp.utils.CommandUtill;

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
            System.out.println(parameterWrapper.toString());
            ParameterS parameterS = new ParameterS(parameterWrapper.getStartInput(), parameterWrapper.getStartSlote(),
                    parameterWrapper.getStopInput(), parameterWrapper.getStopSlote(), parameterWrapper.getClockInternal());
            counterService.sendData(new MessageWrapper(CommandUtill.mapSregCommand(
                    CommandAddresses.getWriteSregAddr(), parameterS).toByteArray()));

            ParameterCtrl parameterCtrl = new ParameterCtrl(parameterWrapper.getTimeRange(), parameterWrapper.getEntrance());
            counterService.sendData(new MessageWrapper(CommandUtill.mapCtrlCommand(
                    CommandAddresses.getWriteCtrlAddr(), parameterCtrl).toByteArray()));
            return "Success!";
        } else {
            return "Failure saving params";
        }
    }

 /*   @RequestMapping(value = "saveCtrlParams", method = RequestMethod.POST)
    public @ResponseBody
    String saveCtrlParams(@RequestBody ParameterCtrl paramCtrl) {
        if (paramCtrl != null) {
            counterService.sendData(new MessageWrapper(CommandUtill.mapCommand2(
                    CommandAddresses.getWriteCtrlAddr(), paramCtrl).toByteArray()));
            System.out.println(paramCtrl.toString());
            return "Success!";
        } else {
            return "Failure saving params";
        }
    }*/

    @RequestMapping(value = "readSregParams", method = RequestMethod.POST)
    public @ResponseBody
    String readSregParams() {
        counterService.sendData(new MessageWrapper(CommandAddresses.getReadSregAddr().toByteArray()));
        return "Data from counter: " + counterService.readData();
    }

}
