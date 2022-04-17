package com.clsld.api.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String fromUserId;
    private String message;
    private String username;
    private String toUserId;

}
