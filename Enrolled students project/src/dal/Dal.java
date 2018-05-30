package dal;
import java.sql.*;

import modell.*;

import java.util.ArrayList;
import java.util.Vector;

import connector.Connector;
import modell.*;

import java.util.Arrays;
public class Dal {
	private String ccode;
	private String cName;
	private String sname;
	private String sadress;
	private static String sqlString;
	private Student student;
	private String points;
	private Studied studied;
	private Course course;
	private ArrayList<Course> courseList = new ArrayList<Course>();
	private ArrayList <Student> studiesList = new ArrayList<Student>();
	
	
	
/* Lägger in student */
	public void addStudent(String spnr, String sname, String sadress) throws SQLException {
		Connection conn = Connector.startConnection();
		Statement stmt = conn.createStatement();
		
		String sqlString = "INSERT INTO Student VALUES ('" + spnr + "', '"
		+ sname + "', '" + sadress + "');";
		
		stmt.executeUpdate(sqlString);
		stmt.close();
		
	}
	/*Lägger till kurs */
	public void addCourse(String ccode, String cName, String points) throws SQLException {
		Connection conn = Connector.startConnection();
		Statement stmt = conn.createStatement();
		
		String sqlString = "INSERT INTO Course VALUES ('" + ccode + "','" + cName + "','" + points + "');";
		stmt.executeUpdate(sqlString);
		stmt.close();
	}
	/*Lägger till student på kurs*/
	public void addStudentToCourse(String spnr, String ccode) throws SQLException {
		Connection conn = Connector.startConnection();
		Statement stmt = conn.createStatement();
		
		sqlString ="SELECT sum(points) "
				+ "From Studies s, course c "
				+ "where s.ccode = c.ccode and spnr = '" + spnr +"' "
				+ "group by spnr "
				+ "UNION SELECT points from course c where ccode = '"+ ccode +"'";
		ResultSet rs = stmt.executeQuery(sqlString);
		int totalpoints = 0;
		while(rs.next()) {
			totalpoints += rs.getInt(1);
		}
		if(totalpoints > 45) {
			stmt.close();
		}
		
		else {
		String sqlString2 = "INSERT INTO Studies VALUES('" + spnr + "','" + ccode +"');";
		stmt.executeUpdate(sqlString2);
		stmt.close();
	}
	}
	//Lägger till student i studied och tilldelar betyg
	public void addStudentToStudied(String spnr, String ccode, String grade) throws SQLException {
		Connection conn = Connector.startConnection();
		Statement stmt = conn.createStatement();
		
		String sqlString  = "INSERT INTO Studied VALUES('" + spnr + "' , '" + ccode + "','" + grade +"');";
		stmt.executeQuery(sqlString);
		stmt.close();
	}
	/* Hämta student */
	public Student findStudent(String spnr) throws SQLException {
		Connection conn = Connector.startConnection();
		
		sqlString = "SELECT * FROM Student Where spnr = '" + spnr +"'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		rs.next();
		spnr = rs.getString(1);
		sname = rs.getString(2);
		sadress = rs.getString(3);
		
		Student  s = new Student (spnr, sname, sadress);
		
		return  s;
	}
	/* Hämta kurs */
	public Course findCourse(String ccode) throws SQLException {
		Connection conn = Connector.startConnection();
		
		sqlString ="SELECT * FROM COURSE WHERE ccode = '" + ccode + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		rs.next();
		ccode = rs.getString(1);
		cName = rs.getString(2);
		points = rs.getString(3);
		
		Course course = new Course (ccode, cName, points);
		
		return course;
	}
	/*Hitta resutatet för studenter på en specifik kurs*/
	public ArrayList<Studied> getGradeForCourse(String spnr, String ccode) throws SQLException {
		ArrayList<Studied> StudentstudiedList = new ArrayList<Studied>();
		
		Connection conn = Connector.startConnection();
		sqlString = "SELECT spnr, ccode, grade FROM Studied WHERE spnr = '" + spnr + "' AND ccode = '" + ccode + "'";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		while (rs.next()) {
			studied = new Studied(rs.getString(1), rs.getString(2), rs.getString(3));
			StudentstudiedList.add(studied);
		}
		return StudentstudiedList;
	}
	
	/*Hämta samtliga kurser*/
	/*public ArrayList<Course> getAllCourses() throws SQLException {
		ArrayList<Course> courseList = new ArrayList<Course>();
		
		Connection conn = Connector.startConnection();
		sqlString = "SELECT * FROM Course";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		while(rs.next()) {
			course = new Course (rs.getString(1), rs.getString(2), rs.getString(3));
			courseList.add(course);
		}
		return courseList;
	}*/
	
	
	
