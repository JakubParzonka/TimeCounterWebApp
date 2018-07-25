package parzonka.time_counter_webapp.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parzonka.time_counter_webapp.Connection;
import parzonka.time_counter_webapp.TimeCounterWebAppApplication;
import parzonka.time_counter_webapp.model.FrequencyParametersWrapper;
import parzonka.time_counter_webapp.model.MessageWrapper;
import parzonka.time_counter_webapp.model.ParameterCtrl;
import parzonka.time_counter_webapp.model.TimeParametersWrapper;
import parzonka.time_counter_webapp.utils.BitsConvert;
import parzonka.time_counter_webapp.utils.CommandAddrUtil;
import parzonka.time_counter_webapp.utils.CommandUtill;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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

    private final
    ResultService resultService;

    private int x = 1;

    @Autowired
    public CounterService(Connection connection, ResultService resultService) {
        this.connection = connection;
        this.resultService = resultService;
    }

    /**
     * Restart and calibrate counter
     */
    public void calibrate() {
        sendData(CommandAddrUtil.getWriteResetAndACalibrationAddr());
//        byte[] b1;
//        byte[] b2;
//        byte[] b3;
//        byte[] sReadReg = {(byte) 0xF2, 0x01, 0x00, 0x00, 0x00};
//
//        sendData(new MessageWrapper(sReadReg));
//        b1 = readData();
//        sendData(new MessageWrapper(sReadReg));
//        b2 = readData();
//        sendData(new MessageWrapper(sReadReg));
//        b3 = readData();
//
//        logger.info("b1 = " + BitsConvert.toBinaryString(b1));
//        logger.info("b2 = " + BitsConvert.toBinaryString(b2));
//        logger.info("b3 = " + BitsConvert.toBinaryString(b3));

//        byte[] offest;
//        byte[] sReadReg = {(byte) 0xF5, 0x01, 0x00, 0x00, 0x00};
//        sendData(new MessageWrapper(sReadReg));
//        offest = readData();
//        logger.info("offest = " + BitsConvert.toBinaryString(offest));
    }

    /**
     * Send data to counter
     *
     * @param messageWrapper
     */
    public void sendData(MessageWrapper messageWrapper) {
        if (x == 1) {
            try {
                DataOutputStream output = new DataOutputStream(connection.getOutputDataStream());
                output.write(messageWrapper.getMessage(), 0, messageWrapper.getLength());
                output.flush();
                logger.info("SENDING " + messageWrapper.getName() + " : " + Arrays.toString(messageWrapper.getMessage()));
            } catch (IOException e) {
                logger.error("Sending operation failed", e);
            }
        } else {
            logger.info("Message send successful: " + Arrays.toString(messageWrapper.getMessage()));
        }
    }

    /**
     * Read incoming data from socket
     *
     * @return String representation of result
     */
    public byte[] readData(int samples) {
        try {
            Thread.sleep(75);
        } catch (InterruptedException e) {
            logger.error("Error while waiting 75 ms ", e);
        }
        ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[samples]; // todo wyczytać w jakim rozmiarze jest przesyłany wynik i taki ustawić rozmiar
        if (x == 1) {
            try {
                DataInputStream inputData = new DataInputStream(connection.getInputDataStream());
                if (inputData.available() > 0) {
                    int bytesRead = inputData.read(buffer);
                    bufferStream.write(buffer, 0, bytesRead);
//                    if (inputData.available() == 0) {
//                        logger.warn("Input data available is 0");
//                    }
                } else {
                    logger.error("Input data is not availble");
                }
            } catch (IOException e) {
                logger.error("Error while reading from socket", e);
            }
            return buffer;
        } else {
            return buffer;
        }
    }

    /**
     * Send starting time parameters to counter
     *
     * @param timeParametersWrapper
     */
    public void saveTimeStartingParams(TimeParametersWrapper timeParametersWrapper) {
        logger.info(timeParametersWrapper.toString());
        MessageWrapper sWrapper = new MessageWrapper("S register", CommandUtill.mapSregCommand(
                CommandAddrUtil.getWriteSregAddr(), timeParametersWrapper.getStartInput(), timeParametersWrapper.getStartSlote(),
                timeParametersWrapper.getStopInput(), timeParametersWrapper.getStopSlote(), timeParametersWrapper.getClockInternal()).toByteArray());
        sendData(sWrapper);

        ParameterCtrl parameterCtrl = new ParameterCtrl(timeParametersWrapper.getTimeRange(), timeParametersWrapper.getEntrance());
        MessageWrapper ctrlWrapper = new MessageWrapper("CTRL register", CommandUtill.mapTimeCtrlCommand(
                CommandAddrUtil.getWriteCtrlAddr(), parameterCtrl).toByteArray());
        sendData(ctrlWrapper);

        RegistersService registersService = new RegistersService(this);
        registersService.sRegisterReadData();
        registersService.ctrlRegisterReadData();
        registersService.offsetRegisterReadData();
    }

    /**
     * Send starting frequency parameters to counter
     *
     * @param frequencyParametersWrapper
     */
    public void saveFrequencyStartingParams(FrequencyParametersWrapper frequencyParametersWrapper) {
        logger.info(frequencyParametersWrapper.toString());
        MessageWrapper sWrapper = new MessageWrapper("S register", CommandUtill.mapSregCommand(
                CommandAddrUtil.getWriteSregAddr(), frequencyParametersWrapper.getStartInput(), frequencyParametersWrapper.getStartSlote(),
                frequencyParametersWrapper.getStopInput(), frequencyParametersWrapper.getStopSlote(), frequencyParametersWrapper.getClockInternal()).toByteArray());
        sendData(sWrapper);

        ParameterCtrl parameterCtrl = new ParameterCtrl(frequencyParametersWrapper.getMeasTime(), frequencyParametersWrapper.getInputEnt());
        MessageWrapper ctrlWrapper = new MessageWrapper("CTRL register", CommandUtill.mapFrequencyCtrlCommand(
                CommandAddrUtil.getWriteCtrlAddr(), parameterCtrl).toByteArray());
        sendData(ctrlWrapper);

        RegistersService registersService = new RegistersService(this);
        registersService.sRegisterReadData();
        registersService.ctrlRegisterReadData();
        registersService.offsetRegisterReadData();
    }

    /**
     * Starting measurement process
     */
    public String startMeasurumentProcess() {
        String result;
        byte[] setDacArray1 = {0x05, (byte) 0x80, (byte) 0x80, 0x00, 0x00};
        byte[] setDacArray2 = {0x05, (byte) 0x80, (byte) 0x80, (byte) 0xF5, 0x03};
        byte[] setStopArray1 = {0x06, 0x10, (byte) 0xc2, 0x4d, 0x00, 0x04, 0x23, 0x00, 0x00, 0x00, 0x02, (byte) 0xcc, 0x04, 0x00, 0x00, 0x03, 0x04, 0x00, 0x00, 0x00};
        byte[] setStopArray2 = {0x06, 0x68, (byte) 0xc8, 0x64, (byte) 0x99, 0x04, 0x23, 0x00, 0x00, 0x00, 0x02, (byte) 0xcc, 0x04, 0x00, 0x00, 0x03, 0x04, 0x00, 0x00, 0x00};

        sendData(new MessageWrapper("SET DAC 1", setDacArray1));
        sendData(new MessageWrapper("SET DELAY 1", setStopArray1));
        sendData(new MessageWrapper("SET DAC 2", setDacArray2));
        sendData(new MessageWrapper("SET DELAY 2", setStopArray2));

        byte[] meas = {CommandAddrUtil.getWriteMeasurementAddr().toByteArray()[0], 0x00, 0x00, 0x00, 0x00};
        MessageWrapper startMeasurment = new MessageWrapper("MEAS register", meas);
        sendData(startMeasurment);

        RegistersService registersService = new RegistersService(this);
        String measruementNumbers = registersService.measNoRegisterReadData();


//        byte[] fifo = {CommandAddrUtil.getReadDataAddr().toByteArray()[0], 0x02, 0x00, 0x00, 0x00};
//        MessageWrapper startRecevingData = new MessageWrapper("FIFO memory", CommandAddrUtil.getReadDataAddr().toByteArray());
//        sendData(startRecevingData);


        result = registersService.fifoRegisterReadData("FIFO memory");
        return result;
    }

}
