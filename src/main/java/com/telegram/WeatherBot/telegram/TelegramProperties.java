package com.telegram.WeatherBot.telegram;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegram")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramProperties {

    String url;
    String token;
    String name;

}
