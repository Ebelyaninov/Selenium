package authorization.adapters;

import api.*;

import static utils.UrlUtils.buildUrl;

public abstract class AuthApiMainAdapter extends MainAdapter {
    protected String requestUrl;

    protected AuthApiMainAdapter(String endpoint) {
        requestUrl = buildUrl(
                Container.AUTH_API,
                Endpoints.AuthApi.AUTHENTICATION,
                ApiTypes.REST,
                ApiServiceVersion.V1,
                endpoint
        );
    }
}