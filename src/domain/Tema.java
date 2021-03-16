package domain;


import repository.HasID;


public class Tema implements HasID<String> {
    private String nrTema;
    private String descriere;
    private int deadline;
    private int primire;

    /**
     *
     * @param nrTema - the number of the homework
     * @param descriere - the description of the homework
     * @param deadline - the deadline of the homework
     * @param primire - Week of receiving the homework assignment
     * Class Constructor
     */
    public Tema(String nrTema, String descriere, int deadline, int primire) {
        this.nrTema = nrTema;
        this.descriere = descriere;
        this.deadline = deadline;
        this.primire = primire;
    }

    /**
     * @return get the description of a homework
     */
    public String getDescriere() {
        return descriere;
    }

    /**
     * modify the description of a homework
     * @param descriere - new description
     */
    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    /**
     * @return the deadline of the homework
     */
    public int getDeadline() {
        return deadline;
    }

    /**
     * modify the grading deadline
     */
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    /**
     * @return The week for which the homework was given
     */
    public int getPrimire() {
        return primire;
    }

    /**
     * modifica modifies the week for which a homework was given
     * @param primire - new week
     */
    public void setPrimire(int primire) {
        this.primire = primire;
    }

    /**
     * @return the ID of a homework
     */
    @Override
    public String getID() {
        return this.nrTema;
    }

    /**
     * Change the ID of a homework
     * @param nrTema - new number for homework
     */
    @Override
    public void setID(String nrTema) {
        this.nrTema = nrTema;
    }

    /**
     * @return Object Tema as a string
     */
    @Override
    public String toString() {
        return nrTema + "," + descriere + "," + deadline + "," + primire;
    }
}
