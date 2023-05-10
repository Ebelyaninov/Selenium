package authorization.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChallengeStatus {
    ACTIVATED("activated"),
    REVOKED("revoked"),
    EXPIRED("expired"),
    COMPLETED("completed");

    private final String status;
}