package ssvv.repository;

import ssvv.validation.ValidationException;

import java.io.*;

public abstract class AbstractFileRepository<ID, E extends HasID<ID>> extends AbstractCrudRepository<ID, E> implements FileRepository<ID, E> {

    private String filename;

    /**
     * Class constructor
     * @param filename - the name of the file
     */
    AbstractFileRepository(String filename) {
        this.filename = filename;
        loadFromFile();
    }

    /**
     * Extracts an object from a string
     * @param linie - The string for the object is to be extracted from
     * @return - The object that was parsed
     */
    public abstract E extractEntity(String linie);

    /**
     *  Load the data from the file
     */
    public void loadFromFile(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String linie;
            while ((linie = bufferedReader.readLine()) != null) {
                E entity = extractEntity(linie);
                super.save(entity);
            }
        } catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }
    }

    /**
     * Write an bject into the file
     * @param entity - The object it writes
     */
    public void saveToFile(E entity){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))) {
            bufferedWriter.write(entity.toString());
            bufferedWriter.newLine();
        } catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }
    }

    /**
     * Rewrite the file with all the objects from memory
     */
    public void writeToFile(){
        try (PrintWriter b = new PrintWriter(this.filename)) {
            //Iterable<E> all = super.findAll();
            super.findAll().forEach(e -> b.println(e.toString()));
        }
            catch (IOException exception) {
            throw new ValidationException(exception.getMessage());
        }

    }

    /**
     * Save an object
     * @param entity - The object to be savced
     * @return null if the object saves already, the object itself if it already exists in memory
     */
    @Override
    public E save(E entity) {
        E entity1 = super.save(entity);
        if (entity1 == null) {
            saveToFile(entity);
        }

        return entity1;
    }

    /**
     * Delete an Object
     * @param id - The id of the object
     * @return The object if the deletion happens successfully or the object itself if there's no instance of it in memory
     */
    @Override
    public E delete(ID id) {
        E entity = super.delete(id);
        if(entity != null){
            writeToFile();
        }
        return entity;
    }

    /**
     * Modify an object
     * @param entity - The new object
     * @return null if the object was modified, the object itself if it doesn't exists
     */
    @Override
    public E update(E entity) {
        E entity1 = super.update(entity);
        if(entity == null){
            writeToFile();
        }
        return entity1;
    }
}
