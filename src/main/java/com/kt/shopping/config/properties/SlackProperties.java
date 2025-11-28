package com.kt.shopping.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "slack.api")
public record SlackProperties(
    String botToken,
    String logChannel
) {
}
