package authorization.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Context {
    @SerializedName("account_number")
    @Expose
    private String accountNumber;
    @SerializedName("transfer_user_id")
    @Expose
    private Integer userId;
}