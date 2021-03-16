package service;

import curent.Curent;
import domain.Nota;
import domain.Student;
import domain.Tema;

import repository.*;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Clasa Service
 */
public class Service {
    //private StudentFileRepository studentFileRepository;
    private StudentXMLRepo studentFileRepository;
    private StudentValidator studentValidator;
    //private TemaFileRepository temaFileRepository;
    private TemaXMLRepo temaFileRepository;
    private TemaValidator temaValidator;
    //private NotaFileRepository notaFileRepository;
    private NotaXMLRepo notaFileRepository;
    private NotaValidator notaValidator;

    /**
     * Class Constructor
     * @param studentFileRepository - repository student
     * @param studentValidator - validator student
     * @param temaFileRepository - repository tema
     * @param temaValidator - validator tema
     * @param notaFileRepository - repository nota
     * @param notaValidator - validator nota
     */
    //public Service(StudentFileRepository studentFileRepository, StudentValidator studentValidator, TemaFileRepository temaFileRepository, TemaValidator temaValidator, NotaFileRepository notaFileRepository, NotaValidator notaValidator) {
    public Service(StudentXMLRepo studentFileRepository, StudentValidator studentValidator, TemaXMLRepo temaFileRepository, TemaValidator temaValidator, NotaXMLRepo notaFileRepository, NotaValidator notaValidator) {

        this.studentFileRepository = studentFileRepository;
        this.studentValidator = studentValidator;
        this.temaFileRepository = temaFileRepository;
        this.temaValidator = temaValidator;
        this.notaFileRepository = notaFileRepository;
        this.notaValidator = notaValidator;
    }

    /**
     * Adds a student to memory
     * @param student - Student to be addedd
     * @return null if the student was added successfully, the student itself otherwise
     */
    public Student addStudent(Student student) {
        studentValidator.validate(student);
        return studentFileRepository.save(student);
    }

    /**
     * Delete a student
     * @param id - The id of the student
     * @return The student if the deletion was sucessful, null otherwise
     */
    public Student deleteStudent(String id){
        if(id == null || id.equals("")) {
            throw new ValidationException("Id-ul nu poate fi null!");
        }
        return studentFileRepository.delete(id);
    }

    /**
     * Search for a student by ID
     * @param id - The id of the student
     * @return The student if he exists, or unll null otherwise
     */
    public Student findStudent(String id){
        if(id == null || id.equals("")){
            throw new ValidationException("Id-ul nu poate fi null!");
        }
        return studentFileRepository.findOne(id);
    }

    /**
     * Modifies the student
     * @param student - New student
     * @return The new student if the modification happens, null otherwise
     */
    public Student updateStudent(Student student){
        studentValidator.validate(student);
        return studentFileRepository.update(student);
    }

    /**
     * @return Returns all the students in memory as an iterable
     */
    public Iterable<Student> getAllStudenti(){
        return studentFileRepository.findAll();
    }

    /**
     * Add a new homework
     * @param tema  - The homework to be added
     * @return Null if the homework already exists, the homework otherwise
     */
    public Tema addTema(Tema tema){
        temaValidator.validate(tema);
        return temaFileRepository.save(tema);
    }

    /**
     * Delete a homework
     * @param nrTema - The id of hte homework
     * @return The homework if it was deleted, null otherwise
     */
    public Tema deleteTema(String nrTema){
        if(nrTema == null || nrTema.equals("")) {
            throw new ValidationException("Id-ul nu poate fi null!");
        }
        return temaFileRepository.delete(nrTema);
    }

    /**
     * Find a homework
     * @param id - The id of the homework
     * @return The homework or null if it doesn't exist
     */
    public Tema findTema(String id){
        if(id == null || id.equals("")){
            throw new ValidationException("Id-ul nu poate fi null!");
        }return temaFileRepository.findOne(id);
    }

    /**
     * Modify a homework
     * @param tema -New homework
     * @return The homework if a modification was done sucessfully, null otherwise
     */
    public Tema updateTema(Tema tema){
        temaValidator.validate(tema);
        return temaFileRepository.update(tema);
    }

    /**
     * @return All the assignments from memory
     */
    public Iterable<Tema> getAllTeme(){
        return temaFileRepository.findAll();
    }

    /**
     * Add a grade
     * @param nota - nota
     * @param feedback - feedback-ul notei
     * @return null daca nota a fost adaugata sau nota daca aceasta exista deja
     */
    public double addNota(Nota nota, String feedback){
        notaValidator.validate(nota);
        Student student = studentFileRepository.findOne(nota.getIdStudent());
        Tema tema = temaFileRepository.findOne(nota.getIdTema());
        int predare = calculeazaSPredare(nota.getData());
        if(predare != tema.getDeadline()){
            if (predare-tema.getDeadline() == 1){
                nota.setNota(nota.getNota()-2.5);
            }
            else{
                throw new ValidationException("Studentul nu mai poate preda aceasta tema!");
            }
        }
        notaFileRepository.save(nota);
        String filename = "fisiere/" + student.getNume() + ".txt";
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true))){
            bufferedWriter.write("\nTema: " + tema.getID());
            bufferedWriter.write("\nNota: " + nota.getNota());
            bufferedWriter.write("\nPredata in saptamana: " + predare);
            bufferedWriter.write("\nDeadline: " + tema.getDeadline());
            bufferedWriter.write("\nFeedback: " +feedback);
            bufferedWriter.newLine();
        } catch (IOException exception){
            throw new ValidationException(exception.getMessage());
        }
        return nota.getNota();
    }

    /**
     * Sterge o nota
     * @param id - id-ul notei
     * @return nota daca aceasta a fost stearsa sau null daca nota nu exista
     */
    public Nota deleteNota(String id){
        if(id == null || id.equals("")) {
            throw new ValidationException("Id-ul nu poate fi null!");
        }
        return notaFileRepository.delete(id);
    }

    /**
     * Search for a great
     * @param id - id-ul notei
     * @return nota sau null daca aceasta nu exista
     */
    public Nota findGrade(String id){
        if(id == null || id.equals("")){
            throw new ValidationException("Id-ul nu poate fi null!");
        }
        return notaFileRepository.findOne(id);
    }

    /**
     * @return toate notele
     */
    public Iterable<Nota> getAllNote(){
        return notaFileRepository.findAll();
    }

    /**
     * Prelungeste Deadline of an assignment
     * @param nrTema - The id of the homework
     * @param deadline - The new deadline
     */
    public void prelungireDeadline(String nrTema, int deadline){
        int diff= Curent.getCurrentWeek();
        Tema tema = temaFileRepository.findOne(nrTema);
        if(tema == null){
            throw new ValidationException("Tema inexistenta!");
        }
        if(tema.getDeadline() >= diff) {
            tema.setDeadline(deadline);
            temaFileRepository.writeToFile();
        }
        else{
            throw new ValidationException("Nu se mai poate prelungi deadline-ul!");
        }
    }

    /**
     * Calculate the week of delivery
     * @param predare - the date of teaching a topic
     * @return the week in which the topic was taught
     */
    private int calculeazaSPredare(LocalDate predare) {
        LocalDate startDate = Curent.getStartDate();
        long days = DAYS.between(startDate, predare);
        double saptamanaPredare = Math.ceil((double)days/7);
        return (int)saptamanaPredare;
    }
}
