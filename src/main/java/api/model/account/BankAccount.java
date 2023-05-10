package api.model.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class BankAccount {
    @Expose
    private int id;

    @Expose
    private String number;

    @Expose
    @SerializedName("owner_id")
    private int ownerId;

    @Expose
    @SerializedName("created_at")
    private int createdAt;

    @Expose
    private boolean active;

    @Expose
    private boolean closed;

    @Expose
    private String type;

    @Expose
    @SerializedName("iban_list")
    private List<String> ibanList;

    @Expose
    private Flags flags;

    @Override
    public String toString() {
        return "Items{" +
                "number=" + number + ",\n" +
                "owner id=" + ownerId + ",\n" +
                "created at=" + createdAt + ",\n" +
                "active=" + active + ",\n" +
                "close = " + closed + ",\n" +
                "type=" + type + ",\n" +
                "iban list=" + ibanList + ",\n" +
                "flags=" + flags + ",\n" +
                '}';
    }
}