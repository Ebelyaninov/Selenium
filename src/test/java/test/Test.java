//package test;
//
//import api.Container;
//import api.RequestBuilder;
//import api.RestAssuredRequestFilter;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import groovy.util.logging.Slf4j;
//import io.restassured.RestAssured;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.http.Method;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.apache.hc.core5.http.ContentType;
//
//import java.io.File;
//
//import static io.restassured.RestAssured.given;
//
//@Slf4j
//public class Test {
//    public static void main(String[] args) {
//        String url = "https://test.amazonaws.com/claims/20234116975/documents/e879f302-efa0-4147-ad45-c7743d1609c6-HSPTB/956acdc1-ce9e-4550-834b-8709249ba9eb.jpg?X-Amz-Signature=29268cb441e9d1d4ce0635f460ae773a6afd116cc9dde857a374be2d47572e17";
//
//        // Specify the path to the image file
//        String filePath = "/Users/yevheniibelianinov/downloads/test.jpeg";
//
//        // Send the PUT request
////        Response response = given()
////                .contentType(ContentType.IMAGE_JPEG.getMimeType())
////                .body(new File(filePath))
////                .put(url);
//
//        //Response response1 = RequestSpecBuilder();
//
//        String requestUrl = "url";
//
//        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
//                .setContentType("Image/jpeg");
//        Response response = given().spec(requestSpecBuilder.build().log().all())
//                .body(new File(filePath))
//                .urlEncodingEnabled(false)
//                .put(url);
//
//        // Print the response
//        System.out.println("Response Code: " + response.getStatusCode());
////        System.out.println("Response Body: " + response.getBody().asString());
//    }
//
//    protected Gson gson = new GsonBuilder()
//            .excludeFieldsWithoutExposeAnnotation()
//            .create();
//
//    protected Response get(String url, RequestSpecification requestSpec, int expectedStatusCode) {
//        return baseMethod(Method.GET, url, requestSpec, expectedStatusCode);
//    }
//
//    private Response baseMethod(Method method, String url, RequestSpecification requestSpec, int expectedStatusCode) {
//        return baseRequest(method, url, requestSpec).then()
//                .log().ifValidationFails()
//                .statusCode(expectedStatusCode)
//                .extract()
//                .response();
//    }
//
//    private Response baseRequest(Method method, String url, RequestSpecification requestSpec) {
//        return given()
//                .relaxedHTTPSValidation()
//                .baseUri(url)
//                .filter(new RestAssuredRequestFilter())
//                .spec(requestSpec)
//                .log().ifValidationFails()
//                .when()
//                .request(method, url);
//    }
//}
