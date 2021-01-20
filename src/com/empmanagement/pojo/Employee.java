package com.empmanagement.pojo;

import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.SessionScoped;

import com.empmanagement.dao.DatabaseOperations;

@ManagedBean
public class Employee  implements java.io.Serializable  {


	private int eid;
	private String empname;
	private int salary;
	private int age;
	private static ArrayList<Employee> employees = null;
	//private  ArrayList<Employee> employees = new ArrayList<Employee>(Arrays.asList(new Employee(001, "Rahul", 302000,22),  new Employee(002, "Shivansu", 353000,23))); 
	private static final long serialVersionUID = 1L;
	public static DatabaseOperations dbObj;


	 public ArrayList<Employee> getEmployees() {
	      return employees;
	   }
   
	public Employee() {

	}

	public Employee(int eid, String empname, int salary, int age) {

		this.eid = eid;
		this.empname = empname;
		this.salary = salary;
		this.age = age;
	}
	
	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String saveEmployeeRecord()
	{
		System.out.println("Inside save employee record");
		dbObj =  new DatabaseOperations();
		String location = dbObj.addEmployeeToDatabse(this);
		return location;
	}
	public String deleteEmployeeRecord()
	{
		System.out.println("Inside Delete Employee Record");
		System.out.println("Employee id: "+this.eid);
		dbObj =  new DatabaseOperations();
		String location = dbObj.deleteEmployeeFromDatabase(this.eid);
		return location;
	}
	public String updateEmployeeDetails()
	{
		System.out.print("Inside Update Employee Details");
		dbObj = new DatabaseOperations();
		String location = dbObj.updateEmployeeInDatabase(this);
		return location;
	}

	public String getEmployeeTableData()
	{
		System.out.println("Inside Get Employee table data");
		dbObj = new DatabaseOperations();
		employees = (ArrayList<Employee>)dbObj.getEmployeeTableFromDatabase();
		System.out.println("employees: "+employees);
		return "index";
	}



}
