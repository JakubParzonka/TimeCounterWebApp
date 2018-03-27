package parzonka.time_counter_webapp.model;

public class ParameterCtrl {
    /**
     * TODO dopisać komentarze do pół
     */
    private int timeRange;

    /**
     * TODO jw. oraz czy nie da sie jakoś lepiej zreprezentować tego
     */
    private int entrance;

    public ParameterCtrl() {
    }

    public ParameterCtrl(int timeRange, int entrance) {
        this.timeRange = timeRange;
        this.entrance = entrance;
    }

    public int getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(int timeRange) {
        this.timeRange = timeRange;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }
}
