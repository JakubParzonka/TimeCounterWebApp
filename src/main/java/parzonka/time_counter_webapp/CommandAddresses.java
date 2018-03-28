package parzonka.time_counter_webapp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import parzonka.time_counter_webapp.model.MessageWrapper;

import java.util.BitSet;


public class CommandAddresses {

    private static Logger logger = LogManager.getLogger(TimeCounterWebAppApplication.class);
    private static final byte[] resetAndCalibration = {0x00, 0x00, 0x00, 0x00, 0x00};
//    private static final byte[]
//    private static final byte[]
//    private static final byte[]
//    private static final byte[]
//    private static final byte[]
//    private static final byte[]

    /**
     * @return
     */
    public static MessageWrapper getWriteResetAndACalibrationAddr() {
        // $00
        MessageWrapper msgWrapper = new MessageWrapper(resetAndCalibration);
        logger.info("RESET register => " + msgWrapper.toString());
        return msgWrapper;
    }


    /**
     * @return
     */
    public static BitSet getWriteMeasurementAddr() {
        // $01
        BitSet bs = new BitSet();
        bs.set(0, true);
        bs.set(39, true);
        logger.info("MEAS register => " + bs.toString());
        return bs;
    }

    /**
     * @return
     */
    public static BitSet getWriteSregAddr() {
        // $02
        BitSet bs = new BitSet();
        bs.set(1, true);
        bs.set(39, true);
        logger.info("SET_S register => " + bs.toString());
        return bs;
    }

    /**
     * @return
     */
    public static BitSet getWriteEnRegAddr() {
        // $03
        BitSet bs = new BitSet();
        bs.set(0, 2, true);
        logger.info("SET_EN register => " + bs.toString());
        return bs;
    }

    /**
     * @return
     */
    public static BitSet getWriteCtrlAddr() {
        // $04
        BitSet bs = new BitSet();
        bs.set(2, true);
        bs.set(13, true);
        bs.set(39, true);
        logger.info("SET_CTRL register => " + bs.toString());
        return bs;
    }

    /**
     * @return
     */
    public static BitSet getReadDataAddr() {
        // $F0
        BitSet bs = new BitSet();
        bs.set(4, 9, true);
        bs.set(39, true);
        logger.info("RD_F_DATA register => " + bs.toString());
        return bs;
    }

    /**
     * @return
     */
    public static BitSet getReadSregAddr() {
        // $F2
        BitSet bs = new BitSet();
        bs.set(1, true);
        bs.set(4, 9, true);
        bs.set(39, true);
        logger.info("RD_S register => " + bs.toString());
        return bs;
    }

    /**
     * @return
     */
    public static BitSet getReadEnRegAddr() {
        // $F3
        BitSet bs = new BitSet();
        bs.set(0, 2, true);
        bs.set(4, 9, true);
        logger.info("RD_EN register => " + bs.toString());
        return bs;
    }

    /**
     * @return
     */
    public static BitSet getReadCtrlRegAddr() {
        // $F3
        BitSet bs = new BitSet();
        bs.set(2, true);
        bs.set(4, 9, true);
        logger.info("RD_CTRL register => " + bs.toString());
        return bs;
    }

}

