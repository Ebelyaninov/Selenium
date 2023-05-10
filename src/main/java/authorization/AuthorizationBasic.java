package authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthorizationBasic {
    BASIC_AUTH_MOKEJIMAI_API("mokejimai", "pass"),
    BASIC_AUTH_BROKEN("testqa", "testqa");

    public final String userName;
    public final String password;
}