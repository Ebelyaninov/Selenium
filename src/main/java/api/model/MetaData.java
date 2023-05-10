package api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MetaData {
    @Expose
    private int total;
    @Expose
    private int limit;
    @Expose
    private int offset;
    @Expose
    @SerializedName("has_next")
    private boolean hasNext;
    @Expose
    @SerializedName("has_previous")
    private boolean hasPrevious;
}
