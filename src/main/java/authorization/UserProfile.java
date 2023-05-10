package authorization;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static authorization.adapters.SystemTokenManager.createShortAuthToken;
@Slf4j
@Getter
public class UserProfile {
    private final int covenanteeId;
    private final String shortAuthToken;

    public UserProfile(
            int covenanteeId,
            UserAuthType authenticateType
    ) {
        log.info("User object with generated tokens initializing...");
        this.covenanteeId = covenanteeId;
        log.info("User id is set to => " + this.covenanteeId);
        log.info("Generating of short auth token starting...");
        this.shortAuthToken = "Bearer " + createShortAuthToken(this.covenanteeId, authenticateType.getAuthenticationType());
        log.debug("Short auth token is set to => " + this.shortAuthToken);
    }

    /**
     * @return value that will be implemented into the Allure report
     */
    @Override
    public String toString() {
        return "clientId = " + covenanteeId;
    }
}
