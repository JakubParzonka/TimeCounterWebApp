package parzonka.time_counter_webapp.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import parzonka.time_counter_webapp.TimeCounterWebAppApplication;
import parzonka.time_counter_webapp.model.ParameterCtrl;
import parzonka.time_counter_webapp.model.ParameterS;

import java.util.BitSet;

public class CommandUtill {

    /**
     * Logger object instance
     */
    private static Logger logger = LogManager.getLogger(TimeCounterWebAppApplication.class);

//
//    /**
//     * Final command form in BitSet object
//     */
//    private BitSet command;
//
//    /**
//     * Temporary command form in ParameterS object
//     */
//    private ParameterS parameters;
//
//    public CommandUtill() {
//
//    }
//
//    public CommandUtill(ParameterS parameters) {
//        parameters = parameters;
//    }

    public static BitSet mapSregCommand(BitSet command, ParameterS parameters) {
        command.set(11, parameters.getStartInput().equals("true"));
        command.set(12, parameters.getStopInput().equals("true"));
        command.set(15, parameters.getStopInput().equals("false"));
        command.set(16, parameters.getStopSlote().equals("true"));
        command.set(19, parameters.getClockInternal().equals("true"));
        logger.info("Command value: " + command.toString());
        return command;
    }

    //TODO ogarnąć bałagan z tymi metodami dodać jakąś klasę nadrzędną Parameter, po której będą dziedziczyć np. ParameterS oraz ParamterCtrl
    public static BitSet mapCtrlCommand(BitSet command, ParameterCtrl parameters) {
        //TODO poprawić algorytm mapowania dla ctrl
        int tr = Integer.parseInt(parameters.getTimeRange());
        switch (tr) {
            case 1:
                break;
            case 10:
                command.set(10, true);
                break;
            case 100:
                command.set(11, true);
                break;
            case 4400:
                command.set(10, true);
                command.set(11, true);
                break;
            default:
                logger.error("Wrong time range value.It's imposibru so check this dude!!!!!!!");
        }
        int ent = Integer.parseInt(parameters.getEntrance());
        switch (ent) {
            case 0:
                break;
            case 1:
                command.set(8, true);
                break;
            case 3:
                command.set(8, true);
                command.set(9, true);
                break;
            default:
                logger.error("Wrong time range value.It's imposibru so check this dude!!!!!!!");
        }
        logger.info("Command value: " + command.toString());
        return command;
    }


}
