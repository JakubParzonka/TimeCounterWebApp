package parzonka.time_counter_webapp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.BitSet;

@Controller
public class TestController {

    private static Logger logger = LogManager.getLogger(TimeCounterWebAppApplication.class);

    @Autowired
    Connection connection;


    @RequestMapping(value = "/writeFirst", method = RequestMethod.POST)
    private @ResponseBody
    String writeFirst() {
        BitSet data = CommandAddresses.getSET_S_BitSetAddres();
        byte[] message = data.toByteArray();
        DataOutputStream output;
        try {
            output = connection.getOutputDataStream();
            output.write(message, 0, 5);
            output.flush();
            logger.info("First message wrote");
        } catch (IOException e) {
            logger.error("Writing operation failed", e);
        }
        return "Jeruzalem";
    }

    @RequestMapping(value = "/writeSecond", method = RequestMethod.POST)
    private void writeSecond() {
        System.out.println("writeSecond");
    }

    @RequestMapping(value = "/readData", method = RequestMethod.POST)
    private @ResponseBody
    String readButton() {
        return "płonie nad nami";
    }


}
