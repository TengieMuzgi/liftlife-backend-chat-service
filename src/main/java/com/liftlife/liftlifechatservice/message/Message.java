package com.liftlife.liftlifechatservice.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Message {
    private final String sender;
    private final String receiver;
    private final String contents;
}
