package parzonka.time_counter_webapp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
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
    public TestController(Connection connection) {
        this.connection = connection;
    }


    @RequestMapping(value = "/startAndReset", method = RequestMethod.POST)
    private @ResponseBody
    String startAndReset() {
        // BitSet data = CommandAddresses.getWriteOfEnBitSetAddres();
//        byte[] message = data.toByteArray();
        byte[] message = {0x00, 0x00, 0x00, 0x00, 0x00};
        try {
            DataOutputStream output = new DataOutputStream(connection.getOutputDataStream());
            output.write(message, 0, message.length);
            output.flush();
            logger.info("Started and calibrated");
        } catch (IOException e) {
            logger.error("Starting and calibrating operation failed", e);
        }
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
        BitSet data = CommandAddresses.getReadOfEnBitSetAddres();
//        byte[] message = data.toByteArray();
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
        ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[16];
        try {
            DataInputStream inputData = new DataInputStream(connection.getInputDataStream());
            if (inputData.available() > 0) {
                int bytesRead = inputData.read(buffer);
                bufferStream.write(buffer, 0, bytesRead);
                if (inputData.available() == 0) {
                    logger.warn("Input data available is 0");
                }
            } else {
                logger.error("Input data is not availble");
            }
        } catch (IOException e) {
            logger.error("Error while reading from socket", e);
        }
        return Arrays.toString(buffer);
    }


}
