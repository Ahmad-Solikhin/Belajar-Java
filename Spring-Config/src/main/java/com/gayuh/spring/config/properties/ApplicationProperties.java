package com.gayuh.spring.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private Date expireDate;
    private Duration defaultTimeout;
    private String name;
    private Integer version;
    private boolean productionMode;

    //Ini contoh class yang kompleks karena ada filed lainnya
    private DatabaseProperties database;
    private List<Role> defaultRoles;
    private Map<String, Role> roles;

    @Getter
    @Setter
    public static class DatabaseProperties{

        private String password;
        private String username;
        private String database;
        private String url;

        //Ini contoh collection
        private List<String> whiteListTables;
        private Map<String, Integer> maxTableSize;

    }

    @Getter
    @Setter
    public static class Role{
        private String id;
        private String name;
    }

}
