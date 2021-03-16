package validation;

public interface Validator<E> {
    /**
     * Validate an entity
     * @param entity - The entity to be validated
     * @throws ValidationException If the entity is valid
     */
    void validate(E entity) throws ValidationException;
}