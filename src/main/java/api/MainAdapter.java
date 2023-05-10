package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class MainAdapter {
    protected Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    protected Response get(String url, RequestSpecification requestSpec, int expectedStatusCode) {
        return baseMethod(Method.GET, url, requestSpec, expectedStatusCode);
    }

    protected Response post(String url, RequestSpecification requestSpec, int expectedStatusCode) {
        return baseMethod(Method.POST, url, requestSpec, expectedStatusCode);
    }

    protected Response put(String url, RequestSpecification requestSpec, int expectedStatusCode) {
        return baseMethod(Method.PUT, url, requestSpec, expectedStatusCode);
    }

    protected Response delete(String url, RequestSpecification requestSpec, int expectedStatusCode) {
        return baseMethod(Method.DELETE, url, requestSpec, expectedStatusCode);
    }

    protected Response patch(String url, RequestSpecification requestSpec, int expectedStatusCode) {
        return baseMethod(Method.PATCH, url, requestSpec, expectedStatusCode);
    }

    protected Response get(String url, RequestSpecification requestSpec) {
        return baseMethod(Method.GET, url, requestSpec);
    }

    protected Response post(String url, RequestSpecification requestSpec) {
        return baseMethod(Method.POST, url, requestSpec);
    }

    protected Response put(String url, RequestSpecification requestSpec) {
        return baseMethod(Method.PUT, url, requestSpec);
    }

    protected Response delete(String url, RequestSpecification requestSpec) {
        return baseMethod(Method.DELETE, url, requestSpec);
    }

    protected Response patch(String url, RequestSpecification requestSpec) {
        return baseMethod(Method.PATCH, url, requestSpec);
    }

    @Deprecated
    //todo need to be removed after moving all to RequestBuilder.java usage. USE RequestBuilder.addBearer instead.
    /**
     * This method is to handle adding of auth token to Request Specification in order not to assign it
     * directly in each method.
     *
     * @param authHeader - the authorization header content that you would like to assign
     * @param rs         - optional parameter that stands for {@link RequestSpecification} object
     * @return - modified RequestSpecification object
     */
    protected RequestSpecification setRequestSpecAuthHeader(Object authHeader, RequestSpecification... rs) {
        RequestSpecification resultRs = rs.length == 0 ? given() : rs[0];
        if (authHeader == null) {
            return resultRs;
        }
        return resultRs.header(AUTHORIZATION, authHeader);
    }

    private Response baseMethod(Method method, String url, RequestSpecification requestSpec, int expectedStatusCode) {
        return baseRequest(method, url, requestSpec).then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    private Response baseMethod(Method method, String url, RequestSpecification requestSpec) {
        return baseRequest(method, url, requestSpec).then()
                .log().ifValidationFails()
                .extract()
                .response();
    }

    private Response baseRequest(Method method, String url, RequestSpecification requestSpec) {
        return given()
                .relaxedHTTPSValidation()
                .baseUri(url)
                .filter(new RestAssuredRequestFilter())
                .spec(requestSpec)
                .log().ifValidationFails()
                .when()
                .request(method, url);
    }
}
