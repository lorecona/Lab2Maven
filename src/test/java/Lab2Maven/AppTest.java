package Lab2Maven;

import Lab2Maven.domain.Nota;
import Lab2Maven.domain.Student;
import Lab2Maven.domain.Tema;
import Lab2Maven.repository.NotaXMLRepo;
import Lab2Maven.repository.StudentXMLRepo;
import Lab2Maven.repository.TemaXMLRepo;
import Lab2Maven.service.Service;
import Lab2Maven.validation.NotaValidator;
import Lab2Maven.validation.StudentValidator;
import Lab2Maven.validation.TemaValidator;
import Lab2Maven.validation.ValidationException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAddStudentSuccess(){
        Student student = new Student("10","name",936,"email");

        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        assertNull(service.addStudent(student));
        service.deleteStudent("10");
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFail(){
        Student student = new Student("","name",936,"email");

        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        service.addStudent(student);
    }
}