	// Visa studenter som tagit kursen och deras betyg
	public ArrayList<Studied> getGradeList(String ccode) throws SQLException {
		ArrayList<Studied> studiedList = new ArrayList<Studied>();
		Connection conn = Connector.startConnection();
		sqlString = "SELECT h.spnr, h.ccode, h.grade FROM Studied h JOIN Student s "
				+ "ON h.spnr = s.spnr WHERE h.ccode = '"+ ccode + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		while(rs.next()) {
			studied = new Studied (rs.getString(1), rs.getString(2), rs.getString(3));
			studiedList.add(studied);
			
		}
		return studiedList;
	}
	
	//Visa studenter på en specifik kurs
	public ArrayList<Student> getCurrentStudies(String ccode) throws SQLException {
		ArrayList<Student> studiesList = new ArrayList<Student>();
		Connection conn = Connector.startConnection();
		sqlString = "SELECT student.spnr, student.sname FROM Student INNER JOIN Studies " + "ON student.spnr = studies.spnr WHERE ccode = '"+ ccode +"'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		while(rs.next()) {
			student = new Student (rs.getString(1), rs.getString(2), ccode);
			studiesList.add(student);
		}
		return studiesList;
	}
	
	/*Ta bort student */
	public void deleteStudent(String spnr) throws SQLException {
		Connection conn = Connector.startConnection();
		
		sqlString = "DELETE FROM Student WHERE spnr ='" + spnr + "'";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sqlString);
		conn.close();
	}
	/*Ta bort kurs */
	public void deleteCourse (String ccode) throws SQLException {
		Connection conn = Connector.startConnection();
		
		sqlString = "DELETE FROM Course WHERE ccode ='" + ccode + "'";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sqlString);
		conn.close();
}
	/*Ta bort student från en kurs*/
	public void removeStudentFromStudies(String spnr, String ccode) throws SQLException {
		Connection conn = Connector.startConnection();
		
		sqlString = "DELETE FROM Studies WHERE spnr = '" + spnr +"' AND ccode ='" + ccode + "'";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sqlString);
		conn.close();
	}
	/* Gör om Arraylist till array för alla kurser*/
	public String[] convertCoursestoArray() throws SQLException {
		Connection conn = Connector.startConnection();
		sqlString = "SELECT * FROM Course";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlString);
		
		
		while(rs.next()) {
			course = new Course (rs.getString(1), rs.getString(2), rs.getString(3));
			courseList.add(course);
		}
		String[] cList = new String[courseList.size()];
		int i = 0;
		for(Course c : courseList){
			cList[i] = c.getCcode();
			i++;
		}
		
		return cList;
}
	//Procentantalet studenter som fått A på en angiven kurs
			public String getPercentageGradeA(String ccode) throws SQLException {

				String percent = new String();

				Connection conn = Connector.startConnection();

		sqlString = "SELECT Grade, (COUNT(grade)* 100 / (SELECT COUNT(*) FROM Studied WHERE ccode='" + ccode + "')) "
						+  "AS ScorePercentage"
						+ " FROM Studied GROUP BY ccode, grade "
						+ "HAVING ccode='"
						+ ccode
						+ "' AND Grade='A'";

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlString);

				while (rs.next()) {
					percent = rs.getString(2);
				}

				return percent;
			}
			
	//Genomströmning
	public String getStudentFlow() throws SQLException {

		Connection conn = null;
		PreparedStatement preState = null;
		ResultSet rs = null;
		String ccode = null;
		String percent = null;
		String result = null;

		try {
			conn = Connector.startConnection();

			preState = conn.prepareStatement("SELECT TOP 1 ccode, "
					+ " FORMAT((SUM(CASE WHEN grade != 'U' THEN 1 ELSE 0 END)) "
					+ "* 100.0 / (SUM(CASE WHEN grade LIKE '_' THEN 1 ELSE 0 END)),'F0') "
				+	 "AS PassedPercentage "
				+ "FROM studied "
				+ "GROUP BY ccode "
				+ "ORDER BY PassedPercentage DESC");
			rs = preState.executeQuery();
			rs.next();
			ccode = rs.getString(1);
			percent = rs.getString(2);
			result = (ccode + " zzsw" + " " + percent + "%");
		} catch (SQLException e) {
			throw new SQLException("Couldn't find a course", e);
		}
		return result;
		
		
	
	}
		} 