package api;

import data.MainCoreDataManager;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static utils.JsonUtil.beautifyString;
import static utils.AllureHelper.attachJSONToReport;
import static utils.AllureHelper.attachTextToReport;
@Slf4j
public class RestAssuredRequestFilter implements Filter {

    public RestAssuredRequestFilter() {
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        String fullRequestLogMessage = logRequest(requestSpec) + logResponse(response);
        String curlRequest = logRequestAsCurl(requestSpec) + logResponse(response);

        if (MainCoreDataManager.getLogConfiguration().apiLogMethod().equals("curl"))
            log.info(curlRequest);
        else
            log.info(fullRequestLogMessage);

        if (MainCoreDataManager.getLogConfiguration().allureApiHumanAttaching())
            attachTextToReport("full_request.log", fullRequestLogMessage);
        if (MainCoreDataManager.getLogConfiguration().allureApiCurlAttaching())
            attachTextToReport("curl_request.log", curlRequest);
        return response;
    }

    private String logRequestAsCurl(FilterableRequestSpecification requestSpec) {
        return "Request was executed. CURL to reproduce request:\n curl -X " + requestSpec.getMethod() + " "
                + requestSpec.getURI()
                + addCurlHeaders(requestSpec)
                + addCurlBody(requestSpec)
                + addCurlQueryParams(requestSpec)
                + "\n";
    }

    private String addCurlHeaders(FilterableRequestSpecification requestSpecification) {
        StringBuilder headersPart = new StringBuilder();
        for (Header header : requestSpecification.getHeaders()) {
            headersPart.append(" ")
                    .append("-H \"")
                    .append(header.getName())
                    .append(": ")
                    .append(header.getValue())
                    .append("\"");
        }
        return headersPart.toString();
    }

    private String addCurlBody(FilterableRequestSpecification requestSpecification) {
        if (requestSpecification.getBody() != null) {
            return " -d '" + requestSpecification.getBody().toString() + "'";
        }
        return null;
    }

    private String addCurlQueryParams(FilterableRequestSpecification requestSpecification) {
        StringBuilder queryParams = new StringBuilder();
        if (requestSpecification.getQueryParams() != null && !requestSpecification.getQueryParams().isEmpty()) {
            Map<String, String> queryParamsMap = requestSpecification.getQueryParams();
            for (Map.Entry<String, String> entry : queryParamsMap.entrySet()) {
                queryParams.append(" ")
                        .append("-d \"")
                        .append(entry.getKey())
                        .append("=")
                        .append(String.valueOf(entry.getValue()))
                        .append("\"");
            }
        }
        return queryParams.toString();
    }

    private String logRequest(FilterableRequestSpecification requestSpec) {
        return "\n" +
                " ========================== Request ==========================" +
                logRequestInfo(requestSpec) +
                logRequestHeaders(requestSpec) +
                logRequestQueryParams(requestSpec) +
                logRequestBody(requestSpec);
    }

    private String logRequestInfo(FilterableRequestSpecification requestSpec) {
        attachTextToReport("Request method", requestSpec.getMethod());
        attachTextToReport("Request URL", requestSpec.getURI());
        return " \n Request Method => " + requestSpec.getMethod() +
                " \n Request URL => " + requestSpec.getURI();
    }

    private String logRequestBody(FilterableRequestSpecification requestSpec) {
        if (requestSpec.getBody() != null) {
            attachJSONToReport("Request body", beautifyString(requestSpec.getBody().toString()));
            return " \n Request Body => " + beautifyString(requestSpec.getBody().toString());
        }
        return "";
    }

    private String logRequestQueryParams(FilterableRequestSpecification requestSpec) {
        if (requestSpec.getQueryParams() != null) {
            attachJSONToReport("Request Query Params", requestSpec.getQueryParams().toString());
            return " \n Request Query parameters => " + requestSpec.getQueryParams().toString();
        }
        return "";
    }

    private String logRequestHeaders(FilterableRequestSpecification requestSpec) {
        attachJSONToReport("Request Headers", mockedHeaders(requestSpec).toString());
        return " \n Request Headers => " + mockedHeaders(requestSpec);
    }

    private String logResponse(Response response) {
        return "\n" +
                " ========================== Response ==========================" +
                logResponseCode(response) +
                logResponseMessage(response)
                + "\n";
    }

    private String logResponseCode(Response response) {
        attachTextToReport("Response status", response.getStatusLine());
        return " \n Response Status => " +
                response.getStatusCode() + " " + response.getStatusLine();
    }

    private String logResponseMessage(Response response) {
        if (response.getBody() != null) {
            attachJSONToReport("Response body", response.getBody().asPrettyString());
            return " \n Response Body => " + response.getBody().asPrettyString() + "\n";
        }
        return "";
    }

    private List<Header> mockedHeaders(FilterableRequestSpecification requestSpec) {
        return requestSpec.getHeaders().asList().stream()
                .map(header -> {
                    if (header.getName().equals(AUTHORIZATION)) {
                        return new Header(AUTHORIZATION, "Mocked");
                    }
                    return header;
                }).collect(Collectors.toList());
    }
}