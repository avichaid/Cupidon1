
import java.sql.*;
import java.util.*;
public class SqlWithJava {
	Connection conn;

	List<Integer> list = new ArrayList<Integer>();	  // to give index for ID so i can loop it
	String _sql;

	public static void main(String[] args) throws SQLException {

		SqlWithJava obg=new  SqlWithJava(); // so i can use the void methods in this page 
		obg.retMaleId(); // fill the next id inside the next cell at the list
		obg.getbasicMatch(); // basic match  - all women for the same man. 

	}

	private Connection connect()   //connect to data base
	{
		String constring="jdbc:sqlite:.\\sqliteDBTest.db";  // where the data base in my computer

		conn=null;
		try   // try to connect to data base
		{
			conn=DriverManager.getConnection(constring);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}

		return conn;
	}


	private void getGirls(int _ID) throws SQLException   // the basic printing data of women
	{
		_sql="SELECT id,name,age, gender,status,email_Address,Company, salary,account";  //basic SQL
		_sql+=" FROM tbl_userinfo WHERE gender='F'";   //for flexibility
		Connection myConn=this.connect(); // connect to data base
		Statement _state1=myConn.createStatement();
		ResultSet rs1=_state1.executeQuery(_sql);   //the connection between SQL and java
		System.out.println("Matchichg Females ");
		//rs.close();
		while(rs1.next()) // while not false - move to the next row
		{
			System.out.println("ID : "+rs1.getInt("id")+"  Name : "+rs1.getString("name")+"  Gender : "+rs1.getString("gender"));  //print the women details
		}
	}


	private void getbasicMatch() throws SQLException   // at the meantime, all women :(
	{
		SqlWithJava obg=new SqlWithJava();  // so i can use the void methods in this page
		for(Integer _id : list) { //for  each man at the list


			_sql="SELECT id,name,age, gender,status,email_Address,Company, salary,account";  //basic SQL
			_sql+=" FROM tbl_userinfo WHERE id="+_id.toString()+"";   //for flexibility
			Connection myConn=this.connect();  //connect to data base
			Statement _state=myConn.createStatement();
			ResultSet rs=_state.executeQuery(_sql);  //the connection between SQL and java
			while(rs.next())
			{
				System.out.println(rs.getString("name")+" Data");
				System.out.println("ID : "+rs.getInt("id")+"   Name : "+rs.getString("name"));
				System.out.println("________________________________________________");  // the man data
				obg.getGirls(_id);// all the women

			}

		}

	}
	private void retMaleId()   // fill the next id inside the next cell at the list
	{
		_sql ="SELECT id FROM tbl_userinfo where gender='M';";

		try
		{
			Connection myConn=this.connect();
			Statement _state=myConn.createStatement();
			ResultSet rs=_state.executeQuery(_sql);

			while(rs.next())
			{
				Integer mid=rs.getInt("id");
				list.add(mid);


			}
			System.out.println("Id's List Is completed");
		}
		catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}
}
