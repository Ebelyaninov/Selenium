package db;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataBasePath {
    private DataBaseType dataBaseType;
    private String dataBaseHost;
    private String dataBasePort;
    private String dataBaseUser;
    private String dataBasePassword;
}
