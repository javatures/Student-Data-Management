package student.management;

import java.util.List;

/**
 * This interface abstracts the data access logic for the student table.
 * 
 * @author Shane Daniel
 * @author shane.daniel@revature.net
 */
public interface StudentDao {
    public List<Student> getAllStudents();//

    public void addStudent(Student s);//

    public void getStudentByName(String name);//

    public void removeStudentById(int id);//

    public void updateStudent(List<String> list);
}
