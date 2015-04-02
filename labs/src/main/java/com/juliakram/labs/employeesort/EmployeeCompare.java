package com.juliakram.labs.employeesort;


import com.juliakram.labs.employeesort.entity.Employee;

import java.util.Comparator;

/**
 * Created by yuliya.kramarenko on 20.12.2014.
 */
public class EmployeeCompare {

    public static final Comparator<Employee> BY_AGE_ASCENDING = (e1, e2) -> e1.getAge().compareTo(e2.getAge());

    public static final Comparator<Employee> BY_NAME_ASCENDING = (e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName());

    public static final Comparator<Employee> BY_SALARY_ASCENDING = (e1, e2) -> e1.getSalary().compareTo(e2.getSalary());

    public static final Comparator<Employee> BY_AGE_THEN_NAME_ASCENDING = BY_AGE_ASCENDING.thenComparing(BY_NAME_ASCENDING);

    public static final Comparator<Employee> BY_SALARY_THEN_NAME_ASCENDING = BY_SALARY_ASCENDING.thenComparing(BY_NAME_ASCENDING);

    public static final Comparator<Employee> BY_AGE_DESCENDING = BY_AGE_ASCENDING.reversed();

    public static final Comparator<Employee> BY_NAME_DESCENDING = BY_NAME_ASCENDING.reversed();

    public static final Comparator<Employee> BY_SALARY_DESCENDING = BY_SALARY_ASCENDING.reversed();

    public static final Comparator<Employee> BY_AGE_AND_NAME_DESCENDING = BY_AGE_THEN_NAME_ASCENDING.reversed();

    public static final Comparator<Employee> BY_SALARY_THEN_NAME_DESCENDING = BY_SALARY_THEN_NAME_ASCENDING.reversed();


}
