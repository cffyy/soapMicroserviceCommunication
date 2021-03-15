package com.example.howtodoinjava.springbootsoapservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.howtodoinjava.xml.school.StudentM2;

@Component
public class StudentService {

	public ArrayList<StudentM2> findStudent(String school) {

		Assert.notNull(school, "The School name must not be null");
		
		StudentM2 student1 = new StudentM2();
		student1.setSchoolName("English School");
		student1.setAddress("Delhi");
		student1.setAge(10);
		student1.setGender(true);
		student1.setStandard(5);

		StudentM2 student2 = new StudentM2();
		student2.setSchoolName("Hindi School");
		student2.setAddress("Indore");
		student2.setAge(10);
		student2.setGender(true);
		student2.setStandard(5);

		Map<String, StudentM2> studentMap = new HashMap<String, StudentM2>();
		studentMap.put("name1", student1);
		studentMap.put("name2", student2);

		ArrayList<StudentM2> studentList = new ArrayList<>();

		for (Map.Entry<String, StudentM2> entry : studentMap.entrySet()) {
			if (entry.getValue().getSchoolName().equalsIgnoreCase(school)) {
				studentList.add(entry.getValue());
			}
		}
		return studentList;
	}

}