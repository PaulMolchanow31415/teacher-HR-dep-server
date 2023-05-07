package teachin.server.entity;

import lombok.Getter;

public enum ScientificDegree {
    CANDIDATE("candidate"),
    DOCTOR("doctor"),
    NO("no");

    @Getter
    private final String scientificDegree;

    ScientificDegree(String scientificDegree) {
        this.scientificDegree = scientificDegree;
    }
}
