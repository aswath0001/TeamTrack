package backend_project1;

import java.sql.*;

public class Backend_project1 {

    public static void main(String[] args) throws Exception {
    	readRecords();
    }
  
    //for reading the table
    public static void readRecords() throws Exception {
        String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        String query = "SELECT * FROM employee";

       
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println("The employee id is: " + rs.getInt(1));
            System.out.println("The employee name is: " + rs.getString(2));
            System.out.println("The employee salary is: " + rs.getInt(3));
            System.out.println("____________________________");
        }

        con.close();
    }
    //inserting values to the table
    public static void insertRecords() throws Exception {
        String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        String query = "insert into  employee values (5 ,'Darsha', 115000)";

       
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

       System.out.println("Number of rows affected " + rows);
        con.close();

   }
    //adding new row to the table
    public static void insertVar() throws Exception {
        String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        

       int id = 6;
       String name = "Rakshita";
       int salary = 2500000;
       
       String query = "insert into  employee values (" + id  + ",'" + name +"'," + salary+")";
        
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

       System.out.println("Number of rows affected " + rows);
        con.close();
    }
    // adding new row using another method
    public static void insertUsingPreparedStatement() throws Exception {
        String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        

       int id = 7;
       String name = "Aswath";
       int salary = 2250000;
       
       String query = "insert into  employee values (?,?,?)";
        
        Connection con = DriverManager.getConnection(url, userName, password);
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, id);
        pst.setString(2, name);
        pst.setInt(3, salary);
        int rows = pst.executeUpdate();
        System.out.println("no of rows affected " + rows);
        con.close();
    }
    // deleting a row from the table
    public static void delete() throws Exception {
        String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        

       int id = 7;
       
       String query = "delete from employee where EMP_id =" + id;
        
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

       System.out.println("Number of rows affected " + rows);
        con.close();
    }
    // updating a row
    public static void update() throws Exception {
        String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        

       
       
       String query = "update employee set EMP_salary = 4560000 where EMP_id = 6 ";
        
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

       System.out.println("Number of rows affected " + rows);
        con.close();
    }
    //calling a simple stored procedure
    public static void sp () throws Exception {
    	String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        
        Connection con = DriverManager.getConnection(url , userName, password);
        CallableStatement cst = con.prepareCall("{call getEmp()}");
        ResultSet RS = cst.executeQuery();
        
        while (RS.next()) {
            System.out.println("The employee id is: " + RS.getInt(1));
            System.out.println("The employee name is: " + RS.getString(2));
            System.out.println("The employee salary is: " + RS.getInt(3));
            System.out.println("____________________________");
        }

        
        con.close();
    }
    // calling stored procedure input with parameter
    public static void sp2 () throws Exception {
    	String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        int id = 7;
        Connection con = DriverManager.getConnection(url , userName, password);
        CallableStatement cst = con.prepareCall("{call getEmpById(?)}");
        cst.setInt(1, id);
        ResultSet RS = cst.executeQuery();
        
        while (RS.next()) {
            System.out.println("The employee id is: " + RS.getInt(1));
            System.out.println("The employee name is: " + RS.getString(2));
            System.out.println("The employee salary is: " + RS.getInt(3));
            System.out.println("____________________________");
        }

        
        con.close();
    }
    // creating a stored precedure wuth in and out parameter
    public static void sp3 () throws Exception {
    	String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        int id = 6;
        Connection con = DriverManager.getConnection(url , userName, password);
        CallableStatement cst = con.prepareCall("{call GetEmpNameById(?,?)}");
        cst.setInt(1, id);
        cst.registerOutParameter(2, Types.VARCHAR);
        cst.executeUpdate();
        System.out.println(cst.getString(2));
    
        con.close();
    }
    //commit auto commit
    public static void commit() throws Exception {
        String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        

       
       
       String query1 = "update employee set EMP_salary = 7560000 where EMP_id = 6 ";
       String query2 = "update employee set EMP_salary = 5550000 where EMP_id = 7 ";
        
        Connection con = DriverManager.getConnection(url, userName, password);
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        
        int row1 = st.executeUpdate(query1);
       System.out.println("Number of rows affected " + row1);
       
       int row2 = st.executeUpdate(query2);
      System.out.println("Number of rows affected " + row2);
      
      if (row1 > 0 & row2 > 0)
    	  con.commit();
        con.close();
    }
    public static void batchDemo() throws Exception {
        String url = "jdbc:mysql://localhost:3306/backend_project1";
        String userName = "root";
        String password = "root";
        

       
       
       String query1 = "update employee set EMP_salary = 600000 where EMP_id = 1 ";
       String query2 = "update employee set EMP_salary = 600000 where EMP_id = 2 ";
       String query3 = "update employee set EMP_salary = 600000 where EMP_id = 3 ";
       String query4 = "update employee set EMP_salary = 600000 where EMP_id = 4 ";
        
        Connection con = DriverManager.getConnection(url, userName, password);
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        st.addBatch(query1);
        st.addBatch(query2);
        st.addBatch(query3);
        st.addBatch(query4);
        
        int [] res = st.executeBatch();
        for (int i :res) {
        	if (i>0) {
        		continue;
        	}
        	else {
        		con.rollback();
        	}
        }
        con.commit();
        
        con.close();
    }
}

