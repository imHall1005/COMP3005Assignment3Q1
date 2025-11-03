/*
-IMPORTANT! must have postgresql-42.7.8.jar in external libraries or will not work!
-IMPORTANT! Check port number and password are correct before proceeding!
-For full function documentation, see getAllStudents(), all functions are very similar to avoid redundancy, full commenting was omitted
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    //function displays all columns of all rows from student table from Students database
    public static void getAllStudents(){
        //we set variables for connection to sql server
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String password = "admin";

        try{
            //connect to sql server
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            //create sql statement
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            //print out results
            while(resultSet.next()){
                System.out.print(resultSet.getString("student_id") + "\t");
                System.out.print(resultSet.getString("first_name") + "\t");
                System.out.print(resultSet.getString("last_name") + "\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.println(resultSet.getString("enrollment_date"));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    //function inserts new row into student table from Students database
    public static void addStudent(String firstName,String lastName,String email,String enrollmentDate){
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String password = "admin";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('"+firstName+"', '"+lastName+"', '"+email+"', '"+enrollmentDate+"')");

        }
        catch (Exception e){
            //System.out.println(e);
        }
    }
    //function updates email column value for specific row in student table from Students database
    public static void updateStudentEmail(int student_id, String newEmail){
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String password = "admin";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE students SET email = '"+newEmail+"' WHERE student_id = "+student_id);

        }
        catch (Exception e){
            //System.out.println(e);
        }
    }
    //function deletes existing row from student table from Students database
    public static void deleteStudent(int student_id){
        String url = "jdbc:postgresql://localhost:5432/Students";
        String user = "postgres";
        String password = "admin";

        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeQuery("DELETE FROM students WHERE student_id = "+student_id);

        }
        catch (Exception e){
            //System.out.println(e);
        }
    }

    public static void main(String[] args) {
        //show initial students; should be John, Jane, and Jim
        getAllStudents();
        //add a new student Billy to database
        addStudent("Billy","Bob","billybob@mail","2025-01-01");
        getAllStudents();
        //update email of John Doe
        updateStudentEmail(1,"theNewJohnDoe@mail.com");
        getAllStudents();
        //Remove Jane from database
        deleteStudent(2);
        getAllStudents();
    }
}