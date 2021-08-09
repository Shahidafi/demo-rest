package com.reactiveworks.demorest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.reactiveworks.demorest.dao.StudentDao;
import com.reactiveworks.demorest.domain.Student;

@Path("studentsresource")
public class StudentsResource {

	StudentDao dao = new StudentDao();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Student> getStudents() {
		return dao.getStudents();
	}

	@GET
	@Path("student/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Student getStudent(@PathParam("id") int id) {
		return dao.getStudent(id);
	}

	@POST
	@Path("student")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Student createStudent(Student student) {
		dao.createStudent(student);
		return student;
	}

	@PUT
	@Path("student")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Student updateStudent(Student student) {
		dao.updateStudent(student);
		return student;
	}

	@DELETE
	@Path("student/{id}")
	public Student deleteStudent(@PathParam("id") int id) {
		Student student = dao.getStudent(id);
		dao.deleteStudent(id);
		return student;
	}
	
}
