package ar.lamansys.messages.domain;

import lombok.Value;

@Value
public class NewMessageBo {
    String targetId;
    String text;
}
