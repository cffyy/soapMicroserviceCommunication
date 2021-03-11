package com.example.howtodoinjava.springbootsoapservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.howtodoinjava.xml.school.StudentBySchoolRequest;
import com.howtodoinjava.xml.school.StudentBySchoolResponse;

@Endpoint
public class StudentEndpoint {
	private static final String NAMESPACE_URI = "http://www.howtodoinjava.com/xml/school";

	private StudentRepository StudentRepository;

	@Autowired
	public StudentEndpoint(StudentRepository StudentRepository) {
		this.StudentRepository = StudentRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "StudentBySchoolRequest")
	@ResponsePayload
	public StudentBySchoolResponse getStudent(@RequestPayload StudentBySchoolRequest request) {
		StudentBySchoolResponse response = new StudentBySchoolResponse();

		response.setStudentList(StudentRepository.findStudentTwo(request.getSchoolName()));
		response.setDocType("850");
		return response;
	}
}