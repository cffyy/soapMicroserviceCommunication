package com.example.howtodoinjava.springbootsoapservice;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.howtodoinjava.xml.school.StudentsListCompareResponse;

import studentCompare.wsdl.StudentBySchoolRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan
class WireMockTest {

	StudentClient studentClient;
	WireMockServer wireMockServer = new WireMockServer();

	@BeforeEach
	public void startWiremock() {
		wireMockServer = new WireMockServer(wireMockConfig().port(8090));
		wireMockServer.start();
	}

	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("studentCompare.wsdl");
		return marshaller;
	}

	public StudentClient studentClient(Jaxb2Marshaller marshaller) {
		StudentClient client = new StudentClient();
		client.setDefaultUri("http://localhost:8090/service/student-details-bySchoolName");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

	@Test
	void test() {

		wireMockServer.stubFor(any((anyUrl())).willReturn(aResponse().withStatus(200)
				.withHeader("content-type", "text/xml").withBodyFile("CountrySuccessResponse.xml")));

		Jaxb2Marshaller j = marshaller();
		StudentClient scc = studentClient(j);
		StudentBySchoolRequest request = new StudentBySchoolRequest();
		request.setSchoolName("English school");
		StudentsListCompareResponse res = scc.compareStudent(request);
		assertEquals("English School", res.getStatus());
	}

	@AfterEach
	void tearDown() {
		wireMockServer.stop();
	}

}
