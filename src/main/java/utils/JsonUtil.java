package utils;

import api.model.BaseListResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import io.restassured.response.Response;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import static com.google.gson.JsonParser.parseString;

@Slf4j
public class JsonUtil {
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    @SneakyThrows
    public static <T> T readJsonObjectFromFile(String pathToFile, Class<T> classType) {
        try (JsonReader reader = new JsonReader(new FileReader(pathToFile))) {
            return GSON.fromJson(reader, classType);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new FileNotFoundException("File with path \"" + pathToFile + "\" not found: " +
                    fileNotFoundException.getLocalizedMessage());
        }
    }

    public static <T> String convertJsonObjectToString(T obj) {
        return GSON.newBuilder().setPrettyPrinting().create().toJson(obj);
    }

    public static JsonElement toJsonTree(Object src) {
        return GSON.toJsonTree(src);
    }

    public static String toJson(Object src) {
        return GSON.toJson(src);
    }

    public static String beautifyString(String jsonString) {
        return GSON.toJson(parseString(jsonString).getAsJsonObject());
    }

    public static <T> BaseListResponse<T> readListFromResponse (Response response){
        String responseString = response.body().asString();
        try {
            Type type = new TypeToken<List<T>>() {}.getType();
            return new BaseListResponse<>(GSON.fromJson(responseString, type));
        } catch (JsonSyntaxException e) {
            Type type = new TypeToken<BaseListResponse<T>>() {}.getType();
            return GSON.fromJson(responseString, type);
        }
    }
}
