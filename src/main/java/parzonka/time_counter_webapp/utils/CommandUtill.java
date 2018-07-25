package parzonka.time_counter_webapp.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import parzonka.time_counter_webapp.TimeCounterWebAppApplication;
import parzonka.time_counter_webapp.model.ParameterCtrl;

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

    public static BitSet mapSregCommand(BitSet command, String... parameters) {
        command.set(11, parameters[0].equals("true"));
        command.set(12, parameters[1].equals("true"));
        command.set(15, parameters[2].equals("false"));
        command.set(16, parameters[3].equals("true"));
        command.set(19, parameters[4].equals("true"));
        //logger.info("Command value: " + command.toString());
        return command;
    }

    //TODO ogarnąć bałagan z tymi metodami dodać jakąś klasę nadrzędną Parameter, po której będą dziedziczyć np. ParameterS oraz ParamterCtrl
    public static BitSet mapTimeCtrlCommand(BitSet command, ParameterCtrl parameters) {
        //TODO poprawić algorytm mapowania dla ctrl
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
        //logger.info("Command value: " + command.toString());
        return command;
    }

    public static BitSet mapFrequencyCtrlCommand(BitSet command, ParameterCtrl parameters) {
        String inputEnt = parameters.getEntrance();
        if (inputEnt.equals("A")) {
            command.set(8, false);
            command.set(9, false);
        } else if (inputEnt.equals("B")) {
            command.set(8);
        } else if (inputEnt.equals("F")) {
            command.set(9);
        } else {
            logger.error("Frequency control input");
        }

        String measTime = parameters.getTimeRange();
        if (measTime.equals("ext")) {
            command.set(16);
            command.set(19);
        } else if (measTime.equals("0,1ms")) {
            command.set(13);
            command.set(14);
            command.set(19);
        } else if (measTime.equals("1ms")) {
            command.set(15);
            command.set(19);
        } else if (measTime.equals("10ms")) {
            command.set(10);
            command.set(19);
        } else if (measTime.equals("100ms")) {
            command.set(10);
            command.set(15);
            command.set(19);
        } else if (measTime.equals("1s")) {
            command.set(11);
            command.set(19);
        } else if (measTime.equals("10s")) {
            command.set(11);
            command.set(15);
            command.set(19);
        } else if (measTime.equals("1us")) {
            command.set(10);
            command.set(11);
            command.set(19);
        } else if (measTime.equals("10us")) {
            command.set(10);
            command.set(11);
            command.set(15);
            command.set(19);
        } else {
            logger.error("Frequency measurement time");
        }

        return command;
    }


}
