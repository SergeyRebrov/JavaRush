package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double value ) {
        for (Student student : students)
        {
            if (student.getAverageGrade() == value)
                return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student studentWithMaxAverageGrade = null;
        for (Student student : students)
        {
            if (studentWithMaxAverageGrade == null)
                studentWithMaxAverageGrade = student;
            else if (student.getAverageGrade() > studentWithMaxAverageGrade.getAverageGrade())
                    studentWithMaxAverageGrade = student;
        }
        return studentWithMaxAverageGrade;
    }

    public Student getStudentWithMinAverageGrade() {
        Student studentWithMinAverageGrade = null;
        for (Student student : students)
        {
            if (studentWithMinAverageGrade == null)
                studentWithMinAverageGrade = student;
            else if (student.getAverageGrade() < studentWithMinAverageGrade.getAverageGrade())
                studentWithMinAverageGrade = student;
        }
        return studentWithMinAverageGrade;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}
