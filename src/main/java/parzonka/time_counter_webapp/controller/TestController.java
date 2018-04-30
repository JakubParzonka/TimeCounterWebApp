package parzonka.time_counter_webapp.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import parzonka.time_counter_webapp.model.MessageWrapper;
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
    Connection connection;

    @Autowired
    public TestController(Connection connection, CounterService counterService) {
        this.connection = connection;
        this.counterService = counterService;
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
        //       byte[] message = {(byte) 0xF0, 0x00, 0x00, 0x00, 0x00};
//        try {
//            DataOutputStream output = new DataOutputStream(connection.getOutputDataStream());
//            output.write(message, 0, message.length);
//            output.flush();
//            logger.info("First message wrote");
//        } catch (IOException e) {
//            logger.error("Writing operation failed", e);
//        }
        counterService.sendData(new MessageWrapper("CTRL writing test",message));
        return "Jeruzalem";
    }

    @RequestMapping(value = "/sRegRead", method = RequestMethod.POST)
    private @ResponseBody
    String sRegRead() {
        byte[] message = {(byte) 0xF2, 0x01, 0x00, 0x00, 0x00};
        String result = "";
        counterService.sendData(new MessageWrapper("S register", message));
        result = "S register = " + Arrays.toString(counterService.readData());
        logger.info(result);
        return result;
    }


    @RequestMapping(value = "/enRegRead", method = RequestMethod.POST)
    private @ResponseBody
    String enRegRead() {
        byte[] message = {(byte) 0xF3, 0x01, 0x00, 0x00, 0x00};
        String result = "";
        counterService.sendData(new MessageWrapper("EN register", message));
        result = "EN register = " + Arrays.toString(counterService.readData());
        logger.info(result);
        return result;
    }

    @RequestMapping(value = "/ctrlRegRead", method = RequestMethod.POST)
    private @ResponseBody
    String ctrlRegRead() {
        byte[] message = {(byte) 0xF4, 0x01, 0x00, 0x00, 0x00};
        String result = "";
        counterService.sendData(new MessageWrapper("CTRL register", message));
        result = "CTRL register = " + Arrays.toString(counterService.readData());
        logger.info(result);
        return result;
    }

    @RequestMapping(value = "/offsetRead", method = RequestMethod.POST)
    private @ResponseBody
    String offsetRead() {
        byte[] message = {(byte) 0xF5, 0x01, 0x00, 0x00, 0x00};
        String result = "";
        counterService.sendData(new MessageWrapper("OFFSET register",message));
        result = "OFFSET register = " + Arrays.toString(counterService.readData());
        logger.info(result);
        return result;
    }

    @RequestMapping(value = "/readData", method = RequestMethod.POST)
    private @ResponseBody
    String readButton() {
        return Arrays.toString(counterService.readData());
    }


}
