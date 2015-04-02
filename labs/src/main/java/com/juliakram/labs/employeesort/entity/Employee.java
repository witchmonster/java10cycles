package com.juliakram.labs.employeesort.entity;

/**
 * Created by yuliya.kramarenko on 19.12.2014.
 */
public class Employee {

    private final Integer id;
    private final String name;
    private final Integer age;
    private final Integer salary;

    public Employee(Integer id, String name, Integer age, Integer salary) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || o instanceof Employee
                && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
