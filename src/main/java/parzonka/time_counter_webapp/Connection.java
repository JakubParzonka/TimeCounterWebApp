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
@Scope(value = "singleton")
public class Connection {

    private final static String IP_ADDRESS = "172.20.20.1";
    private final static int PORT_NUMBER = 5100;
    private Socket counterSocket;

    @Bean
    private Socket prepareSocket() {
        String wroteMessage = "Nothing";
        try {

            System.out.println("InetAddress created");
            counterSocket = new Socket(InetAddress.getByName(IP_ADDRESS), PORT_NUMBER);
            System.out.println("Socket created");
//
//            outputData = new DataOutputStream(counterSocket.getOutputStream());
//            System.out.println("Data output stream created");
//            intputData = new DataInputStream(counterSocket.getInputStream());
//            System.out.println("Data input intputStreamFromCounter created");
//            outputData.write(message, 0, 5);
//            outputData.flush();
//            System.out.println("First message wrote");
////            outputStreamToCounter.write(messageForReadFromRegister);
////            System.out.println("Second message wrote");
////            wroteMessage = inFromCounter.readLine();
//
//            ByteArrayOutputStream bufferStream = new ByteArrayOutputStream();
////
////            byte[] buffer = new byte[1024];
////            if (intputData.available() > 0) {
////
////                int bytesRead = intputData.read(buffer);
////
////                bufferStream.write(buffer, 0, bytesRead);
////
////                if (intputData.available() == 0) {
////
////                }
////            }
//            wroteMessage = "Connected!";
//            counterSocket.close();
//            System.out.println("Socket closed\n\n************\n");
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
