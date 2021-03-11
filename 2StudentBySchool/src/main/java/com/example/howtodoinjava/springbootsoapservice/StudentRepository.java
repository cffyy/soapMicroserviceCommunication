package com.example.howtodoinjava.springbootsoapservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.howtodoinjava.xml.school.StudentTwo;

@Component
public class StudentRepository {

	public ArrayList<StudentTwo> findStudentTwo(String school) {

		Assert.notNull(school, "The School name must not be null");
		StudentTwo student1 = new StudentTwo();
		student1.setSchoolName("School");
		student1.setAddress("Delhi");
		student1.setAge(10);
		student1.setGender(true);
		student1.setStandard(5);

		StudentTwo student2 = new StudentTwo();
		student2.setSchoolName("College");
		student2.setAddress("Indore");
		student2.setAge(10);
		student2.setGender(true);
		student2.setStandard(5);

		StudentTwo student3 = new StudentTwo();
		student3.setSchoolName("College");
		student3.setAddress("Indore");
		student3.setAge(10);
		student3.setGender(true);
		student3.setStandard(5);

		StudentTwo student4 = new StudentTwo();
		student4.setSchoolName("School");
		student4.setAddress("Delhi");
		student4.setAge(10);
		student4.setGender(true);
		student4.setStandard(5);

		StudentTwo student5 = new StudentTwo();
		student5.setSchoolName("College");
		student5.setAddress("Indore");
		student5.setAge(10);
		student5.setGender(true);
		student5.setStandard(5);

		StudentTwo student6 = new StudentTwo();
		student6.setSchoolName("School");
		student6.setAddress("Delhi");
		student6.setAge(10);
		student6.setGender(true);
		student6.setStandard(5);

		Map<String, StudentTwo> studentMap = new HashMap<String, StudentTwo>();
		studentMap.put("name1", student1);
		studentMap.put("name2", student2);
		studentMap.put("name3", student3);
		studentMap.put("name4", student4);
		studentMap.put("name5", student5);
		studentMap.put("name6", student6);

		ArrayList<StudentTwo> studentList = new ArrayList<>();

		for (Map.Entry<String, StudentTwo> entry : studentMap.entrySet()) {
			if (entry.getValue().getSchoolName().equalsIgnoreCase(school)) {
				studentList.add(entry.getValue());
			}
		}
		return studentList;
	}

}