package api.model.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Flags {

    @Expose
    private boolean savings;

    @Expose
    @SerializedName("public")
    private boolean publicity;
}
