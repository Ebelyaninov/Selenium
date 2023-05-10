package utils;

import api.ApiServiceVersion;
import api.ApiTypes;
import api.Container;

import java.util.Arrays;

public class UrlUtils {

    public static String buildUrl(Object... partsOfUrl) {
        StringBuilder urlBuilder = new StringBuilder();
        Arrays.stream(partsOfUrl).forEach(urlPart -> {
            urlBuilder.append(urlPart);
            if (!urlPart.equals(partsOfUrl[partsOfUrl.length - 1])) {
                urlBuilder.append("/");
            }
        });
        return urlBuilder.toString();
    }

    public static String buildUrl(Container container, String service, ApiTypes type, ApiServiceVersion version, String endpoint) {
        return buildUrl(container.getHost(), service, type.toString(), version.toString(), endpoint);
    }

    public static String buildUrl(Container container, ApiTypes type, ApiServiceVersion version, String endpoint) {
        return buildUrl(container.getHost(), type.toString(), version.toString(), endpoint);
    }

    public static String buildUrl(Container container, ApiTypes type, String service, ApiServiceVersion version, String endpoint) {
        return buildUrl(container.getHost(), type.toString(), service, version.toString(), endpoint);
    }

    public static String buildUrl(Container container, String firstService, String secondService, ApiTypes type, ApiServiceVersion version, String endpoint) {
        return buildUrl(container.getHost(), firstService, secondService, type.toString(), version.toString(), endpoint);
    }

    public static String buildUrl(Container container, Object... partsOfUrl) {
        return buildUrl();
    }
}