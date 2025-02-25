package org.ilvendev.exceptions.resource_exceptions;

public class ResourceNotFoundException extends RuntimeException {
  private final String resourceType;
  private final String identifier;

  public ResourceNotFoundException(String resourceType, String identifier) {
    super(String.format("%s with identifier '%s' not found", resourceType, identifier));
    this.resourceType = resourceType;
    this.identifier = identifier;
  }
}
