package parzonka.time_counter_webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


@Service
public class Connection {

    private final static String IP_ADDRESS = "172.20.20.1";
    private final static int PORT_NUMBER = 5100;
    private Socket counterSocket;
    public static final int TIMEOUT = 10;

    private Socket prepareSocket() {
        try {
            System.out.println("InetAddress created");
            counterSocket = new Socket(InetAddress.getByName(IP_ADDRESS), PORT_NUMBER);
            System.out.println("Socket created");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counterSocket;
    }

    /**
     * @return DataOutputStream
     * @throws IOException
     */
    public DataOutputStream getOutputDataStream() throws IOException {
        return new DataOutputStream(counterSocket.getOutputStream());
    }

    /**
     * @return DataInputStream
     * @throws IOException
     */
    public DataInputStream getInputDataStream() throws IOException {
        return new DataInputStream(counterSocket.getInputStream());
    }

}
