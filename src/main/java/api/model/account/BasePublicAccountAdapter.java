package api.model.account;

import api.ApiServiceVersion;
import api.ApiTypes;
import api.Endpoints;
import api.MainAdapter;
import data.MainCoreDataManager;

import static utils.UrlUtils.buildUrl;

public class BasePublicAccountAdapter extends MainAdapter {
    protected String requestUrl;

    protected BasePublicAccountAdapter(String endpoint) {
        requestUrl = buildUrl(MainCoreDataManager.getRoutes().evpbank(),
                Endpoints.EvpBank.PublicAccounts.HOST,
                ApiTypes.REST,
                ApiServiceVersion.V1,
                endpoint
        );
    }
}