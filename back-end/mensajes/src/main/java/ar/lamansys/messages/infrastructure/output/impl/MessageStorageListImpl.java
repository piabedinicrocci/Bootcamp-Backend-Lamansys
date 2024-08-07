package ar.lamansys.messages.infrastructure.output.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import ar.lamansys.messages.domain.MessageStoredBo;
import ar.lamansys.messages.infrastructure.output.MessageStorage;


public class MessageStorageListImpl implements MessageStorage {

    private final List<MessageStoredBo> messages = new ArrayList<>();

    @Override
    public void save(MessageStoredBo message) {
        messages.add(message);
    }

    @Override
    public Stream<MessageStoredBo> findByContact(String userId) {
        return messages.stream()
                .filter(ownerIdOrTargetIdIs(userId));
    }

    private static Predicate<MessageStoredBo> ownerIdOrTargetIdIs(String userId) {
        return m -> userId.equals(m.getOwnerId()) || userId.equals(m.getTargetId());
    }

    @Override
    public Stream<MessageStoredBo> findBetween(
        String oneContact,
        String otherContact
    ) {
        return messages.stream()
                .filter(isBetween(oneContact, otherContact));
    }

    private static Predicate<MessageStoredBo> isBetween(
            String sessionUserId,
            String contactId
    ) {
        return sentFromTo(sessionUserId, contactId)
                .or(sentFromTo(contactId, sessionUserId));
    }

    private static Predicate<MessageStoredBo> sentFromTo(
            String ownerId,
            String targetId
    ) {
        return message ->
                message.getOwnerId().equals(ownerId)
                && message.getTargetId().equals(targetId);
    }
}
