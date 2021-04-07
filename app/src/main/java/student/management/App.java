package student.management;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        List<String> modifyInputs = new ArrayList<>();
        List<String> addInputs = new ArrayList<>();
        Manage record;

        String str;
        String fname;
        int id;

        // Used to prompt for input.
        // ************************************************************************************************/
        String[] modifyStatements = { "Enter ID of Student To Be Modified: ", "Enter New First Name: ",
                "Enter New Last Name: ", "Enter New Email: ", "Enter New Gender: ", "Enter New Age: ",
                "Enter New Classification: " };
        String[] addStatements = { "Enter Student's First Name: ", "Enter Student's Last Name: ",
                "Enter Student's Gender: ", "Enter Student's Age: ", "Enter Student's Email: ",
                "Enter Student's Classification: " };
        // ************************************************************************************************/

        // Use to select from the menu.
        byte choice;

        // Start the PostgreSQL server.
        // ******************************************/
        String cmd = "docker start quizzical_chatterjee";
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(cmd);
        pr.waitFor();
        // ******************************************/
        
        // Read properties from file.
        FileInputStream fs = new FileInputStream(
                "/Volumes/Software/Developer/Java-Programs/Student-Data-Management/app/src/main/resources/database.properties");
        Properties properties = new Properties();
        properties.load(fs);

        // Get connection to PostgreSQL data base.
        Connection con = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
                properties.getProperty("password"));

        record = new Manage(con);

        while (true) {
            record.menu();
            choice = Byte.parseByte(input.nextLine());
            System.out.println();

            switch (choice) {
            case 1:
                for (String string : addStatements) {
                    System.out.print(string);
                    str = input.nextLine();
                    addInputs.add(str);
                    System.err.println();
                }

                record.addStudent(new Student(0, addInputs.get(0), addInputs.get(1), addInputs.get(2),
                        Integer.parseInt(addInputs.get(3)), addInputs.get(4), addInputs.get(5)));
                break;

            case 2:
                System.out.print("Enter ID of Student To Be Removed: ");
                id = Integer.parseInt(input.nextLine());

                record.removeStudentById(id);
                break;

            case 3:
                for (String string : modifyStatements) {
                    System.out.print(string);
                    str = input.nextLine();
                    modifyInputs.add(str);
                    System.out.println();
                }

                record.updateStudent(modifyInputs);
                break;

            case 4:
                System.out.print("Enter name of Student To Inquiry: ");
                fname = input.nextLine();
                System.out.println();

                record.getStudentByName(fname);
                break;

            case 5:
                record.viewAllRecords();
                break;

            case 6:
                input.close();
                record.exit();
                break;

            default:
                System.out.println("Incorrect Input");
                break;
            }
        }
    }
}
