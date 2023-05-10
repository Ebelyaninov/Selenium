package authorization.adapters;

import api.Endpoints;
import api.MacTokenUtil;
import api.RequestBuilder;
import authorization.models.AuthTokensResponse;
import authorization.models.UserId;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthTokensAdapter extends AuthApiMainAdapter {

    public AuthTokensAdapter() {
        super(Endpoints.AuthApi.AUTH_TOKENS);
    }

    public AuthTokensResponse postAuthToken(UserId requestBody, int statusCode) {
        String macToken = MacTokenUtil.getMacToken(requestUrl, gson.toJson(requestBody));
        RequestSpecification requestSpecification = RequestBuilder.builder()
                .addAuthHeader(macToken)
                .setBody(requestBody)
                .build();

        Response response = post(requestUrl, requestSpecification, statusCode);
        return gson.fromJson(response.body().asString(), AuthTokensResponse.class);
    }
}