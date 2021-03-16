package ssvv.repository;

/**
 * CRUD operations file repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> - type of entities saved in repository
 */
public interface FileRepository<ID, E extends HasID<ID>> {

    /**
     * Loads the data from the file
     */
    void loadFromFile();

    /**
     * Writes a new object into the file
     * @param entity - The object to be written
     */
    void saveToFile(E entity);

    /**
     * Rewrite the file
     */
    void writeToFile();
}
