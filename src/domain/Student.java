package domain;

import repository.HasID;

public class Student implements HasID<String> {
    private String idStudent;
    private String nume;
    private int grupa;
    private String email;

    /**
     * Class Constructor
     * @param idStudent - the id of the student
     * @param nume - the name of the student
     * @param grupa - the group of the student
     * @param email - the email of the student
     */
    public Student(String idStudent, String nume, int grupa, String email) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.email = email;
    }

    /**
     * @return the id of the student
     */
    public String getID() {
        return idStudent;
    }

    /**
     * modifica the id of the student
     * @param ID - new id for student
     */
    public void setID(String ID) {
        this.idStudent = ID;
    }

    /**
     * @return the name of the student
     */
    public String getNume() {
        return nume;
    }

    /**
     * modify the name of the student
     * @param nume - new name
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * @return the student group
     */
    public int getGrupa() {
        return grupa;
    }

    /**
     * modify the student group
     * @param grupa - the new group
     */
    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    /**
     * @return the email of the student
     */
    public String getEmail() {
        return email;
    }

    /**
     * modify the email of the student
     * @param email - new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return an object of type Student as a string
     */
    @Override
    public String toString() {
        return idStudent + "," + nume + "," + grupa + "," + email;
    }
}
