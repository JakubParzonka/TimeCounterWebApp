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
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.BitSet;

@Controller
public class TestController {

    /**
     * Logger object instance
     */
    private static Logger logger = LogManager.getLogger(TimeCounterWebAppApplication.class);

    /**
     * Socket of reading from counter
     */
    private Socket socketForReading;

    @Autowired
    Connection connection;


    @RequestMapping(value = "/writeFirst", method = RequestMethod.POST)
    private @ResponseBody
    String writeFirst() {
        // BitSet data = CommandAddresses.getWriteOfEnBitSetAddres();
//        byte[] message = data.toByteArray();
        byte[] message = {0x03, 0x12, 0x34, 0x56, 0x78};
        try {
            Socket counterSocket = new Socket(InetAddress.getByName("172.20.20.1"), 5100);
            counterSocket.setKeepAlive(true);
            DataOutputStream output = new DataOutputStream(counterSocket.getOutputStream());
            output.write(message, 0, message.length);
            output.flush();
            logger.info("First message wrote");
            counterSocket.close();
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
        byte[] message = {(byte) 0xF3, 0x00, 0x00, 0x00, 0x00};
        try {
            socketForReading = new Socket(InetAddress.getByName("172.20.20.1"), 5100);
            socketForReading.setKeepAlive(true);
            DataOutputStream output = new DataOutputStream(socketForReading.getOutputStream());
            output.write(message, 0, message.length);
            output.flush();
            logger.info("Second message wrote");
//            socketForReading.close();
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
            // socketForReading = new Socket(InetAddress.getByName("172.20.20.1"), 5100);
            DataInputStream inputData = new DataInputStream(socketForReading.getInputStream());
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
