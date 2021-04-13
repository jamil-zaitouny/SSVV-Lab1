package ssvv.example;

import org.junit.BeforeClass;
import org.junit.Test;
import ssvv.domain.Nota;
import ssvv.domain.Student;
import ssvv.domain.Tema;
import ssvv.repository.NotaXMLRepo;
import ssvv.repository.StudentXMLRepo;
import ssvv.repository.TemaXMLRepo;
import ssvv.service.Service;
import ssvv.validation.NotaValidator;
import ssvv.validation.StudentValidator;
import ssvv.validation.TemaValidator;

import java.time.LocalDate;
import static org.junit.Assert.fail;

public class BigBangIntegrationTest {
    static Service service;
    @BeforeClass
    public static void start(){
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
    public void testAddStudent(){
        try{
            String studentID = "1";
            String studentName = "Vladimir";
            int group = 1;
            String email = "vlad@gmail.com";
            Student student = new Student(studentID, studentName, group, email);
            service.addStudent(student);
        }catch(Exception exception){
            fail();
        }
    }

    @Test
    public void testAddHomework(){
        try{
            Tema tema = new Tema("1", "asdasd", 1, 1);
            service.addTema(tema);
        }catch(Exception exception){
            fail();
        }

    }
    @Test
    public void testAddGrade(){
        try{
            String studentID = "1";
            String homeworkID = "1";
            String gradeId = "1";

            double grade = 9;
            LocalDate date = LocalDate.now();

            Nota nota = new Nota(gradeId, studentID, homeworkID, grade, date);
            service.addNota(nota, "You did good my child!!");
        }catch(Exception exception)
        {
            exception.printStackTrace();
            fail();
        }

    }
    @Test
    public void addStudent(){
        testAddStudent();
        testAddHomework();
        testAddGrade();
    }

}
