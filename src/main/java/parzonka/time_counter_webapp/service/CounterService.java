package parzonka.time_counter_webapp.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parzonka.time_counter_webapp.CommandAddresses;
import parzonka.time_counter_webapp.Connection;
import parzonka.time_counter_webapp.TimeCounterWebAppApplication;
import parzonka.time_counter_webapp.model.MessageWrapper;
import parzonka.time_counter_webapp.model.ParameterCtrl;
import parzonka.time_counter_webapp.model.ParameterS;
import parzonka.time_counter_webapp.model.ParameterWrapper;
import parzonka.time_counter_webapp.utils.CommandUtill;

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
            // TODO stworzyć nowy exception jakiś dla npe
            DataOutputStream output = new DataOutputStream(connection.getOutputDataStream());
            output.write(messageWrapper.getMessage(), 0, messageWrapper.getLength());
            output.flush();
            logger.info("Message send successful: " + Arrays.toString(messageWrapper.getMessage()));
        } catch (IOException e) {
            logger.error("Sending operation failed", e);
        }
    }

    /**
     * Read incoming data from socket
     *
     * @return String representation of result
     */
    public String readData() {
        ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1460];
        try {
            DataInputStream inputData = new DataInputStream(connection.getInputDataStream());
            if (inputData.available() > 0) {
                int bytesRead = inputData.read(buffer);
                bufferStream.write(buffer, 0, bytesRead);
                if (inputData.available() == 0) {
                    logger.warn("Input data available is 0");
                }
            } else {
                logger.error("Input data is nolt availble");
            }
        } catch (IOException e) {
            logger.error("Error while reading from socket", e);
        }
        return Arrays.toString(buffer);
    }

    /**
     * Send starting parameters to counter
     *
     * @param parameterWrapper
     */
    public void saveStartingParams(ParameterWrapper parameterWrapper) {

        System.out.println(parameterWrapper.toString());

        ParameterS parameterS = new ParameterS(parameterWrapper.getStartInput(), parameterWrapper.getStartSlote(),
                parameterWrapper.getStopInput(), parameterWrapper.getStopSlote(), parameterWrapper.getClockInternal());
        MessageWrapper sWrapper = new MessageWrapper(CommandUtill.mapSregCommand(
                CommandAddresses.getWriteSregAddr(), parameterS).toByteArray());
            sendData(sWrapper);

        ParameterCtrl parameterCtrl = new ParameterCtrl(parameterWrapper.getTimeRange(), parameterWrapper.getEntrance());
        MessageWrapper ctrlWrapper = new MessageWrapper(CommandUtill.mapCtrlCommand(
                CommandAddresses.getWriteCtrlAddr(), parameterCtrl).toByteArray());
        sendData(ctrlWrapper);
    }

    /**
     * Starting measurement process
     */
    public void startMeasrumentProcess() {

        MessageWrapper startMeasurment = new MessageWrapper(CommandAddresses.getWriteMeasurementAddr().toByteArray());
        sendData(startMeasurment);

        MessageWrapper startReceingData = new MessageWrapper(CommandAddresses.getReadDataAddr().toByteArray());
        sendData(startReceingData);

    }
}
