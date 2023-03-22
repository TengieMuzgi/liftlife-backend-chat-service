package com.liftlife.liftlifechatservice.message;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    private String sender;
    private String receiver;
    private String contents;
}
