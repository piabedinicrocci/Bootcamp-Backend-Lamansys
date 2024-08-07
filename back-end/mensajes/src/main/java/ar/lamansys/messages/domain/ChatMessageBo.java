package ar.lamansys.messages.domain;

import lombok.Value;

@Value
public class ChatMessageBo {
    boolean isSent;
    String text;
}
