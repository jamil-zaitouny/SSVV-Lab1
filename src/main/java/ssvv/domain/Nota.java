package ssvv.domain;

import ssvv.repository.HasID;

import java.time.LocalDate;

public class Nota implements HasID<String> {
    private String id;
    private String idStudent;
    private String idTema;
    private double nota;
    private LocalDate data;

    /**
     * Class Constructor
     * @param id - Grade id
     * @param idStudent - Student id
     * @param idTema - Homework id
     * @param nota - grade value
     * @param data - date for which the homework has been submitted
     */
    public Nota(String id, String idStudent, String idTema, double nota, LocalDate data){
        this.id = id;
        this.idStudent = idStudent;
        this.idTema = idTema;
        this.nota = nota;
        this.data = data;
    }

    /**
     * @return the id of the student
     */
    public String getIdStudent() {
        return idStudent;
    }

    /**
     * @return id of the homework
     */
    public String getIdTema() {
        return idTema;
    }

    /**
     * @return grade value
     */
    public double getNota() {
        return nota;
    }

    /**
     * modify the value of the grade
     * @param nota - noua valoarea a notei
     */
    public void setNota(double nota) {
        this.nota = nota;
    }

    /**
     * @return The date for the which the grade was given
     */
    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return idStudent + "," + idTema + "," + nota + "," + data;
    }

    /**
     * @return id of the grade
     */
    @Override
    public String getID() {
        return this.id;
    }

    /**
     * modifica id of the grade
     * @param id - the new id
     */
    @Override
    public void setID(String id) {
        this.id = id;
    }

    /**
     * modify the id of the student
     * @param idStudent - the new id
     */
    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    /**
     * modify the id of the homework
     * @param idTema - id of the homework
     */
    public void setIdTema(String idTema) {
        this.idTema = idTema;
    }
}
