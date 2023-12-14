package com.bol.mancala.base.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "game.config")
@Configuration
@Getter
@Setter
public class ApplicationProperties {
    private int playerCountLimit;
    private int pitCountLimit;
    private int pitCountPerPlayerLimit;
    private int seedCountPerPitLimit;
    private int storeCountPerPlayerLimit;
    private int storeCountLimit;
}
