package ssvv.example;

import org.junit.Before;
import org.junit.Test;
import ssvv.domain.Nota;
import ssvv.domain.Student;
import ssvv.domain.Tema;
import ssvv.repository.*;
import ssvv.service.Service;
import ssvv.validation.NotaValidator;
import ssvv.validation.StudentValidator;
import ssvv.validation.TemaValidator;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Service service;
    @Before
    public void init(){
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
        //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
        //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
        //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }


    @Test
    //todo fix
    public void testGroupNumberValueIsTrue(){
        String studentID = "1";
        String studentName = "Vladimir";
        int group = 1;
        String email = "vlad@gmail.com";
        try{
            Student student = new Student(studentID, studentName, group, email);
            service.addStudent(student);
            assertTrue(true);

        }catch (Exception exception){
            fail();
        }
    }
    @Test
    public void testGroupNumberValueIsFalse(){
        String studentID = "1";
        String studentName = "Vladimir";
        int group = -1;
        String email = "vlad@gmail.com";
        try{
            Student student = new Student(studentID, studentName, group, email);
            service.addStudent(student);
            fail();

        }catch (Exception exception){
            assertTrue(true);
        }
    }
}
