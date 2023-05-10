package authorization.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PropertiesItem {

    @Expose
    private String type;

    @Expose
    @SerializedName("app_translation")
    private String appTranslation;

    @Expose
    @SerializedName("app_translation_parameters")
    private String appTranslationParameters;

    @Expose
    @SerializedName("sms_message")
    private String smsMessage;

    @Expose
    @SerializedName("charged_user_id")
    private String chargedUserId;
}