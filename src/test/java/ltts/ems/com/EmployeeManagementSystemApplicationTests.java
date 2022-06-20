package ltts.ems.com;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import ltts.ems.com.model.Attendance;
import ltts.ems.com.model.Department;
import ltts.ems.com.model.EmployeeDetails;
import ltts.ems.com.repository.AttendanceRepository;
import ltts.ems.com.repository.DepartmentRepository;
import ltts.ems.com.repository.EmployeeRepository;
import ltts.ems.com.service.EmployeeService;
import ltts.ems.com.service.EmployeeServiceImpl;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EmployeeManagementSystemApplicationTests {

	@Autowired
	EmployeeRepository employeerepository;
	@Autowired
	private AttendanceRepository attRepo;
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private DepartmentRepository deptrepo;
	private Attendance attendance;
	private EmployeeDetails empdetails;
	private Department department;
;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date inTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date outTime;

	

	@Test
	@Order(1)
	public void testInsert() 
	{
		LocalDate doj=LocalDate.parse("2022-05-12");
		LocalDate dob=LocalDate.parse("2000-09-13");
		
		empdetails=new EmployeeDetails(101,"checkuser","dummy","checkusername","checkusername",doj,"xyz",dob,"xyz", "abc", "abc");
		employeerepository.save(empdetails);
		System.out.println();
		System.out.println("Saved to database :"+empdetails);	
		System.out.println();

	}
	
	@Test
	@Order(2)
	public void testReadAll()
	{
		List list=employeerepository.findAll();
		System.out.println();
		System.out.println("All records :"+list);
		System.out.println();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test	
	@Order(3)
	public void testFindByUsername()
	{
		EmployeeDetails employeedetails1=employeerepository.findByUserNameAndPassword("checkusername","checkusername");
		assertThat(employeedetails1.getFirstName());
		System.out.println();
		System.out.println("Founded the employee with Employee Firstname : "+employeedetails1.getFirstName());
		System.out.println();

	}
	
	@Test
	@Order(4)
	public void testDeleteRecord()
	{
		EmployeeDetails employeedetails1=employeerepository.findByUserNameAndPassword("checkusername","checkusername");
		employeerepository.delete(employeedetails1);
		System.out.println();
		System.out.println("Deleting the inserted record");
		System.out.println();

	}
	
	
	@Test
	@Order(5)
	public void testAttendanceInsert()
	{

		LocalDate doj=LocalDate.parse("2022-05-12");
		LocalDate dob=LocalDate.parse("2000-09-13");
		
		empdetails=new EmployeeDetails(101,"checkuser","dummy","checkusername","checkusername",doj,"xyz",dob,"xyz", "abc", "abc");
		employeerepository.save(empdetails);
		System.out.println("\nAttendance Test Cases");
		inTime = new Date();
    	outTime = new Date();
    	inTime.setTime(1000);
    	outTime.setTime(1000);
    	attendance = new Attendance(10,empdetails.getEmpId(),empdetails.getFirstName(),inTime,outTime,"DontCare");
    	attRepo.save(attendance);
    	System.out.println("\nAttendance registered");
    	List<Attendance> attendance1=attRepo.findAttendanceByEmpId(empdetails.getEmpId());
    	attRepo.deleteAll(attendance1);
    	EmployeeDetails employeedetails1=employeerepository.findByUserNameAndPassword("checkusername","checkusername");
		employeerepository.delete(employeedetails1);
		System.out.println("\n Attendance deleted");
	}
	
	
	@Test
	@Order(6)
	public void testDepartment() {
		department = new Department(1,"Checkdepartment", "XYZLocation");
    	deptrepo.save(department);
    	System.out.println("\n Department Test Cases");
    	System.out.println("\n Added dummy department");
    	deptrepo.delete(department);
   	 	System.out.println("\n Department record deleted");
    }

	
	
	
	
	
}
