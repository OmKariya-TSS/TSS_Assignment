package com.tss.addition;

import com.tss.model.Animal;
import com.tss.model.Dog;
import com.tss.model.Student;
import com.tss.model.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AdditionTest {

    Addition test;
    StudentService studentService;
    Student st = new Student(1,"OM",100);
    @BeforeEach
    void doBefore(){
        test = new Addition();
        studentService = mock(StudentService.class);
    }
    @Test
    void testStudent(){
        when(studentService.getTotalStudents()).thenReturn(7);
        when(studentService.getTotalMarks()).thenReturn(700);
        st.setStudentService(studentService);
        double percentage =st.calculatePercentage();
        assertEquals(100, percentage);
    }
    @Test
    void addTest(){
        int actual = test.add(10,20);
        assertEquals(30,actual);
    }
    @Test
    void testAdditionWithNegativeNumbers() {
        assertEquals(-2, test.add(-5, 3));
    }
    @Test
    void testMultiplication(){
        int actual = test.multiPly(10,0);
        assertEquals(10,actual,"failed");
    }
    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> test.divide(10, 0));
    }
    @Test
    void checkBoolean(){
        assertTrue(test.add(5,2)==7);
    }
    @Test
    void testInstance(){
        Animal DOG = new Dog();
        assertInstanceOf(Dog.class,DOG);
    }
}