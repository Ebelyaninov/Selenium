package authorization.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

@Data
public class AuthToken {

    @Expose
    @ToString.Exclude
    private String value;

    @Expose
    @SerializedName("user_id")
    private String userId;

    @Expose
    @SerializedName("session_id")
    private String sessionId;

    @Expose
    @SerializedName("created_at")
    private int createdAt;

    @Expose
    @SerializedName("used_at")
    private int usedAt;

    @Expose
    private int lifetime;

    @Expose
    @SerializedName("impersonator_id")
    private String impersonatorId;

    @Expose
    private String type;
}