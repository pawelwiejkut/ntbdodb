/**
 * Created by pawelwiejkut on 20.05.2016.
 */

import classes.*;
import classes.DataFactory.impl.DataFactExt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static DataFactExt df ;
    private static List objects = new LinkedList();
    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/points.odb");
    private static EntityManager em = emf.createEntityManager();



    public static Employee generateEmployee(){
        Employee employee = new Employee();
        df = new DataFactExt();
        employee.setName(df.getEmployeeName());
        employee.setJob(df.getEmployeeJob());
        employee.setSalary(df.getEmployeeSalary());
        employee.setHiredate(df.getEmployeeHireDate());
        return employee;
    }

    public static Department generateDepartment(){
        Department department = new Department();
        df = new DataFactExt();
        department.setName(df.getDepartmentName());
        return department;
    }

    public static Project generateProject(){
        Project project = new Project();
        df = new DataFactExt();
        project.setName(df.getProjectName());
        project.setSubject(df.getProjectSubject());
        return project;
    }

    public static Address generateAddress(){
        Address address = new Address();
        df = new DataFactExt();
        address.setCity(df.getCity());
        address.setStreet(df.getAddress());
        address.setPostcode(df.getAddressLine2());
        return address;
    }

    public static Task generateTask(){
        Task task = new Task();
        df = new DataFactExt();
        task.setDescription(df.getProjectSubject());
        task.setStartDate(df.getEmployeeHireDate());
        task.setEndDate(df.getBirthDate());
        return task;
    }

    public static Applicant generateApplicant(){
        Applicant applicant= new Applicant();
        df = new DataFactExt();
        applicant.setName(df.getName());
        applicant.setPosition(df.getEmployeeJob());
        applicant.setApplicationdate(df.getBirthDate());
        return applicant;
    }


public static void createEmp() {

    for (int i = 0; i < 50; i++) {
        Employee emp;
        Employee emp1;
        Department dep;
        Address adr;
        Address adr1;
        Address adr3;
        Address adr4;
        Task task;
        Task task1;
        Project proj;
        Project proj1;
        Applicant applic;

        dep = generateDepartment();
        emp = generateEmployee();
        emp1 = generateEmployee();

        adr = generateAddress();
        adr1 = generateAddress();
        task = generateTask();
        proj = generateProject();
        proj1 = generateProject();
        adr3 = generateAddress();
        adr4 = generateAddress();
        task1 = generateTask();

        applic = generateApplicant();


        adr.setDepartment(dep);
        proj.addEmployee(emp1);
        proj.addEmployee(emp);
        proj.addTask(task);
        proj1.addEmployee(emp);
        proj1.addEmployee(emp1);
        proj1.addTask(task1);
        dep.setHead(emp);
        dep.setAddress(adr);

        emp.setAddress(adr3);
        adr3.setPerson(emp);

        applic.setAddress(adr1);
        adr1.setPerson(applic);

        task.setProject(proj);
        task1.setProject(proj1);

        emp.addProject(proj);
        emp1.addProject(proj1);
        emp.setDepartment(dep);
        emp1.setDepartment(dep);
        emp1.setAddress(adr4);
        adr4.setPerson(emp1);

        em.persist(emp);
        em.persist(adr);
        em.persist(adr1);
        em.persist(adr3);
        em.persist(adr4);
        em.persist(emp1);
        em.persist(dep);
        em.persist(proj);
        em.persist(proj1);
        em.persist(task);
        em.persist(task1);
        em.persist(applic);
    }

    em.getTransaction().begin();
    em.getTransaction().commit();
}
    public static void exercise3a() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of pepole");
        int s;
        s = input.nextInt();
        Query q1 = em.createQuery(String.format(("select address.city,count(address) from Person group by address.city having count(address)>%s"), s));
        List results = q1.getResultList();
        for (Object elem : results) {
            Object[] row = (Object[]) elem;
            System.out.println(row[0] + " " + row[1]);
        }
    }

    public static void exercise3b(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter first and second number");
        int s;
        int k;
        s = input.nextInt();
        k = input.nextInt();
        Query q1 = em.createQuery(String.format(("select address.city, avg(salary)  from Employee group by address.city having avg(salary) between %s and %s"), s,k));
        List results = q1.getResultList();
        for (Object elem : results) {
            Object[] row = (Object[]) elem;
            System.out.println(row[0] + " " + row[1]);
        }


    }


public static void main(String[] args) {
            createEmp();
            //exercise3a();
            //exercise3b();

            em.close();
            emf.close();
        }
    }

