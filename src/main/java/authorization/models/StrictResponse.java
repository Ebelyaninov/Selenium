package authorization.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * describe /system-tokens/strict response body
 */
@Data
public class StrictResponse {

    @Expose
    private String type;

    @Expose
    @SerializedName("scope_challenge")
    private ScopeChallenge scopeChallenge;

    @Expose
    @SerializedName("system_token")
    private SystemToken systemToken;
}