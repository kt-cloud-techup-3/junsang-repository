package com.kt.shopping.integration.eventlistener;

import com.kt.shopping.common.support.Message;
import com.kt.shopping.integration.slack.NotifyApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {
    private final NotifyApi notifyApi;

    @EventListener(Message.class)
    public void onMessage(Message message) {
        notifyApi.notify(message.message());
    }
}
