package parzonka.time_counter_webapp.model;

import lombok.Getter;
import lombok.Setter;

public class ParameterCtrl {
    /**
     * TODO dopisać komentarze do pół
     */
    @Getter
    @Setter
    private String timeRange;

    /**
     * TODO jw. oraz czy nie da sie jakoś lepiej zreprezentować tego
     */
    @Getter
    @Setter
    private String entrance;

    public ParameterCtrl() {
    }

    public ParameterCtrl(String timeRange, String entrance) {
        this.timeRange = timeRange;
        this.entrance = entrance;
    }

    @Override
    public String toString() {
        return "ParameterCtrl{" +
                "timeRange=" + timeRange +
                ", entrance=" + entrance +
                '}';
    }
}
