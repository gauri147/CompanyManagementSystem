import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.String;

class Db1 {

   Connection conn;


   public boolean OpenConnection() throws SQLException, IOException {

      try {
         Class.forName("com.mysql.jdbc.Driver");
      }
      catch (ClassNotFoundException e) {
         System.out.println("Could not load the driver.");
         e.printStackTrace();
         return false;
      }
      try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Company","root","root");  
         return true;
      }
      catch (SQLException sql) {
         sql.printStackTrace();
         return false;
      }
   }

   public void CloseConnection() throws SQLException {
      conn.close();
   }

   public ResultSet ListAll() {
      try {
         Statement st = conn.createStatement();
         ResultSet resultset = st.executeQuery("select Country_Name from COUNTRY");
         return resultset;
      }
      catch (SQLException sql) {
         sql.printStackTrace();
         return null;
      }
   }

   public void populate() {
      try {
         File f;
         Scanner scanner;
         Statement stmt = conn.createStatement();
         String sql;

         f = new File("G:\\DB1\\Project1\\csv\\DEPARTMENT.csv");
         scanner = new Scanner(f);
         while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] arrOfStr = str.split(",");

            sql = "INSERT INTO DEPARTMENT " +
                   "VALUES ('"+ arrOfStr[0] +"', "+ arrOfStr[1] +", "+ arrOfStr[2]+", '"+ arrOfStr[3] +"')";
            stmt.executeUpdate(sql);
         }

         f = new File("G:\\DB1\\Project1\\csv\\EMPLOYEE.csv");
         scanner = new Scanner(f);
         while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] arrOfStr = str.split(",");

            // for (String a : arrOfStr) 
            //    System.out.println(a);

            sql = "INSERT INTO EMPLOYEE " +
                   "VALUES ('"+ arrOfStr[0] +"', '"+ arrOfStr[1] +"', '"+ arrOfStr[2]+"', "+ arrOfStr[3]+", '"+ arrOfStr[4]+"', '"+ arrOfStr[5]+"', '"+ arrOfStr[6]+
                   "',"+ arrOfStr[7]+"," + arrOfStr[8] + "," + arrOfStr[9]+")";
                   
            stmt.executeUpdate(sql);
         }

         f = new File("G:\\DB1\\Project1\\csv\\DEPENDENT.csv");
         scanner = new Scanner(f);
         while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] arrOfStr = str.split(",");

            sql = "INSERT INTO DEPENDENT " +
                   "VALUES ("+ arrOfStr[0] +", '"+ arrOfStr[1] +"', '"+ arrOfStr[2]+ "','" + arrOfStr[3]+ "','" + arrOfStr[4]+"')";
            stmt.executeUpdate(sql);
         }
     
         f = new File("G:\\DB1\\Project1\\csv\\DEPT_LOCATIONS.csv");
         scanner = new Scanner(f);
         while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] arrOfStr = str.split(",");

            sql = "INSERT INTO DEPT_LOCATIONS " +
                   "VALUES ("+ arrOfStr[0] +", '"+ arrOfStr[1] +"')";
            stmt.executeUpdate(sql);
         }

         f = new File("G:\\DB1\\Project1\\csv\\PROJECT.csv");
         scanner = new Scanner(f);
         while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] arrOfStr = str.split(",");

            sql = "INSERT INTO PROJECT " +
                   "VALUES ('"+ arrOfStr[0] +"', "+ arrOfStr[1] +", '"+ arrOfStr[2]+ "'," + arrOfStr[3] +")";
            stmt.executeUpdate(sql);
         }

         f = new File("G:\\DB1\\Project1\\csv\\WORKS_ON.csv");
         scanner = new Scanner(f);
         while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] arrOfStr = str.split(",");

            sql = "INSERT INTO WORKS_ON " +
                   "VALUES ("+ arrOfStr[0] +", "+ arrOfStr[1] +", "+ arrOfStr[2]+")";
            stmt.executeUpdate(sql);
         }

         scanner.close();

         return;
      }
      catch (SQLException sql) {
         sql.printStackTrace();
         return;
      }
      catch (FileNotFoundException e) {
         System.out.println("Exception :" + e.getMessage());
      }
      catch (Exception e) {
         System.out.println("Exception : " + e.getMessage());
      }
   }

   public static void main( String args[] )
   {
      ResultSet reset;
      try {
         Db1 connect = new Db1();
         // JDBCconnect connect = new JDBCconnect(args[0], args[1]);
         if ( connect.OpenConnection() ) {
            // reset = connect.ListAll( );

            // while( reset.next() ) {
            //    System.out.println(reset.getString("Country_Name"));
            // }
            connect.populate();
         }

         connect.CloseConnection();
      }
      catch (SQLException exception) {
         System.out.println("\nSQLException" + exception.getMessage()+"\n");
      }
      catch ( IOException e) {
         e.printStackTrace();
      }
   }
}


