package api.model;

import lombok.Data;

import java.util.List;

@Data
public class BaseListResponse<T> extends BaseResponse {
    List<T> items;

    public BaseListResponse(List<T> items) {
        this.items = items;
    }
}
