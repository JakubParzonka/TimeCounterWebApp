package parzonka.time_counter_webapp.model;

import java.util.Arrays;

public class MessageWrapper {
    /**
     * Bytes represantaion of message
     */
    private byte[] message;

    public MessageWrapper(byte[] message) {
        this.message = message;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
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
