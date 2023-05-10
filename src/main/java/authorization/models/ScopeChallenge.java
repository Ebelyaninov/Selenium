package authorization.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ScopeChallenge {

    @Expose
    @SerializedName("challenge_id")
    private String challengeId;

    @Expose
    private String identifier;

    @Expose
    @SerializedName("user_id")
    private String userId;

    @Expose
    private PropertiesItem properties;

    @Expose
    private String scope;

    @Expose
    private String audience;

    @Expose
    @SerializedName("session_id")
    private String sessionId;

    @Expose
    @SerializedName("status")
    private ChallengeStatus challengeStatus;

    @Expose
    private String context;
}