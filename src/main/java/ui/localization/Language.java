package ui.localization;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Language {
    @SerializedName("lt")
    LITHUANIAN("lt", "lit", "lt"),
    @SerializedName("en")
    ENGLISH("en", "eng", "en-US");

    private final String urlPart;
    private final String selectValue;
    private final String acceptLanguage;

    @Override
    public String toString() {
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
    }

    public static Language getByUrlPart(String urlPart) {
        return Arrays.stream(Language.values())
                .filter(language -> language.getUrlPart().equals(urlPart))
                .findFirst().orElse(ENGLISH);
    }
}
