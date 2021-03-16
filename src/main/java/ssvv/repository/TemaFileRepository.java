package ssvv.repository;

import ssvv.domain.Tema;

public class TemaFileRepository extends AbstractFileRepository<String, Tema> {

    /**
     * Class constructor
     * @param filename - The name of the file
     */
    public TemaFileRepository(String filename){
        super(filename);
    }

    /**
     * Extract information about the grade from a
     * @param linie - The string that contains the data about the homework
     * @return the homework
     */
    @Override
    public Tema extractEntity(String linie) {
        String[] cuvinte = linie.split(",");
        return new Tema(cuvinte[0], cuvinte[1], Integer.parseInt(cuvinte[2]), Integer.parseInt(cuvinte[3]));
    }
}
