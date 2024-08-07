package ar.lamansys.messages.domain;

import lombok.Value;

@Value
public class MessageStoredBo {
    String ownerId;
    String targetId;
    String text;
}


