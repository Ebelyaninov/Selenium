package authorization.models;

import authorization.AvailableScopes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class SystemToken {

    @Expose
    @ToString.Exclude
    private String value;

    @Expose
    @SerializedName("expires_in")
    private long expiresIn;

    @Expose
    private String scope;

    @Expose
    private String audience;

    @Expose
    private Context context;

    @Expose
    @SerializedName("accessed_by")
    private Integer accessedBy;

    @Expose
    @SerializedName("available_scopes")
    private AvailableScopes[] availableScopes;

    public static String convertScopesListToString(List<AvailableScopes> scopes){
        String scopeString = null;
        if (scopes != null) {
            scopeString = scopes.stream()
                    .map(AvailableScopes::getValue)
                    .reduce("", (partialString, element) -> partialString + " " + element)
                    .replaceFirst(" ", "");
        }
        return scopeString;
    }
}