package com.bol.mancala.base.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * A handy instance of application.yml in java class as bean
 * that can be autowired and eliminate hardcoded @value
 * most of game setting resides here during game setup
 * creation all fields will be checked against this class
 */
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
