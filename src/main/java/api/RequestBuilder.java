package api;

import authorization.AuthorizationBasic;
import authorization.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import ui.localization.Language;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.security.KeyStore;
import java.util.*;

import static io.restassured.RestAssured.preemptive;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.conn.ssl.SSLConnectionSocketFactory.SSL;

@Slf4j
public class RequestBuilder extends RequestSpecBuilder {
    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public RequestBuilder() {
        super();
        log.debug("[Request builder] Builder initialization");
        this.setContentType(ContentType.JSON);
        RestAssuredConfig config = RestAssured.config()
                .logConfig(LogConfig.logConfig().blacklistHeader(AUTHORIZATION))
                .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation(SSL).allowAllHostnames());
        this.setConfig(config);
    }

    /**
     * Returns new instance of builder from static reference
     *
     * @return new blank instance of builder
     */
    public static RequestBuilder builder() {
        return new RequestBuilder();
    }

    /**
     * Adding header "Authorization"
     *
     * @param headerValue - authorization header value
     * @return - builder instance for future buildings
     */
    public RequestBuilder addAuthHeader(String headerValue) {
        this.addHeader(HttpHeaders.AUTHORIZATION, headerValue);
        return this;
    }

    /**
     * Adding "Bearer" token as authorization header parameter
     *
     * @param bearerToken - bearer token
     * @return - builder instance for future buildings
     */
    public RequestBuilder addBearer(String bearerToken) {
        log.debug("[Request builder] Bearer token adding to request as Authorization header");
        return this.addAuthHeader(bearerToken);
    }

    /**
     * Adding "Bearer" token as authorization header parameter
     *
     * @param user      - User for which we are adding token
     * @param container - Container for which we are adding token
     * @return - builder instance for future buildings
     */
    public RequestBuilder addBearer(User user, Container container) {
        String bearerToken = user.getBearerToken(container);
        if (bearerToken != null)
            //Adding header if bearer token is not null
            return this.addBearer(bearerToken);
        //If bearer token is null header will not be added
        return this;
    }

    /**
     * @param user - user for which basic Auth Scheme will be added
     * @return - builder instance for future buildings
     */
    public RequestBuilder addBasicAuth(User user) {
        return this.addBasicAuth(user.getBasicAuth());
    }

    /**
     * @param authorizationBasic - Authorization info for basic auth authorization adding to request
     * @return - builder instance for future buildings
     */

    public RequestBuilder addBasicAuth(AuthorizationBasic authorizationBasic) {
        log.debug("Adding basic auth for request with authorization data: " + authorizationBasic);
        return this.setAuth(preemptive().basic(authorizationBasic.getUserName(), authorizationBasic.getPassword()));
    }


    /**
     * Add a query parameter to be sent with the request.
     * This method is the same as addParam(String, Collection) for all HTTP methods except POST where this method can be used to differentiate between form and query params.
     *
     * @param parameterName   – The parameter key
     * @param parameterValues – The parameter values
     * @return
     */
    @Override
    public RequestBuilder addQueryParam(String parameterName, Collection<?> parameterValues) {
        log.debug(String.format("[Request builder] Adding query parameter \"%s\" with value \"%s\"to request", parameterName, parameterValues));
        if (parameterValues != null)
            parameterValues.stream().filter(Objects::nonNull)
                    .forEach(parameter -> super.addQueryParam(parameterName, parameterValues));
        return this;
    }

    /**
     * Add Accept Language header to the request.
     */
    public RequestBuilder addAcceptLanguage(Language language) {
        log.debug("[Request builder] Adding \"Accept Language\" header with value: " + language.getAcceptLanguage());
        super.addHeader("Accept-language", language.getAcceptLanguage());
        return this;
    }

    @Override
    public RequestBuilder setBody(String body) {
        log.debug("[Request builder] Adding request body to request.");
        super.setBody(body);
        return this;
    }

    @Override
    public RequestBuilder setBody(byte[] body) {
        log.debug("[Request builder] Adding request body to request.");
        this.setBody(gson.toJson(body));
        return this;
    }

    @Override
    public RequestBuilder setBody(Object object) {
        log.debug("[Request builder] Adding request body to request.");
        this.setBody(gson.toJson(object));
        return this;
    }

    @Override
    public RequestBuilder setBody(Object object, ObjectMapper mapper) {
        log.debug("[Request builder] Adding request body to request.");
        super.setBody(object, mapper);
        return this;
    }

    @Override
    public RequestBuilder setBody(Object object, ObjectMapperType mapperType) {
        log.debug("[Request builder] Adding request body to request.");
        super.setBody(object, mapperType);
        return this;
    }

    @Override
    public RequestBuilder addCookies(Map<String, ?> cookies) {
        log.debug("[Request builder] Adding cookies to request.");
        super.addCookies(cookies);
        return this;
    }

    @Override
    public RequestSpecification build() {
        log.debug("[Request builder] RequestSpecification final object build.");
        return super.build();
    }

    @Override
    public RequestBuilder addCookie(Cookie cookie) {
        log.debug("[Request builder] Adding cookie to request.");
        super.addCookie(cookie);
        return this;
    }

    @Override
    public RequestBuilder addCookie(String key, Object value, Object... cookieNameValuePairs) {
        log.debug("[Request builder] Adding cookie to request.");
        super.addCookie(key, value, cookieNameValuePairs);
        return this;
    }

    @Override
    public RequestBuilder addCookie(String name) {
        log.debug("[Request builder] Adding cookie to request.");
        super.addCookie(name);
        return this;
    }

    @Override
    public RequestBuilder addCookies(Cookies cookies) {
        log.debug("[Request builder] Adding cookies to request.");
        super.addCookies(cookies);
        return this;
    }

    @Override
    public RequestBuilder addFilter(Filter filter) {
        log.debug("[Request builder] Adding filter to request.");
        super.addFilter(filter);
        return this;
    }

    @Override
    public RequestBuilder addFilters(List<Filter> filters) {
        log.debug("[Request builder] Adding filters to request.");
        super.addFilters(filters);
        return this;
    }

    @Override
    public RequestBuilder addParams(Map<String, ?> parametersMap) {
        log.debug("[Request builder] Adding params to request.");
        super.addParams(parametersMap);
        return this;
    }

    @Override
    public RequestBuilder addParam(String parameterName, Object... parameterValues) {
        log.debug("[Request builder] Adding param to request.");
        super.addParam(parameterName, parameterValues);
        return this;
    }

    @Override
    public RequestBuilder addParam(String parameterName, Collection<?> parameterValues) {
        log.debug("[Request builder] Adding param to request.");
        super.addParam(parameterName, parameterValues);
        return this;
    }

    @Override
    public RequestBuilder removeParam(String parameterName) {
        log.debug("[Request builder] Removing param from request.");
        super.removeParam(parameterName);
        return this;
    }

    @Override
    public RequestBuilder addQueryParams(Map<String, ?> parametersMap) {
        log.debug("[Request builder] Adding query params to request.");
        super.addQueryParams(parametersMap);
        return this;
    }

    @Override
    public RequestBuilder addQueryParam(String parameterName, Object... parameterValues) {
        log.debug("[Request builder] Adding query param to request.");
        if (parameterValues != null)
            Arrays.stream(parameterValues).filter(Objects::nonNull)
                    .forEach(parameter -> super.addQueryParam(parameterName, parameterValues));
        return this;
    }

    @Override
    public RequestBuilder removeQueryParam(String parameterName) {
        log.debug("[Request builder] Removing query param from request.");
        super.removeQueryParam(parameterName);
        return this;
    }

    @Override
    public RequestBuilder addFormParam(String parameterName, Collection<?> parameterValues) {
        log.debug("[Request builder] Adding form param to request.");
        super.addFormParam(parameterName, parameterValues);
        return this;
    }

    @Override
    public RequestBuilder addFormParams(Map<String, ?> parametersMap) {
        log.debug("[Request builder] Adding form params to request.");
        super.addFormParams(parametersMap);
        return this;
    }

    @Override
    public RequestBuilder addFormParam(String parameterName, Object... parameterValues) {
        log.debug("[Request builder] Adding form param to request.");
        super.addFormParam(parameterName, parameterValues);
        return this;
    }

    @Override
    public RequestBuilder removeFormParam(String parameterName) {
        log.debug("[Request builder] Removing form param to request.");
        super.removeFormParam(parameterName);
        return this;
    }

    @Override
    public RequestBuilder addPathParam(String parameterName, Object parameterValue) {
        log.debug("[Request builder] Adding path params to request.");
        super.addPathParam(parameterName, parameterValue);
        return this;
    }

    @Override
    public RequestBuilder addPathParams(String firstParameterName, Object firstParameterValue, Object... parameterNameValuePairs) {
        log.debug("[Request builder] Adding path params to request.");
        super.addPathParams(firstParameterName, firstParameterValue, parameterNameValuePairs);
        return this;
    }

    @Override
    public RequestBuilder addPathParams(Map<String, ?> parameterNameValuePairs) {
        log.debug("[Request builder] Adding path params to request.");
        super.addPathParams(parameterNameValuePairs);
        return this;
    }

    @Override
    public RequestBuilder removePathParam(String parameterName) {
        log.debug("[Request builder] Removing path params to request.");
        super.removePathParam(parameterName);
        return this;
    }

    @Override
    public RequestBuilder setKeyStore(String pathToJks, String password) {
        log.debug("[Request builder] Key store setting.");
        super.setKeyStore(pathToJks, password);
        return this;
    }

    @Override
    public RequestBuilder setTrustStore(String pathToJks, String password) {
        log.debug("[Request builder] Key store setting.");
        super.setTrustStore(pathToJks, password);
        return this;
    }

    @Override
    public RequestBuilder setTrustStore(File pathToJks, String password) {
        log.debug("[Request builder] Trusted store setting.");
        super.setTrustStore(pathToJks, password);
        return this;
    }

    @Override
    public RequestBuilder addHeaders(Map<String, String> headers) {
        log.debug("[Request builder] Adding headers to request");
        super.addHeaders(headers);
        return this;
    }

    @Override
    public RequestBuilder addHeader(String headerName, String headerValue) {
        log.debug("[Request builder] Adding header to request");
        super.addHeader(headerName, headerValue);
        return this;
    }

    @Override
    public RequestBuilder setContentType(ContentType contentType) {
        log.debug("[Request builder] ContentType setting to request");
        super.setContentType(contentType);
        return this;
    }

    @Override
    public RequestBuilder setContentType(String contentType) {
        log.debug("[Request builder] ContentType setting to request");
        super.setContentType(contentType);
        return this;
    }

    @Override
    public RequestBuilder setAccept(ContentType contentType) {
        log.debug("[Request builder] Accept setting to request");
        super.setAccept(contentType);
        return this;
    }

    @Override
    public RequestBuilder setAccept(String mediaTypes) {
        log.debug("[Request builder] Accept setting to request");
        super.setAccept(mediaTypes);
        return this;
    }

    @Override
    public RequestBuilder addMultiPart(MultiPartSpecification multiPartSpecification) {
        log.debug("[Request builder] Adding MultiPart to request");
        super.addMultiPart(multiPartSpecification);
        return this;
    }

    @Override
    public RequestBuilder addMultiPart(File file) {
        log.debug("[Request builder] Adding MultiPart to request");
        super.addMultiPart(file);
        return this;
    }

    @Override
    public RequestBuilder addMultiPart(String controlName, File file) {
        log.debug("[Request builder] Adding MultiPart to request");
        super.addMultiPart(controlName, file);
        return this;
    }

    @Override
    public RequestBuilder addMultiPart(String controlName, File file, String mimeType) {
        log.debug("[Request builder] Adding MultiPart to request");
        super.addMultiPart(controlName, file, mimeType);
        return this;
    }

    @Override
    public RequestBuilder addMultiPart(String controlName, String fileName, byte[] bytes) {
        log.debug("[Request builder] Adding MultiPart to request");
        super.addMultiPart(controlName, fileName, bytes);
        return this;
    }

    @Override
    public RequestBuilder addMultiPart(String controlName, String fileName, byte[] bytes, String mimeType) {
        log.debug("[Request builder] Adding MultiPart to request");
        super.addMultiPart(controlName, fileName, bytes, mimeType);
        return this;
    }

    @Override
    public RequestBuilder addMultiPart(String controlName, String fileName, InputStream stream) {
        log.debug("[Request builder] Adding MultiPart to request");
        super.addMultiPart(controlName, fileName, stream);
        return this;
    }

    @Override
    public RequestBuilder addMultiPart(String controlName, String fileName, InputStream stream, String mimeType) {
        log.debug("[Request builder] Adding MultiPart to request");
        super.addMultiPart(controlName, fileName, stream, mimeType);
        return this;
    }

    @Override
    public RequestBuilder addMultiPart(String controlName, String contentBody) {
        log.debug("[Request builder] Adding MultiPart to request");
        super.addMultiPart(controlName, contentBody);
        return this;
    }

    @Override
    public RequestBuilder addMultiPart(String controlName, String contentBody, String mimeType) {
        log.debug("[Request builder] Adding MultiPart to request");
        super.addMultiPart(controlName, contentBody, mimeType);
        return this;
    }

    @Override
    public RequestBuilder setAuth(AuthenticationScheme auth) {
        log.debug("[Request builder] Setting request authorization scheme");
        super.setAuth(auth);
        return this;
    }

    @Override
    public RequestBuilder setPort(int port) {
        log.debug("[Request builder] Setting request port");
        super.setPort(port);
        return this;
    }

    @Override
    public RequestBuilder setUrlEncodingEnabled(boolean isEnabled) {
        log.debug("[Request builder] Setting url encoding enabled");
        super.setUrlEncodingEnabled(isEnabled);
        return this;
    }

    @Override
    public RequestBuilder setSessionId(String sessionIdValue) {
        log.debug("[Request builder] Setting request session id");
        super.setSessionId(sessionIdValue);
        return this;
    }

    @Override
    public RequestBuilder setSessionId(String sessionIdName, String sessionIdValue) {
        log.debug("[Request builder] Setting request session id");
        super.setSessionId(sessionIdName, sessionIdValue);
        return this;
    }

    @Override
    public RequestBuilder addRequestSpecification(RequestSpecification specification) {
        log.debug("[Request builder] Request specification adding (merging)");
        super.addRequestSpecification(specification);
        return this;
    }

    @Override
    public RequestBuilder setConfig(RestAssuredConfig config) {
        log.debug("[Request builder] Config setting");
        super.setConfig(config);
        return this;
    }

    @Override
    public RequestBuilder setBaseUri(String uri) {
        log.debug("[Request builder] Base url setting");
        super.setBaseUri(uri);
        return this;
    }

    @Override
    public RequestBuilder setBaseUri(URI uri) {
        log.debug("[Request builder] Base url setting");
        super.setBaseUri(uri);
        return this;
    }

    @Override
    public RequestBuilder setBasePath(String path) {
        log.debug("[Request builder] Base path setting");
        super.setBasePath(path);
        return this;
    }

    @Override
    public RequestBuilder log(LogDetail logDetail) {
        log.debug("[Request builder] Logging details setting");
        super.log(logDetail);
        return this;
    }

    @Override
    public RequestBuilder setTrustStore(KeyStore trustStore) {
        log.debug("[Request builder] Trust store setting");
        super.setTrustStore(trustStore);
        return this;
    }

    @Override
    public RequestBuilder setKeyStore(KeyStore keyStore) {
        log.debug("[Request builder] Key store setting");
        super.setKeyStore(keyStore);
        return this;
    }

    @Override
    public RequestBuilder setRelaxedHTTPSValidation() {
        log.debug("[Request builder] Relaxed HTTPS validation setting");
        super.setRelaxedHTTPSValidation();
        return this;
    }

    @Override
    public RequestBuilder setRelaxedHTTPSValidation(String protocol) {
        log.debug("[Request builder] Relaxed HTTPS validation setting");
        super.setRelaxedHTTPSValidation(protocol);
        return this;
    }

    @Override
    public RequestBuilder setProxy(String host, int port) {
        log.debug("[Request builder] Proxy setting");
        super.setProxy(host, port);
        return this;
    }

    @Override
    public RequestBuilder setProxy(String host) {
        log.debug("[Request builder] Proxy setting");
        super.setProxy(host);
        return this;
    }

    @Override
    public RequestBuilder setProxy(int port) {
        log.debug("[Request builder] Proxy setting");
        super.setProxy(port);
        return this;
    }

    @Override
    public RequestBuilder setProxy(String host, int port, String scheme) {
        log.debug("[Request builder] Proxy setting");
        super.setProxy(host, port, scheme);
        return this;
    }

    @Override
    public RequestBuilder setProxy(URI uri) {
        log.debug("[Request builder] Proxy setting");
        super.setProxy(uri);
        return this;
    }

    @Override
    public RequestBuilder setProxy(ProxySpecification proxySpecification) {
        log.debug("[Request builder] Proxy setting");
        super.setProxy(proxySpecification);
        return this;
    }

    @Override
    public RequestBuilder and() {
        log.debug("[Request builder] Returning the same instance of request builder");
        return this;
    }
}