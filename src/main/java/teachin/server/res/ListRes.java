package teachin.server.res;

import lombok.Getter;
import teachin.server.entity.AbstractEntity;

public class ListRes<E extends AbstractEntity> extends BaseRes {
    @Getter
    private final Iterable<E> data;

    public ListRes(String message, Iterable<E> data) {
        super(true, message);
        this.data = data;
    }
}
