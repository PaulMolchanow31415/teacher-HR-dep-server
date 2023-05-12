package teachin.server.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import teachin.server.entity.AbstractEntity;

public class ListRes<E extends AbstractEntity> extends BaseRes {
    @ApiModelProperty(notes = "Элементы")
    @Getter
    private final Iterable<E> data;

    public ListRes(String message, Iterable<E> data) {
        super(true, message);
        this.data = data;
    }
}
