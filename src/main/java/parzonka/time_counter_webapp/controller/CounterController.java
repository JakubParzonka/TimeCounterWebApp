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
            MessageWrapper sWrapper = new MessageWrapper(CommandUtill.mapSregCommand(
                    CommandAddresses.getWriteSregAddr(), parameterS).toByteArray());
            counterService.sendData(sWrapper);

            ParameterCtrl parameterCtrl = new ParameterCtrl(parameterWrapper.getTimeRange(), parameterWrapper.getEntrance());
            MessageWrapper ctrlWrapper = new MessageWrapper(CommandUtill.mapCtrlCommand(
                    CommandAddresses.getWriteCtrlAddr(), parameterCtrl).toByteArray());
            counterService.sendData(ctrlWrapper);
            return "Success!";
        } else {
            return "Failure saving params";
        }
    }

    @RequestMapping(value = "/startMeasurement", method = RequestMethod.POST)
    public @ResponseBody
    String startMeasurement() {
        MessageWrapper startMeasurment = new MessageWrapper(CommandAddresses.getWriteMeasurementAddr().toByteArray());
        counterService.sendData(startMeasurment);

        MessageWrapper startReceingData = new MessageWrapper(CommandAddresses.getReadDataAddr().toByteArray());
        counterService.sendData(startReceingData);
        
        return "3,11512312 ms";
    }

    @RequestMapping(value = "readSregParams", method = RequestMethod.POST)
    public @ResponseBody
    String readSregParams() {
        counterService.sendData(new MessageWrapper(CommandAddresses.getReadSregAddr().toByteArray()));
        return "Data from counter: " + counterService.readData();
    }

}
