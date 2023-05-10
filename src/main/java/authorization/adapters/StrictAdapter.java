package authorization.adapters;

import api.Endpoints;
import api.RequestBuilder;
import authorization.models.StrictResponse;
import authorization.models.SystemToken;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StrictAdapter extends AuthApiMainAdapter {

    public StrictAdapter() {
        super(Endpoints.AuthApi.STRICT);
    }

    public StrictResponse getStrictUsingPOST(String shortAuthToken, SystemToken requestBody, int statusCode) {
        RequestSpecification specification = RequestBuilder.builder()
                .addAuthHeader(shortAuthToken)
                .setBody(requestBody)
                .build();
        Response response = post(requestUrl, specification, statusCode);
        return gson.fromJson(response.body().asString(), StrictResponse.class);
    }
}