package parzonka.time_counter_webapp.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import parzonka.time_counter_webapp.model.MessageWrapper;
import parzonka.time_counter_webapp.service.RegistersService;
import parzonka.time_counter_webapp.utils.BitsConvert;
import parzonka.time_counter_webapp.utils.CommandAddrUtil;
import parzonka.time_counter_webapp.Connection;
import parzonka.time_counter_webapp.TimeCounterWebAppApplication;
import parzonka.time_counter_webapp.service.CounterService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;

@Controller
public class TestController {

    /**
     * Logger object instance
     */
    private static Logger logger = LogManager.getLogger(TimeCounterWebAppApplication.class);


    private final
    RegistersService registersService;

    @Autowired
    public TestController(CounterService counterService, RegistersService registersService) {
        this.counterService = counterService;
        this.registersService = registersService;
    }

    private final
    CounterService counterService;

    @RequestMapping(value = "/startAndReset", method = RequestMethod.POST)
    private @ResponseBody
    String startAndReset() {
        counterService.sendData(CommandAddrUtil.getWriteResetAndACalibrationAddr());
        return "Started and calibrated";
    }

    @RequestMapping(value = "/writeFirst", method = RequestMethod.POST)
    private @ResponseBody
    String writeFirst() {
        byte[] message = {0x03, 0x12, 0x34, 0x56, 0x78};
        counterService.sendData(new MessageWrapper("CTRL writing test", message));
        return "Jeruzalem";
    }

    @RequestMapping(value = "/fifoRegRead", method = RequestMethod.POST)
    private @ResponseBody
    String fifoRegRead() {
        return registersService.fifoRegisterReadData("FIFO memory");
    }


    @RequestMapping(value = "/sRegRead", method = RequestMethod.POST)
    private @ResponseBody
    String sRegRead() {
        return registersService.sRegisterReadData();
    }


    @RequestMapping(value = "/enRegRead", method = RequestMethod.POST)
    private @ResponseBody
    String enRegRead() {
        return registersService.enRegisterReadData();

    }

    @RequestMapping(value = "/ctrlRegRead", method = RequestMethod.POST)
    private @ResponseBody
    String ctrlRegRead() {
        return registersService.ctrlRegisterReadData();

    }

    @RequestMapping(value = "/offsetRead", method = RequestMethod.POST)
    private @ResponseBody
    String offsetRead() {
        return registersService.offsetRegisterReadData();
    }

    @RequestMapping(value = "/measNoRead", method = RequestMethod.POST)
    private @ResponseBody
    String measNoRead() {
        return registersService.measNoRegisterReadData();
    }

    @RequestMapping(value = "/readData", method = RequestMethod.POST)
    private @ResponseBody
    String readButton() {
        return Arrays.toString(counterService.readData(8));
    }


}
