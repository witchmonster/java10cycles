package com.juliakram.labs.employeesort;

import com.juliakram.labs.employeesort.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.juliakram.labs.employeesort.EmployeeCompare.*;
import static org.junit.Assert.assertEquals;

public class EmployeeCompareTest {

    public static final List<Employee> employees;

    static {
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(new Employee(1, "Lannister Thyrion", 34, 1000));
        employeesList.add(new Employee(2, "Arya Stark", 14, 10));
        employeesList.add(new Employee(3, "Jon Snow", 20, 1000));
        employeesList.add(new Employee(4, "Daenerys Targaryen", 21, 1000000));
        employeesList.add(new Employee(5, "Khal Drogo", 30, 10000));
        employees = employeesList;
    }

    @Test
    public void testCompareByAge() throws Exception {
        employees.sort(BY_AGE_ASCENDING);
        assertEquals("[2, 3, 4, 5, 1]", collectEmployeeIdsToList().toString());
        System.out.println("AGE:ASCENDING --- " + employees);
        employees.sort(BY_AGE_DESCENDING);
        assertEquals("[1, 5, 4, 3, 2]", collectEmployeeIdsToList().toString());
        System.out.println("AGE:DESCENDING --- " + employees);
    }

    private List<Integer> collectEmployeeIdsToList() {
        return employees.stream().map(Employee::getId).collect(Collectors.toList());
    }

    @Test
    public void testCompareByName() throws Exception {
        employees.sort(BY_NAME_ASCENDING);
        assertEquals("[2, 4, 3, 5, 1]", collectEmployeeIdsToList().toString());
        employees.sort(BY_NAME_DESCENDING);
        assertEquals("[1, 5, 3, 4, 2]", collectEmployeeIdsToList().toString());
    }

    @Test
    public void testCompareByAgeThenName() throws Exception {
        employees.sort(BY_AGE_THEN_NAME_ASCENDING);
        assertEquals("[2, 3, 4, 5, 1]", collectEmployeeIdsToList().toString());
        employees.sort(BY_AGE_AND_NAME_DESCENDING);
        assertEquals("[1, 5, 4, 3, 2]", collectEmployeeIdsToList().toString());
    }

    @Test
    public void testCompareBySalary() throws Exception {
    //discrepancy in reversed sorting because we have same salaries and do not perform backup sort by name
        employees.sort(BY_SALARY_ASCENDING);
        assertEquals("[2, 1, 3, 5, 4]", collectEmployeeIdsToList().toString());
        employees.sort(BY_SALARY_DESCENDING);
        assertEquals("[4, 5, 1, 3, 2]", collectEmployeeIdsToList().toString());
    }

    @Test
    public void testCompareBySalaryThenName() throws Exception {
    //all fine here
        employees.sort(BY_SALARY_THEN_NAME_ASCENDING);
        assertEquals("[2, 3, 1, 5, 4]", collectEmployeeIdsToList().toString());
        employees.sort(BY_SALARY_THEN_NAME_DESCENDING);
        assertEquals("[4, 5, 1, 3, 2]", collectEmployeeIdsToList().toString());
    }
}