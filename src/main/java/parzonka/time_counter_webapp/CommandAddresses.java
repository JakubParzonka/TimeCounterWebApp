package parzonka.time_counter_webapp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.BitSet;

public class CommandAddresses {
    private static Logger logger = LogManager.getLogger(TimeCounterWebAppApplication.class);

    public static BitSet getWriteOfEnBitSetAddres() {
        // $03
        BitSet bs = new BitSet();
        bs.set(0,2, true);
        bs.set(8,true);
        bs.set(9,true);
        for (int i = 10; i < 40; i++) {
            bs.set(i, false);
        }
//        bs.set(39,true);
        logger.info("Write of EN register => " + bs.toString());
        return bs;
    }

    public static BitSet getReadOfEnBitSetAddres() {
        // $F3
        BitSet bs = new BitSet();
        bs.set(0,2, true);
        bs.set(4, 9, true);
        for (int i = 10; i < 40; i++) {
//            if (i % 2 == 0)
            bs.set(i, false);
        }

        logger.info("Read of EN register => " + bs.toString());
        return bs;
    }

    public static BitSet getRESET_BitSetAddres() {
        // 01000011
        BitSet bs = new BitSet();
        bs.set(0, 1, true);
        bs.set(2, 5, false);
        bs.set(6, true);
        bs.set(7, false);
        logger.info("CA => RESET - " + bs.toString());
        return bs;
    }


    public static BitSet getSET_TRIG_BitSetAddres() {
        // 01000100
        BitSet bs = new BitSet();
        bs.set(0, 2, false);
        bs.set(3, true);
        bs.set(4, 6, false);
        bs.set(6, true);
        bs.set(7, false);
        logger.info("CA => SET_TRIG - " + bs.toString());
        return bs;
    }

    public static BitSet getTIRG_DIV_BitSetAddres() {
        // 01000101
        BitSet bs = new BitSet();
        bs.set(0, true);
        bs.set(1, false);
        bs.set(2, true);
        bs.set(3, 5, false);
        bs.set(6, true);
        bs.set(7, false);
        logger.info("CA => TRIG_DIV - " + bs.toString());
        return bs;
    }

    public static BitSet getSYNTH_N_BitSetAddres() {
        // 0F
        BitSet bs = new BitSet();
        bs.set(0, 1, true);
        bs.set(2, 5, false);
        bs.set(6, true);
        bs.set(7, false);
        logger.info("CA => SYNTH_N - " + bs.toString());
        return bs;
    }
}

