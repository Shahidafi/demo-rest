package com.reactiveworks.demorest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.reactiveworks.demorest.domain.Student;

public class StudentDao {

	Connection con = null;

	public StudentDao() {
		String url = "jdbc:mysql://localhost:3306/shahidafridi";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException | ClassNotFoundException exception) {
			System.out.println(exception.getMessage());
		}
	}

	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		String query = "select * from student";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				students.add(new Student(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}
		return students;
	}

	public Student getStudent(int id) {
		Student student = new Student();
		String query = "select * from student where id = " + id;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setAge(rs.getInt(3));
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}
		return student;
	}

	public void createStudent(Student student) {
		String query = "insert into student values (?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, student.getId());
			stmt.setString(2, student.getName());
			stmt.setInt(3, student.getAge());
			stmt.executeUpdate();
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}
	}

	public void updateStudent(Student student) {
		String query = "update student set name = ?, age = ? where id = ? ";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, student.getName());
			stmt.setInt(2, student.getAge());
			stmt.setInt(3, student.getId());
			stmt.executeUpdate();
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}
	}

	public void deleteStudent(int id) {
		String query = "delete from student where id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
		}
	}

}
