package ui.localization;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class PageContent<T> {
    @Expose
    protected List<T> contents;
}
