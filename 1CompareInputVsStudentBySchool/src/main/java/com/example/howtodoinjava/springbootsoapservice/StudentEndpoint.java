package com.example.howtodoinjava.springbootsoapservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.howtodoinjava.xml.school.StudentDetailListRequest;
import com.howtodoinjava.xml.school.StudentsListCompareResponse;

import studentCompare.wsdl.StudentBySchoolRequest;

@Endpoint
public class StudentEndpoint {
	private static final String NAMESPACE_URI = "http://www.howtodoinjava.com/xml/school";

	private StudentClient studentService;

	@Autowired
	public StudentEndpoint(StudentClient studentService) {
		this.studentService = studentService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudentDetailListRequest")
	@ResponsePayload
	public StudentsListCompareResponse getStudent(@RequestPayload StudentDetailListRequest request) { //1st request
		StudentBySchoolRequest sbsr = new StudentBySchoolRequest(); //2nr request
		sbsr.setSchoolName(request.getStudentList().get(0).getSchoolName());
		return studentService.compareStudent(sbsr); //sending 2nd request
	}
}