package student.management;

/**
 * Represents a student
 * 
 * @author Shane Daniel
 * @author shane.daniel@revature.net
 */
public class Student {
    // Class attributes.
    private String fname;
    private String lname;
    private String email;
    private String gender;
    private String classification;
    private int age;
    private int id;

    // Default Constructor.
    public Student() {
        fname = null;
        lname = null;
        email = null;
        gender = null;
        classification = null;
        age = -1;
        id = -1;
    }

    /**
     * Creates a student with a specified first name, last name, gender, age, and
     * email.
     * 
     * @param fname          Student's first name.
     * @param lname          Student's last name.
     * @param gender         Student's gender.
     * @param age            Students's age.
     * @param email          Student's email.
     * @param classification Student's classification.
     */
    public Student(int id, String fname, String lname, String gender, int age, String email, String classification) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.classification = classification;
    }

    /**
     * Sets the students first and last name.
     * 
     * @param fname String containing the student's first name.
     * @param lname String containing the student's last name.
     */
    public void setName(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    /**
     * Get the student's email address.
     * 
     * @return A string containing student's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the student's email address.
     * 
     * @param email String containing the student's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the student's age.
     * 
     * @return A int containing the student's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the student's age.
     * 
     * @param age int containing the student's age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the id of the student.
     * 
     * @return A int containg the student's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the students id.
     * 
     * @param id int containing the student's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the gender of the student.
     * 
     * @return A String containing the student's gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the students gender.
     * 
     * @param gender String containing the student's gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the first name of the student.
     * 
     * @return A String containing the student's first name.
     */
    public String getFname() {
        return fname;
    }

    /**
     * Sets the student's first name.
     * 
     * @param fname String containing the student's first name.
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * Get the last name of the student.
     * 
     * @return A String containing the student's last name.
     */
    public String getLname() {
        return lname;
    }

    /**
     * Sets the student's last name.
     * 
     * @param lname String containing the student's last name.
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * Get the classification of the student.
     * 
     * @return A string containing the student's classification.ß
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Sets the student's classification.
     * 
     * @param classification String containing the student's classification.
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }
}
