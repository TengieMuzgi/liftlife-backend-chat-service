package com.liftlife.liftlifechatservice;

import com.liftlife.liftlifechatservice.message.Message;
import com.liftlife.liftlifechatservice.message.MessageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LiftLifeChatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiftLifeChatServiceApplication.class, args);
    }


    //init
    @Bean
    public CommandLineRunner run(MessageRepository messageRepository) {
        return args -> {
            messageRepository.insert("first", new Message("Coach", "Client", "first message"));
            messageRepository.insert("second", new Message("Client", "Coach", "message acquired"));
        };
    }

}
