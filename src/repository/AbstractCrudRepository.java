package repository;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCrudRepository <ID, E extends HasID<ID>> implements CrudRepository<ID, E> {
    private Map<ID, E> elemente;


    /**
     * Class constructor
     */
    AbstractCrudRepository(){
        this.elemente = new HashMap<>();
    }

    /**
     *
     * @param id -the id of the entity to be returned
     * id must not be null
     * @return Object that has the ID otherwise return null
     */
    @Override
    public E findOne(ID id) {
        return this.elemente.get(id);
    }

    /**
     *
     * @return all values of an object
     */
    @Override
    public Iterable<E> findAll() {
        return this.elemente.values();
    }

    /**
     * Save an object in memory
     * @param entity - the object that you want to save
     * entity must be not null
     * @return Null if the object saves properly, the object itself if everything is fine
     */
    @Override
    public E save(E entity) {
        /*
        for(ID id: elemente.keySet()){
            if(id == entity.getID()){
                return elemente.get(id);
            }
        }
        */
        E el = this.findOne(entity.getID());
        if (el==null){
            this.elemente.put(entity.getID(), entity);
            return null;
        }
        else return entity;

    }

    /**
     * Deletes an object from the in-memory repository
     * @param id - the id of the object
     * id must be not null
     * @return the deleted object
     */
    @Override
    public E delete(ID id) {
        return this.elemente.remove(id);
    }

    /**
     * modifies an object
     * @param entity - new object
     * entity must not be null
     * @return Null if the object exists in memory, the object itself if it doesn't exist
     */
    @Override
    public E update(E entity) {
        if(this.elemente.get(entity.getID()) == null){
            return entity;
        }
        this.elemente.replace(entity.getID(), entity);
        return null;
    }
}
