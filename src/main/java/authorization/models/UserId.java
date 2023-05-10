package authorization.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * class for /auth-tokens/user-id request body
 */
@Data
@AllArgsConstructor
public class UserId {

    @Expose
    @SerializedName("user_id")
    private int userId;

    @Expose
    private String provider;
}