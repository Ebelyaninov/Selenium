package ui.localization;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
abstract public class BaseContent {
    @Expose
    protected Language language;
}
