package parzonka.time_counter_webapp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


@Service
public class Connection {

    /**
     * Logger object instance
     */
    private static Logger logger = LogManager.getLogger(TimeCounterWebAppApplication.class);

    private final static String IP_ADDRESS = "172.20.20.1";
    private final static int PORT_NUMBER = 5100;
    private Socket counterSocket;
    public static final int TIMEOUT = 10;

    @Bean
    private Socket prepareSocket() {
        try {
            counterSocket = new Socket(InetAddress.getByName(IP_ADDRESS), PORT_NUMBER);
            counterSocket.setKeepAlive(true);
            logger.info("Socket connected");
        } catch (IOException e) {
            logger.error("Socket exception", e);
        }
        return counterSocket;
    }

    /**
     * @return DataOutputStream
     * @throws IOException
     */
    DataOutputStream getOutputDataStream() throws IOException {
        return new DataOutputStream(counterSocket.getOutputStream());
    }

    /**
     * @return DataInputStream
     * @throws IOException
     */
    DataInputStream getInputDataStream() throws IOException {
        return new DataInputStream(counterSocket.getInputStream());
    }

}
