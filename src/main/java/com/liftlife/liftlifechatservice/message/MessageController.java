package com.liftlife.liftlifechatservice.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/prototype")
public class MessageController {
    //TODO: add service layer
    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/all")
    public Flux<Message> findAllMessages() {
        //TODO: closing-reopening stream
        return messageRepository.getSink().asFlux();
    }

    @PostMapping("/insert")
    public void insertMessages(@RequestBody Map<String, Message> messages) {
        Set<String> keys = messages.keySet();
        for (String key : keys) {
            messageRepository.insert(key, messages.get(key));
        }
    }
}
