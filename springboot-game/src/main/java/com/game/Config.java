package com.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public LogicServer logicServer() {
        LogicServer server = new LogicServer();
        server.run();
        return server;
    }
}
