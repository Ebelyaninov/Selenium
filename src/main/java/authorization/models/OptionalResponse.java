package authorization.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

/**
 * describe /system-tokens/optional response body
 */
@Data
public class OptionalResponse {

    @Expose
    @ToString.Exclude
    private String value;

    @Expose
    private String audience;

    @Expose
    private String scope;

    @Expose
    @SerializedName("expires_in")
    private int expiresIn;

    @Expose
    @SerializedName("available_scopes")
    private ArrayList<String> availableScopes;
}