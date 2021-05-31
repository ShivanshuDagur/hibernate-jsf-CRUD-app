package com.empmanagement.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.empmanagement.pojo.Employee;
import com.empmanagement.util.HibernateUtil;

/*
	This comment is added to test pull request on git hub
*/




public class DatabaseOperations {

	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();

	public String addEmployeeToDatabse(Employee newEmp)
	{
		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.save(newEmp);
			System.out.println("Employee Record With Id: " + newEmp.getEid() + " Is Successfully Created In Database");                     
		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return "index";
	}
	public String deleteEmployeeFromDatabase(int delEmployeeId) {
		System.out.println("Inside Delete from database");
		try {
			transObj = sessionObj.beginTransaction();
			Employee emp = (Employee)sessionObj.load(Employee.class, new Integer(delEmployeeId));
			sessionObj.delete(emp);
			System.out.println("Employee Record With Id: " + delEmployeeId + " Is Successfully Deleted From Database");

		} catch (Exception exceptionObj) {
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return "index";
	}
	public String updateEmployeeInDatabase(Employee empObj)
	{
		System.out.println("*****Inside update Employee database****");
		try {
			transObj = sessionObj.beginTransaction();
			sessionObj.merge(empObj);        
			System.out.println("Student Record With Id: " + empObj.getEid() + " Is Successfully Updated In Database"); 

		} catch(Exception exceptionObj){
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return "index";
	}

	public List getEmployeeTableFromDatabase()
	{
		System.out.println("***** Inside getEmployeeTableFromDatabase ****");
		try {
			transObj = sessionObj.beginTransaction();
			List empList = sessionObj.createQuery("FROM Employee").list();
			
			for (Iterator iterator = empList.iterator(); iterator.hasNext();){
				Employee emp = (Employee) iterator.next(); 
				System.out.println();
				System.out.print("Eid: " + emp.getEid()); 
				System.out.print(" Name: " + emp.getEmpname()); 
				System.out.println(" Salary: " + emp.getSalary()); 
				System.out.println(" Age: " + emp.getAge()); 
			}
			return empList;

		} catch(Exception exceptionObj){
			exceptionObj.printStackTrace();
		} finally {
			transObj.commit();
		}
		return null;
	}

}
