package com.liftlife.liftlifechatservice.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void findAllMessages() {
    }

    @PostMapping("/insert")
    public void insertMessages(/*@RequestBody Map<String, Message> messages*/){
        messageRepository.insert("third", new Message("Coach", "Client", "third message"));
        messageRepository.insert("forth", new Message("Client", "Coach", "another acquired"));
    }

    @PostMapping("/insert2")
    public void insertMessages2(/*@RequestBody Map<String, Message> messages*/){
        messageRepository.insert("fifth", new Message("Coach1", "Client1", "another message"));
        messageRepository.insert("sixth", new Message("Client1", "Coach1", "goodbye"));
    }

}
