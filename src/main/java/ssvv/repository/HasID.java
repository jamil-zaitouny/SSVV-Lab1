package ssvv.repository;

public interface HasID<ID> {

    /**
     *
     * @return The id of the object
     */
    ID getID();

    /**
     * Modifies the id of an object
     * @param id - a new id
     */
    void setID(ID id);
}