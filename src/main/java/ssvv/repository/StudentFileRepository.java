package ssvv.repository;

import ssvv.domain.Student;

public class StudentFileRepository extends AbstractFileRepository<String, Student> {

    /**
     * Class constructor
     * @param filename - The name of the file
     */
    public StudentFileRepository(String filename) {
        super(filename);
    }

    /**
     * Extract information about the student from a string
     * @param linie - The string that contains the data of the student
     * @return studentul
     */
    @Override
    public Student extractEntity(String linie) {
        String[] cuvinte = linie.split(",");
        return new Student(cuvinte[0], cuvinte[1], Integer.parseInt(cuvinte[2]), cuvinte[3]);
    }
}
