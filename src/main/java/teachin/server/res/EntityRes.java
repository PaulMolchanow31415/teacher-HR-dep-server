package teachin.server.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import teachin.server.entity.AbstractEntity;

public class EntityRes<E extends AbstractEntity> extends BaseRes {
    @ApiModelProperty(notes = "Сущность")
    @Getter
    protected final E data;

    public EntityRes(boolean success, String message, E data) {
        super(success, message);
        this.data = data;
    }
}
