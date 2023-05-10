package authorization.adapters;

import api.Container;
import authorization.AvailableScopes;
import authorization.models.SystemToken;
import authorization.models.UserId;

import java.util.List;

import static authorization.models.SystemToken.convertScopesListToString;
import static org.apache.http.HttpStatus.SC_OK;

/**
 * class for initializing all tokens for user
 */
public class SystemTokenManager {

    public static String createShortAuthToken(int userId, String authenticateType) {
        UserId requestBody = new UserId(userId, authenticateType);
        return new AuthTokensAdapter()
                .postAuthToken(requestBody, SC_OK)
                .getAuthToken()
                .getValue();
    }

    public static String createSystemToken(String shortAuthToken, SystemToken requestBody) {
        return new StrictAdapter()
                .getStrictUsingPOST(shortAuthToken, requestBody, SC_OK)
                .getSystemToken()
                .getValue();
    }

    public static String createSystemToken(String shortAuthToken, Container container, List<AvailableScopes> scopes, long expiresIn) {
        SystemToken requestBody = SystemToken.builder()
                .audience(container.getAudience())
                .scope(convertScopesListToString(scopes))
                .expiresIn(expiresIn)
                .build();

        return createSystemToken(shortAuthToken, requestBody);
    }

    public static String createSystemTokenOptionalEndpoint(String shortAuthToken, Container container, List<AvailableScopes> scopes) {
        SystemToken requestBody = SystemToken.builder()
                .audience(container.getAudience())
                .scope(convertScopesListToString(scopes))
                .build();
        return new OptionalAdapter()
                .getResponseOptional(shortAuthToken, requestBody)
                .getValue();
    }
}