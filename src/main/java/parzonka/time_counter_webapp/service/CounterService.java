package parzonka.time_counter_webapp.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parzonka.time_counter_webapp.CommandAddresses;
import parzonka.time_counter_webapp.Connection;
import parzonka.time_counter_webapp.TimeCounterWebAppApplication;
import parzonka.time_counter_webapp.model.MessageWrapper;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;

@Service
public class CounterService {

    /**
     * Logger object instance
     */
    private static Logger logger = LogManager.getLogger(TimeCounterWebAppApplication.class);

    private final
    Connection connection;

    @Autowired
    public CounterService(Connection connection) {
        this.connection = connection;
    }

    /**
     * Restart and calibrate counter
     */
    public void startCounter() {
        sendData(CommandAddresses.getWriteResetAndACalibrationAddr());
    }

    /**
     * Send data to counter
     *
     * @param messageWrapper
     */
    public void sendData(MessageWrapper messageWrapper) {
        try {
            DataOutputStream output = new DataOutputStream(connection.getOutputDataStream());
            output.write(messageWrapper.getMessage(), 0, messageWrapper.getLength());
            output.flush();
            logger.info("Message send successful: " + Arrays.toString(messageWrapper.getMessage()));
        } catch (IOException e) {
            logger.error("Sending operation failed", e);
        }
    }

    public String readData(){
        ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1460];
        try {
            DataInputStream inputData = new DataInputStream(connection.getInputDataStream());
            while (inputData.available() > 0) {
                int bytesRead = inputData.read(buffer);
                bufferStream.write(buffer, 0, bytesRead);
                if (inputData.available() == 0) {
                    logger.warn("Input data available is 0");
                }
            } /*else {
                logger.error("Input data is not availble");
            }*/
        } catch (IOException e) {
            logger.error("Error while reading from socket", e);
        }
        return Arrays.toString(buffer);
    }


}
