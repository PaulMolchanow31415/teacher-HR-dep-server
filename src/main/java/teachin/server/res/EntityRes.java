package teachin.server.res;

import teachin.server.entity.AbstractEntity;

public class EntityRes<E extends AbstractEntity> extends BaseRes {
    protected final E data;

    public EntityRes(boolean success, String message, E data) {
        super(success, message);
        this.data = data;
    }
}
