package parzonka.time_counter_webapp.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import parzonka.time_counter_webapp.Connection;
import parzonka.time_counter_webapp.TimeCounterWebAppApplication;
import parzonka.time_counter_webapp.model.FrequencyParametersWrapper;
import parzonka.time_counter_webapp.model.MessageWrapper;
import parzonka.time_counter_webapp.model.ParameterCtrl;
import parzonka.time_counter_webapp.model.TimeParametersWrapper;
import parzonka.time_counter_webapp.utils.CommandAddrUtil;
import parzonka.time_counter_webapp.utils.CommandUtill;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

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
        sendData(CommandAddrUtil.getWriteResetAndACalibrationAddr());
    }

    /**
     * Send data to counter
     *
     * @param messageWrapper
     */
    public void sendData(MessageWrapper messageWrapper) {
//        try {
//            // TODO stworzyć nowy exception jakiś dla npe
//            DataOutputStream output = new DataOutputStream(connection.getOutputDataStream());
//            output.write(messageWrapper.getMessage(), 0, messageWrapper.getLength());
//            output.flush();
        logger.info("Message send successful: " + Arrays.toString(messageWrapper.getMessage()));
//        } catch (IOException e) {
//            logger.error("Sending operation failed", e);
//        }
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
     * Send starting time parameters to counter
     *
     * @param timeParametersWrapper
     */
    public void saveTimeStartingParams(TimeParametersWrapper timeParametersWrapper) {
        logger.info(timeParametersWrapper.toString());
        MessageWrapper sWrapper = new MessageWrapper(CommandUtill.mapSregCommand(
                CommandAddrUtil.getWriteSregAddr(), timeParametersWrapper.getStartInput(),timeParametersWrapper.getStartSlote(),
                timeParametersWrapper.getStopInput(),timeParametersWrapper.getStopSlote(),timeParametersWrapper.getClockInternal()).toByteArray());
        sendData(sWrapper);

        ParameterCtrl parameterCtrl = new ParameterCtrl(timeParametersWrapper.getTimeRange(), timeParametersWrapper.getEntrance());
        MessageWrapper ctrlWrapper = new MessageWrapper(CommandUtill.mapTimeCtrlCommand(
                CommandAddrUtil.getWriteCtrlAddr(), parameterCtrl).toByteArray());
        sendData(ctrlWrapper);
    }

    /**
     *  Send starting frequency parameters to counter
     *
     * @param frequencyParametersWrapper
     */
    public void saveFrequencyStartingParams(FrequencyParametersWrapper frequencyParametersWrapper) {
        logger.info(frequencyParametersWrapper.toString());
        MessageWrapper sWrapper = new MessageWrapper(CommandUtill.mapSregCommand(
                CommandAddrUtil.getWriteSregAddr(), frequencyParametersWrapper.getStartInput(),frequencyParametersWrapper.getStartSlote(),
                frequencyParametersWrapper.getStopInput(),frequencyParametersWrapper.getStopSlote(),frequencyParametersWrapper.getClockInternal()).toByteArray());
        sendData(sWrapper);

        ParameterCtrl parameterCtrl = new ParameterCtrl(frequencyParametersWrapper.getMeasTime(), frequencyParametersWrapper.getInputEnt());
        MessageWrapper ctrlWrapper = new MessageWrapper(CommandUtill.mapFrequencyCtrlCommand(
                CommandAddrUtil.getWriteCtrlAddr(), parameterCtrl).toByteArray());
        sendData(ctrlWrapper);
    }

    /**
     * Starting measurement process
     */
    public void startMeasrumentProcess() {
        MessageWrapper startMeasurment = new MessageWrapper(CommandAddrUtil.getWriteMeasurementAddr().toByteArray());
        sendData(startMeasurment);

        MessageWrapper startRecevingData = new MessageWrapper(CommandAddrUtil.getReadDataAddr().toByteArray());
        sendData(startRecevingData);
    }

}
