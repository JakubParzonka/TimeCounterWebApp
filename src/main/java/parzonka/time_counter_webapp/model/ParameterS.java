package parzonka.time_counter_webapp.model;

import lombok.Getter;
import lombok.Setter;

public class ParameterS {

    @Getter
    @Setter
    private String startInput;

    @Getter
    @Setter
    private String startSlote;

    @Getter
    @Setter
    private String stopInput;

    @Getter
    @Setter
    private String stopSlote;

    @Getter
    @Setter
    private String clockInternal;

    public ParameterS() {
    }

    public ParameterS(String startInput, String startSlote, String stopInput, String stopSlote, String clockInternal) {
        this.startInput = startInput;
        this.startSlote = startSlote;
        this.stopInput = stopInput;
        this.stopSlote = stopSlote;
        this.clockInternal = clockInternal;
    }

    @Override
    public String toString() {
        return "ParameterS{" +
                "startInput='" + startInput + '\'' +
                ", startSlote='" + startSlote + '\'' +
                ", stopInput='" + stopInput + '\'' +
                ", stopSlote='" + stopSlote + '\'' +
                ", clockInternal='" + clockInternal + '\'' +
                '}';
    }
}