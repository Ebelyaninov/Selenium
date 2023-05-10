package authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserAuthType {
    USER_ID_BASIC("user_id_basic"),
    USER_ID_FULLY_AUTHENTICATED("user_id_fully_authenticated");

    private final String authenticationType;
}