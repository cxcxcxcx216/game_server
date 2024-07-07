package com.game;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        ConfigurableApplicationContext context = event.getApplicationContext();
//        ShoutDownHook shoutDownHook = new ShoutDownHook();
//        shoutDownHook.attachShutDownHook();
    }
}

