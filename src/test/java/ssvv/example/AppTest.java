package ssvv.example;

import org.junit.Test;
import ssvv.domain.Student;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     *
     *
     */
    @Test
    public void testGroupNumberValue(){
        String studentID = "1";
        String studentName = "Vladimir";
        int group = -1;
        String email = "vlad@gmail.com";
        try{
            Student student = new Student(studentID, studentName, group, email);
            fail();
        }catch (Exception exception){
            assertTrue(true);
        }
    }

}
