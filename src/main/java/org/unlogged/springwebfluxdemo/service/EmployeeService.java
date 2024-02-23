//package org.unlogged.springwebfluxdemo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.unlogged.springwebfluxdemo.model.Employee;
//import org.unlogged.springwebfluxdemo.repository.EmployeeRepository;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//@Service
//public class EmployeeService {
//
//    @Autowired
//    EmployeeRepository employeeRepository;
//
//    public void initializeEmployees(List<Employee> employees) {
//        Flux<Employee> savedEmployees = employeeRepository.saveAll(employees);
//        savedEmployees.subscribe();
//    }
//
//    public Flux<Employee> getAllEmployees() {
//        Flux<Employee> employees = employeeRepository.findAll();
//        return employees;
//    }
//
//    public Flux<Employee> getEmployeesFilterByAge(int age) {
//        return employeeRepository.findByAgeGreaterThan(age);
//    }
//
//    public Mono<Employee> getEmployeeById(int id) {
//        return employeeRepository.findById(id);
//    }
//}