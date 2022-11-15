package com.cst438;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentGrade;
import com.cst438.domain.AssignmentGradeRepository;
import com.cst438.domain.AssignmentRepository;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.AssignmentListDTO;

/*
 * This example shows how to use selenium testing using the web driver 
 * with Chrome browser.
 * 
 *  - Buttons, input, and anchor elements are located using XPATH expression.
 *  - onClick( ) method is used with buttons and anchor tags.
 *  - Input fields are located and sendKeys( ) method is used to enter test data.
 *  - Spring Boot JPA is used to initialize, verify and reset the database before
 *      and after testing.
 *      
 *  In SpringBootTest environment, the test program may use Spring repositories to 
 *  setup the database for the test and to verify the result.
 */

@SpringBootTest
public class EndToEndAssignmentAdd {

	public static final String CHROME_DRIVER_FILE_LOCATION = "C:/chromedriver_win32/chromedriver.exe";

	public static final String URL = "http://localhost:3000";
	public static final String TEST_USER_EMAIL = "test@csumb.edu";
	public static final String TEST_INSTRUCTOR_EMAIL = "dwisneski@csumb.edu";
	public static final int SLEEP_DURATION = 1000; // 1 second.
	public static final String TEST_ASSIGNMENT_NAME = "Test Assignment";
	public static final String TEST_COURSE_TITLE = "Test Course";
	public static final String TEST_STUDENT_NAME = "Test";
	public static final String TEST_DUE_DATE = "01/01/1111";


	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	AssignmentListDTO assignmentListDTO;

	@Test
	public void addAssignmentTest() throws Exception {
		
		Assignment a = null;
		do {
			a = (Assignment) assignmentRepository.findAssignmentByName(TEST_ASSIGNMENT_NAME);
			System.out.println(a);
			if(a != null)
				assignmentRepository.delete(a);
		} while (a != null);

		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(URL);
		Thread.sleep(SLEEP_DURATION);
		

		try {
			driver.get(URL);;
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.xpath("//button[@name='addassignment']")).click();
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.xpath("//input[@name='assignmentname']")).sendKeys(TEST_ASSIGNMENT_NAME);
			Thread.sleep(SLEEP_DURATION);
			driver.findElement(By.xpath("//input[@name='duedate']")).sendKeys(TEST_DUE_DATE);
			Thread.sleep(SLEEP_DURATION);
			driver.findElement(By.xpath("//input[@name='coursename']")).sendKeys(TEST_COURSE_TITLE);
			Thread.sleep(SLEEP_DURATION);
			driver.findElement(By.xpath("//button[@id='Submit']")).click();
			Thread.sleep(SLEEP_DURATION);
			
			Assignment assignment = (Assignment) assignmentRepository.findAssignmentByName(TEST_ASSIGNMENT_NAME);
			if(assignment == null) {
				System.out.println("Assignment not found in database");
			}

		} catch (Exception ex) {
			throw ex;
		} finally {

			Assignment as = (Assignment) assignmentRepository.findAssignmentByName(TEST_ASSIGNMENT_NAME);
			if (as!=null) assignmentRepository.delete(as);

			driver.quit();
		}

	}
}
