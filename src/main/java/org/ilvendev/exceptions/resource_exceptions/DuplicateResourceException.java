package org.ilvendev.exceptions.resource_exceptions;

public class DuplicateResourceException extends RuntimeException {
    private final String resourceType;
    private final String identifier;

    public DuplicateResourceException(String resourceType, String identifier) {
        super(String.format("%s with identifier: %s already exists", resourceType, identifier));
        this.resourceType = resourceType;
        this.identifier = identifier;
    }
}
