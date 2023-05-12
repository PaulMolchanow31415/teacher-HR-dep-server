package teachin.server.entity;

import lombok.Getter;

public enum ScientificDegree {
    CANDIDATE("кандидат"),
    DOCTOR("доктор"),
    NO("нет");

    @Getter
    private final String scientificDegree;

    ScientificDegree(String scientificDegree) {
        this.scientificDegree = scientificDegree;
    }
}
