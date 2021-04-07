package student.management;

import java.sql.ResultSet;
import java.util.List;

/**
 * This interface abstracts the data access logic for the student table.
 * 
 * @author Shane Daniel
 * @author shane.daniel@revature.net
 */
public interface StudentDao {
    public List<Student> getAllStudents();//

    public int addStudent(Student s);//

    public ResultSet getStudentByName(String name);//

    public int removeStudentById(int id);//

    public int updateStudent(List<String> list);
}
