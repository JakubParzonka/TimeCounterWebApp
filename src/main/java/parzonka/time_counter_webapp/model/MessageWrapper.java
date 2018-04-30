package parzonka.time_counter_webapp.model;

import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;

public class MessageWrapper {
    /**
     * Bytes represantaion of message
     */
    @Setter
    @Getter
    private byte[] message;

    /**
     * Name of using register
     */
    @Setter
    @Getter
    private String name;
    public MessageWrapper(String name,byte[] message) {
        this.message = message;
    }

    public int getLength() {
        return message.length;
    }

    @Override
    public String toString() {
        return "MessageWrapper{" +
                "message=" + Arrays.toString(message) +
                '}';
    }
}
