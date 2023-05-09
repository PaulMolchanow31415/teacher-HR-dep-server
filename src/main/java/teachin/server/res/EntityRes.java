package teachin.server.res;

import lombok.Getter;
import teachin.server.entity.AbstractEntity;

public class EntityRes<E extends AbstractEntity> extends BaseRes {
    @Getter
    protected final E data;

    public EntityRes(boolean success, String message, E data) {
        super(success, message);
        this.data = data;
    }
}
