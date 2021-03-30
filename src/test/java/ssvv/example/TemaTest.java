package ssvv.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ssvv.domain.Student;
import ssvv.domain.Tema;
import ssvv.repository.NotaXMLRepo;
import ssvv.repository.StudentXMLRepo;
import ssvv.repository.TemaXMLRepo;
import ssvv.service.Service;
import ssvv.validation.NotaValidator;
import ssvv.validation.StudentValidator;
import ssvv.validation.TemaValidator;
import ssvv.validation.ValidationException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TemaTest {
	private Service service;
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
	public void testIdNull(){
		try{
			Tema tema = new Tema(null, "asdasd", 1, 1);
			service.addTema(tema);
			fail();

		}catch (Exception exception){
			assertTrue(true);
		}
	}
	@Test
	public void testIdEmpty(){
		try{
			Tema tema = new Tema("", "asdasd", 1, 1);
			service.addTema(tema);
			fail();

		}catch (Exception exception){
			assertTrue(true);
		}
	}

	@Test
	public void testDescriptionEmpty(){
		Tema tema = new Tema("asdas", "", 1, 1);
		try{
			service.addTema(tema);
			Assert.fail();
		}catch (ValidationException exception){
			Assert.assertTrue(true);
		}
	}
	@Test
	public void testHomeworkDeadlineOutOfRangeMoreThan14(){
		Tema tema = new Tema("asdas", "asdasdad", 15, 1);
		try{
			service.addTema(tema);
			Assert.fail();
		}catch (ValidationException exception){
			Assert.assertTrue(true);
		}
	}
	@Test
	public void testHomeworkDeadlineOutOfRangeLessThan1(){
		Tema tema = new Tema("asdas", "asdasdad", 0, 1);
		try{
			service.addTema(tema);
			Assert.fail();
		}catch (ValidationException exception){
			Assert.assertTrue(true);
		}
	}
	@Test
	public void testDeadlineOutOfPrimireMoreThan14(){
		Tema tema = new Tema("asdas", "asdasdad", 12, 15);
		try{
			service.addTema(tema);
			Assert.fail();
		}catch (ValidationException exception){
			Assert.assertTrue(true);
		}
	}
	@Test
	public void testDeadlineOutOfPrimireLessThan1(){
		Tema tema = new Tema("asdas", "asdasdad", 12, 0);
		try{
			service.addTema(tema);
			Assert.fail();
		}catch (ValidationException exception){
			Assert.assertTrue(true);
		}
	}
	@Test
	public void testIdNotNull(){
		try{
			Tema tema = new Tema("1231", "asdasd", 1, 1);
			service.addTema(tema);
			assertTrue(true);

		}catch (Exception exception){
			fail();
		}
	}
	@Test
	public void testDescriptionNotNull(){
		Tema tema = new Tema("asdas", "adsfsda", 1, 1);
		try{
			service.addTema(tema);
			Assert.assertTrue(true);
		}catch (ValidationException exception){
			Assert.fail();
		}
	}
	@Test
	public void testDeadlineInRange(){
		Tema tema = new Tema("asdas", "asdasdad", 13, 1);
		try{
			service.addTema(tema);
			Assert.assertTrue(true);
		}catch (ValidationException exception){
			Assert.fail();
		}
	}
	@Test
	public void testDeadlineInPrimire(){
		Tema tema = new Tema("asdas", "asdasdad", 12, 12);
		try{
			service.addTema(tema);
			Assert.assertTrue(true);
		}catch (ValidationException exception){
			Assert.fail();
		}
	}

	@Test
	public void testTemaNull(){
		try{
			service.addTema(null);
			fail();
		}catch (ValidationException exception){
			fail();
		}catch(NullPointerException excpetion){
			Assert.assertTrue(true);
		}
	}
	@Test
	public void testTemaNotNull(){
		try{
			service.addTema(new Tema("12312,", "123123", 1, 1));
			Assert.assertTrue(true);
		}catch (ValidationException exception){
			fail();
		}
	}
}
