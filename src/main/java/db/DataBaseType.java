package db;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataBaseType {
    MARIADB("jdbc:mariadb://%s:%s?allowMultiQueries=true"),
    POSTGRE_SQL("jdbc:postgresql://%s:%s/app"),
    MYSQL("jdbc:mysql://%s:%s?allowMultiQueries=true");

    private String dataBasePathTemplate;
}
