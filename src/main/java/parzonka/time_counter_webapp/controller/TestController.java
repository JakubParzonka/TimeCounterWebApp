package parzonka.time_counter_webapp.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import parzonka.time_counter_webapp.utils.CommandAddrUtil;
import parzonka.time_counter_webapp.Connection;
import parzonka.time_counter_webapp.TimeCounterWebAppApplication;
import parzonka.time_counter_webapp.service.CounterService;

import java.io.DataOutputStream;
import java.io.IOException;

@Controller
public class TestController {

    /**
     * Logger object instance
     */
    private static Logger logger = LogManager.getLogger(TimeCounterWebAppApplication.class);

    private final
    Connection connection;

    @Autowired
    public TestController(Connection connection) {
        this.connection = connection;
    }

    @Autowired
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
        try {
            DataOutputStream output = new DataOutputStream(connection.getOutputDataStream());
            output.write(message, 0, message.length);
            output.flush();
            logger.info("First message wrote");
        } catch (IOException e) {
            logger.error("Writing operation failed", e);
        }
        return "Jeruzalem";
    }

    @RequestMapping(value = "/writeSecond", method = RequestMethod.POST)
    private @ResponseBody
    String writeSecond() {
        byte[] message = {(byte) 0xF3, 0x01, 0x00, 0x00, 0x00};
        try {
            DataOutputStream output = new DataOutputStream(connection.getOutputDataStream());
            output.write(message, 0, message.length);
            output.flush();
            logger.info("Second message wrote");
        } catch (IOException e) {
            logger.error("Writing operation failed", e);
        }

        return "Baśka miała fajny";
    }

    @RequestMapping(value = "/readData", method = RequestMethod.POST)
    private @ResponseBody
    String readButton() {
        return counterService.readData();
    }


}
