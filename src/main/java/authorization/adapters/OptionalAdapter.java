package authorization.adapters;

import api.Endpoints;
import api.RequestBuilder;
import authorization.models.OptionalResponse;
import authorization.models.SystemToken;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OptionalAdapter extends AuthApiMainAdapter {
    public OptionalAdapter() {
        super(Endpoints.AuthApi.OPTIONAL);
    }

    public OptionalResponse getResponseOptional(String shortAuthToken, SystemToken requestBody) {
        RequestSpecification specification = RequestBuilder.builder()
                .addAuthHeader(shortAuthToken)
                .setBody(requestBody)
                .build();
        Response response = post(requestUrl, specification);
        return gson.fromJson(response.body().asString(), OptionalResponse.class);
    }
}