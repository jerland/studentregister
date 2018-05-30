package controller;
import dal.Dal;

import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;

import modell.*;

public class Controller {
	private Dal dal;
	private Student student;
	private Course course;
	ArrayList<Student> gradeList;
	ArrayList<Course> courseList;
	Vector<String> vectorlist;
	
	public Controller()
	{
		dal = new Dal ();
		
	}
	public Student findStudent(String spnr) throws SQLException {
		student = dal.findStudent(spnr);
				return student;
	}
	public Course findCourse(String ccode) throws SQLException {
		course = dal.findCourse(ccode);
		return course;
	}
	public ArrayList<Studied> getGradeList(String ccode) throws SQLException {
		return dal.getGradeList(ccode);
	}
	public ArrayList<Studied> getGradeForCourse(String spnr, String ccode) throws SQLException {
		return dal.getGradeForCourse(spnr, ccode);
	}
	public ArrayList<Student> getCurrentStudies(String ccode) throws SQLException {
		return dal.getCurrentStudies(ccode);
	}
	/*public ArrayList<Course> getCoursesList() throws SQLException {
		return dal.getAllCourses();
	}*/
	public void addStudent(String spnr, String sname,String sadress) throws SQLException {
		dal.addStudent(spnr, sname, sadress);
	}
	public void addCourse(String ccode, String cName, String points) throws SQLException {
		dal.addCourse(ccode, cName, points);
	}
	public void addStudentToCourse(String spnr, String ccode) throws SQLException {
		dal.addStudentToCourse(spnr, ccode);	
	}
	public void addStudentToStudied(String spnr, String ccode, String grade) throws SQLException {
		dal.addStudentToStudied(spnr, ccode, grade);
	}
	public void deleteStudent(String spnr) throws SQLException {
		dal.deleteStudent(spnr);
	}
	public void deleteCourse(String ccode) throws SQLException {
		dal.deleteCourse(ccode);
	}
	public void removeStudentFromCourse(String spnr, String ccode) throws SQLException {
		dal.removeStudentFromStudies(spnr, ccode);
	}
	public String[] convertCoursestoArray() throws SQLException {
		return dal.convertCoursestoArray();
	}
	public String getStudentFlow() throws SQLException {
		return dal.getStudentFlow();
	}
	public String getPercentageGradeA(String ccode) throws SQLException {
		return dal.getPercentageGradeA(ccode);
	}
	/*public Object[] getAllData(String from) throws SQLException {
		return dal.getAllData(from);
	}
	public Object[] getAllMetaData(String from) throws SQLException {
		return dal.getAllMetaData(from);
	}*/
	/*public  Vector<String> getStudent(String spnr)throws SQLException{
		return dal.getStudent(spnr);
}*/
	}


