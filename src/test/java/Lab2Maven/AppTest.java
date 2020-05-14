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
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Service service;
    @Before
    public void setUp() throws Exception {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        this.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

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
        assertNull(this.service.addStudent(student));
        //this.service.deleteStudent("10");
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFail(){
        Student student = new Student("","name",936,"email");
        this.service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFailIdNull(){
        Student student = new Student(null,"name",936,"email");
        this.service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFailNameEmpty(){
        Student student = new Student("10","",936,"email");
        this.service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFailNameNull(){
        Student student = new Student("10",null,936,"email");
        this.service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFailGroupEmpty(){
        Student student = new Student("10","name",-1,"email");
        this.service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFailGroupMin(){
        Student student = new Student("10",null,Integer.MIN_VALUE,"email");
        this.service.addStudent(student);
    }

    @Test
    public void testAddStudentSuccessGroupMinMinus(){
        Student student = new Student("10","name",Integer.MIN_VALUE-1,"email");
        assertNull(this.service.addStudent(student));
        this.service.deleteStudent("10");
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFailGroupMinPlus(){
        Student student = new Student("10",null,Integer.MIN_VALUE+1,"email");
        this.service.addStudent(student);
    }

    @Test
    public void testAddStudentSuccessGroupZero(){
        Student student = new Student("10","name",0,"email");
        assertNull(this.service.addStudent(student));
        this.service.deleteStudent("10");
    }

    @Test
    public void testAddStudentSuccessGroupMax(){
        Student student = new Student("10","name",Integer.MAX_VALUE,"email");
        assertNull(this.service.addStudent(student));
        this.service.deleteStudent("10");
    }

    @Test
    public void testAddStudentSuccessGroupMaxMinus(){
        Student student = new Student("10","name",Integer.MAX_VALUE-1,"email");
        assertNull(this.service.addStudent(student));
        this.service.deleteStudent("10");
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFailGroupMaxPlus(){
        Student student = new Student("10",null,Integer.MAX_VALUE+1,"email");
        this.service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFailGroupEmailNull(){
        Student student = new Student("10",null,Integer.MIN_VALUE+1,null);
        this.service.addStudent(student);
    }

    @Test(expected = ValidationException.class)
    public void testAddStudentFailGroupEmailEmpty(){
        Student student = new Student("10",null,Integer.MIN_VALUE+1,"");
        this.service.addStudent(student);
    }







    //----------------------------------------------ADD TEMA------------------------------------------------------------
    @Test
    public void testAddTemaSuccess(){
        Tema tema = new Tema("20", "desc", 7, 6);
        assertNull(this.service.addTema(tema));
        //this.service.deleteTema("20");
    }

    @Test
    public void testAddTemaFailure(){
        Tema tema = new Tema("20", "desc", 7, 6);
        assertSame(this.service.addTema(tema), tema);
        this.service.deleteTema("20");
    }

    @Test(expected = ValidationException.class)
    public void testAddTemaFailureIdNull(){
        Tema tema = new Tema(null, "desc", 7, 6);
        this.service.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void testAddTemaFailureIdEmpty(){
        Tema tema = new Tema("", "desc", 7, 6);
        this.service.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void testAddTemaFailureDescNull(){
        Tema tema = new Tema("20", null, 7, 6);
        this.service.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void testAddTemaFailureDescEmpty(){
        Tema tema = new Tema("20", "", 7, 6);
        this.service.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void testAddTemaFailureDeadlineSmall(){
        Tema tema = new Tema("20", "desc", 0, 6);
        this.service.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void testAddTemaFailureDeadlineBig(){
        Tema tema = new Tema("20", "desc", 15, 6);
        this.service.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void testAddTemaFailureDeliverySmall(){
        Tema tema = new Tema("20", "desc", 7, 0);
        this.service.addTema(tema);
    }

    @Test(expected = ValidationException.class)
    public void testAddTemaFailureDeliveryBig(){
        Tema tema = new Tema("20", "desc", 7, 15);
        this.service.addTema(tema);
    }

    @Test
    public void testAddGradeSuccess(){
        LocalDate data = LocalDate.of(2018,3,27);
        Nota nota = new Nota("10","1","6",10,data);
        this.service.addNota(nota,"feedback");
    }

    //------------------------------------------------INTEGRATION------------------------------------------------------

    @Test
    public void testAddTemaIntegration(){
        this.testAddStudentSuccess();
        this.testAddTemaSuccess();
    }

    @Test
    public void testAddGradeIntegration(){
        this.testAddTemaIntegration();
        LocalDate data = LocalDate.of(2018,3,27);
        Nota nota = new Nota("10","10","20",10,data);
        this.service.addNota(nota,"feedback");
        this.service.deleteStudent("10");
        this.service.deleteTema("20");
    }

    @Test
    public void cluster(){
        this.testAddStudentSuccess();
        this.testAddTemaSuccess();
        this.testAddGradeSuccess();
    }
}
