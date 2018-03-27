package parzonka.time_counter_webapp.model;

public class ParameterS {

    public String startInput;
    public String startSlote;
    public String stopInput;
    public String stopSlote;
    public String clockInternal;

    public ParameterS() {
    }
    public ParameterS(String startInput, String startSlote, String stopInput, String stopSlote, String clockInternal) {
        this.startInput = startInput;
        this.startSlote = startSlote;
        this.stopInput = stopInput;
        this.stopSlote = stopSlote;
        this.clockInternal = clockInternal;
    }

    public String getStartInput() {
        return startInput;
    }

    public String getStartSlote() {
        return startSlote;
    }

    public String getStopInput() {
        return stopInput;
    }

    public String getStopSlote() {
        return stopSlote;
    }

    public String getClockInternal() {
        return clockInternal;
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
