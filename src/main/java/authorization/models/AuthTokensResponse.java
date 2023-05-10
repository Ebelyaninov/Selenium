package authorization.models;

import api.model.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthTokensResponse extends BaseResponse {

    @Expose
    private String type;

    @Expose
    @SerializedName("auth_token")
    private AuthToken authToken;
}