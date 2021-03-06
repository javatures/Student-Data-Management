package student.management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates and manages records of student objects.
 * 
 * @author Shane Daniel
 * @author shane.daniel@revature.net
 */
public class Manage implements StudentDao {
    private Connection con;
    private PreparedStatement ps;

    /**
     * Creates a Manage object with a specified connection to PostgreSQL database.
     * 
     * @param con Connection Object
     */
    public Manage(Connection con) {
        this.con = con;
    }

    // Creates a command-line interface GUI for the user.
    public void menu() {
        System.out.println("\n**************** Student Management System ****************");
        System.out.println("***********************************************************");
        System.out.println("                 1.) Add Student");
        System.out.println("                 2.) Remove Student");
        System.out.println("                 3.) Modify Student");
        System.out.println("                 4.) Student Inquiry");
        System.out.println("                 5.) View All Students");
        System.out.println("                 6.) Exit");
        System.out.println("***********************************************************");
        System.out.print("       Selection --> ");
    }

    /**
     * This method is used to insert a Student object to a database.
     * 
     * @param student A instance of the Student class.
     */
    @Override
    public int addStudent(Student student) {
        int success = 0;
        try {
            ps = con.prepareStatement(
                    "INSERT INTO students(first_name, last_name, email, gender, age, classification) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, student.getFname());
            ps.setString(2, student.getLname());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getGender());
            ps.setInt(5, student.getAge());
            ps.setString(6, student.getClassification());
            success = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * This method is used to upate infor on an existing student in the database.
     * 
     * @param list A list of Strings.
     */
    @Override
    public int updateStudent(List<String> list) {
        int success = 0;
        try {
            ps = con.prepareStatement(
                    "UPDATE students SET first_name = ?, last_name = ?, email = ?, gender = ?, age = ?, classification = ? WHERE student_id = ?");
            ps.setString(1, list.get(1));
            ps.setString(2, list.get(2));
            ps.setString(3, list.get(3));
            ps.setString(4, list.get(4));
            ps.setInt(5, Integer.parseInt(list.get(5)));
            ps.setString(6, list.get(6));
            ps.setInt(7, Integer.parseInt(list.get(0)));
            success = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Remove a student from the records data base.
     * 
     * @param id A int containing the student's id.
     */
    @Override
    public int removeStudentById(int id) {
        int success = 0;
        try {
            ps = con.prepareStatement("DELETE FROM students WHERE student_id = ?");
            ps.setInt(1, id);
            success = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(success);
        return success;
    }

    // Displays all rows and data of the records data base.
    public void viewAllRecords() {
        List<Student> students = new ArrayList<>();
        System.out.println(
                "\n******************************************* Student Records ***********************************************************");
        System.out.println(
                "***********************************************************************************************************************");
        System.out.println("ID\tFirst Name\tLast Name\tEmail\t\t\t\tGender\t\tAge\t\tClassification\n");
        students = getAllStudents();
        students.forEach(student -> {
            System.out.print(student.getId());
            System.out.print("\t" + student.getFname());
            System.out.print("\t\t" + student.getLname());
            System.out.print("\t\t" + student.getEmail());
            System.out.print("\t" + student.getGender());
            System.out.print("\t\t" + student.getAge());
            System.out.println("\t\t" + student.getClassification() + "\n");
        });
        System.out.println(
                "***********************************************************************************************************************\n\n");
    }

    // This is a helper method to display all rows and elements of the record data
    // base.
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM students");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student temp = new Student(rs.getInt("student_id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("gender"), rs.getInt("age"), rs.getString("email"),
                        rs.getString("classification"));
                students.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * Prints out information about a specific studet.
     * 
     * @param name A String containing the student's name to inquiry the records
     *             data base.
     */
    @Override
    public ResultSet getStudentByName(String name) {
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM students WHERE first_name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Name: " + rs.getString(2) + " " + rs.getString(3));
                System.out.println("Email: " + rs.getString(4));
                System.out.println("Gender: " + rs.getString(5));
                System.out.println("Age: " + rs.getInt(6) + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // Exit the application
    public void exit() throws SQLException, IOException, InterruptedException {
        System.out.println("Exiting...");

        // Stop the PostgreSQL server.
        // ****************************************/
        String cmd = "docker stop quizzical_chatterjee";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        // ****************************************/

        con.close(); // Close the connection to the PostgreSQL Server.
        System.exit(0);
    }
}
