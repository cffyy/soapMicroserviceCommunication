package com.example.howtodoinjava.springbootsoapservice;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.howtodoinjava.xml.school.StudentsListCompareResponse;

import studentCompare.wsdl.StudentBySchoolRequest;
import studentCompare.wsdl.StudentBySchoolResponse;

@Component
public class StudentClient extends WebServiceGatewaySupport {

	public StudentsListCompareResponse compareStudent(StudentBySchoolRequest request) {
		StudentBySchoolResponse sr = (StudentBySchoolResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request);
		StudentsListCompareResponse response = new StudentsListCompareResponse();
		response.setStatus(sr.getStudentList().get(0).getSchoolName());
		return response;
	}

}