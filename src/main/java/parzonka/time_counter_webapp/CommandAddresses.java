package parzonka.time_counter_webapp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.BitSet;

public class CommandAddresses {

    private static Logger logger = LogManager.getLogger(TimeCounterWebAppApplication.class);

    /**
     * @return
     */
    public static BitSet getWriteResetAndACalibrationAddr() {
        // $00
        BitSet bs = new BitSet();
        bs.set(0, 9, false);
        logger.info("RESET register => " + bs.toString());
        return bs;
    }

    /**
     * @return
     */
    public static BitSet getWriteMeasurementAddr() {
        // $01
        BitSet bs = new BitSet();
        bs.set(0, true);
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
    public static BitSet getReadDataAddr() {
        // $F0
        BitSet bs = new BitSet();
        bs.set(4, 9, true);
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
        logger.info("RD_S register => " + bs.toString());
        return bs;
    }

    /**
     *
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

}

