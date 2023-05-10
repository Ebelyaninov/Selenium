package api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@SuperBuilder(toBuilder = true, builderMethodName = "responseBuilder")
public abstract class BaseResponse {
    @Expose
    @SerializedName("_metadata")
    protected MetaData metadata;
    @Expose
    protected String error;
    @Expose
    @SerializedName("error_description")
    protected String errorDescription;

    public boolean isUnAuthorized() {
        logCheck();
        return this.error.equals("unauthorized") && (this.errorDescription.equals("No authorization data found")
                || errorDescription.equals("You have not provided any credentials or they are invalid"))
                || errorDescription.equals("Invalid mac value");
    }

    public boolean isForbidden() {
        logCheck();
        return (this.error.equals("forbidden") || this.error.equals("no_rights"))
                && (this.errorDescription.equals("Access Denied.") ||
                this.errorDescription.equals("Client has no read permission on account"));
    }


    public boolean isResourceNotFound() {
        logCheck();
        return (this.error.equals("not_found") ||
                this.error.equals("invalid_state"))
                && (this.errorDescription.contains("not found")
                || this.errorDescription.contains("not resolve entity")
                || this.errorDescription.contains("does not exist.")
                || this.errorDescription.contains("Not found resolver")
                || this.errorDescription.contains("No currencies found")
                || this.errorDescription.contains("Not found exchange rate for")
                || this.errorDescription.contains("not find"));

    }

    public boolean isUserNotFound() {
        logCheck();
        return this.error.equals("invalid_parameters") && this.errorDescription.equals("User not found");
    }

    public boolean isBadRequest() {
        logCheck();
        return (this.error.equals("invalid_parameters") ||
                this.error.contains("error") ||
                this.error.equals("invalid_request")) &&
                (this.errorDescription.contains("only for")
                        || this.errorDescription.contains("cannot be")
                        || this.errorDescription.contains("is required")
                        || this.errorDescription.contains("must be specified")
                        || this.errorDescription.contains("already")
                        || this.errorDescription.contains("parameter is missing")
                        || this.errorDescription.contains("not acceptable")
                        || this.errorDescription.contains("Invalid")
                        || this.errorDescription.contains("Unable to")
                        || this.errorDescription.contains("Unsupported")
                        || this.errorDescription.contains("is invalid")
                        || this.errorDescription.contains("is wrong type value")
                        || this.errorDescription.contains("does not exist")
                        || this.errorDescription.contains("do not match")
                        || this.errorDescription.contains("Could not find")
                        || this.errorDescription.contains("not found")
                        || this.errorDescription.contains("Can not parse")
                        || this.errorDescription.contains("No statement")
                        || this.errorDescription.contains("should be")
                        || this.errorDescription.contains("Missing required key")
                        || this.errorDescription.contains("Request content is invalid"));
    }

    public boolean isConflict() {
        return this.error.equals("invalid_state") && (this.errorDescription.contains("cannot be made"));
    }

    public boolean isInternalServerError() {
        return this.error.equals("internal_server_error") && this.errorDescription.contains("Unexpected internal system error");
    }

    private void logCheck() {
        log.info("Assertion that request is bad request:" +
                "\nError message: " + this.error +
                "\nError description: " + this.errorDescription);
    }
}
