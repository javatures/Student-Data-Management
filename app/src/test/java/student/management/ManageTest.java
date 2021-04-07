package student.management;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@TestInstance(Lifecycle.PER_CLASS)
class ManageTest {
    private List<Student> students = new ArrayList<>();
    private Manage record = null;
    private Connection con = null;
    private int id;

    @BeforeAll
    void createConnection() throws IOException, SQLException, InterruptedException {
        // Start the PostgreSQL server.
        // ******************************************/
        String cmd = "docker start quizzical_chatterjee";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        // ******************************************/

        // Read database.properties file.
        // ************************************************************************************************************************/
        FileInputStream fs = new FileInputStream(
                "/Volumes/Software/Developer/Java-Programs/Student-Data-Management/app/src/main/resources/database.properties");
        Properties properties = new Properties();
        properties.load(fs);
        // ************************************************************************************************************************/

        // Check for correct url, username and password for PostgreSQL server.
        // *********************************************************************************************/
        assertEquals("jdbc:postgresql://localhost:5432/records", properties.getProperty("url"));
        assertEquals("records", properties.getProperty("username"));
        assertEquals("p@ssword", properties.getProperty("password"));
        // *********************************************************************************************/

        // Connect to the PostgreSQL server.
        // **************************************************************************************************************/
        con = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
                properties.getProperty("password"));

        assertNotNull(con); // Check for valid connection.
        record = new Manage(con);
        // **************************************************************************************************************/
    }

    @AfterAll
    void closeConnection() throws SQLException, InterruptedException, IOException {
        // Close connection to PostgreSQL server.
        // *************************************************************/
        con.close();
        assertTrue(con.isClosed()); // Check is connection was closed.
        // *************************************************************/

        // Stop the PostgreSQL server.
        // ****************************************/
        String cmd = "docker stop quizzical_chatterjee";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        // ****************************************/
    }

    @Test
    void getAllStudentsTest() throws SQLException, IOException {
        students = record.getAllStudents();
        assertNotNull(record.getAllStudents()); // Check to see if a object is returned.
    }

    @Test
    void addStudentTest() throws SQLException {
        Student student = new Student(0, "Daniel", "Miles", "Male", 44, "daniel.miles@revature.net", "Junior");
        assertEquals(1, record.addStudent(student)); // Check if student was successfully added to database.
    }

    @Test
    void getStudentByNameTest() {
        id = students.get(1).getId();
        System.out.println(id);
        boolean exist = false;
        for (Student student : students) {
            if (student.getFname().equals("Daniel")) {
                exist = true;
            }
        }
        assertTrue(exist);
        assertNotNull(record.getStudentByName("Daniel"));
    }

    @Test
    void removeStudentByIdTest() {
        assertEquals(0, record.removeStudentById(id)); // Check if student successfully removed from the database.
    }

    @Test
    void updateStudentTest() {
        List<String> test = new ArrayList<>();
        test.add("2");
        test.add("Dexter");
        test.add("Dawkins");
        test.add("dexter.dawkins@revature.net");
        test.add("Male");
        test.add("32");
        test.add("Junior");
        assertEquals(1, record.updateStudent(test)); // Check if student was successfully updated in database.
    }
}
