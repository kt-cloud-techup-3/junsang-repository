package com.kt.shopping.integration.slack;

import com.kt.shopping.config.properties.SlackProperties;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SlackConfiguration {

    private final SlackProperties slackProperties;

    @Bean
    public MethodsClient methodsClient() {
        return Slack.getInstance().methods(slackProperties.botToken());

    }
}
