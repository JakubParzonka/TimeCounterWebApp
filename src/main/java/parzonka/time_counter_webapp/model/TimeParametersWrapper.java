package parzonka.time_counter_webapp.model;

import lombok.Getter;
import lombok.Setter;

public class TimeParametersWrapper {

    @Getter
    @Setter
    public String startInput;

    @Getter
    @Setter
    public String startSlote;

    @Getter
    @Setter
    public String stopInput;

    @Getter
    @Setter
    public String stopSlote;

    @Getter
    @Setter
    public String clockInternal;

    @Getter
    @Setter
    private String timeRange;

    @Getter
    @Setter
    private String entrance;

    public TimeParametersWrapper() {
    }

    public TimeParametersWrapper(String startInput, String startSlote, String stopInput, String stopSlote, String clockInternal, String timeRange, String entrance) {
        this.startInput = startInput;
        this.startSlote = startSlote;
        this.stopInput = stopInput;
        this.stopSlote = stopSlote;
        this.clockInternal = clockInternal;
        this.timeRange = timeRange;
        this.entrance = entrance;
    }

    @Override
    public String toString() {
        return "TimeParametersWrapper{" +
                "startInput='" + startInput + '\'' +
                ", startSlote='" + startSlote + '\'' +
                ", stopInput='" + stopInput + '\'' +
                ", stopSlote='" + stopSlote + '\'' +
                ", clockInternal='" + clockInternal + '\'' +
                ", timeRange='" + timeRange + '\'' +
                ", entrance='" + entrance + '\'' +
                '}';
    }
}
