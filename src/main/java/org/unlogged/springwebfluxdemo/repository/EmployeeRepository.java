//package org.unlogged.springwebfluxdemo.repository;
//
//import org.springframework.data.cassandra.repository.AllowFiltering;
//import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
//import org.unlogged.springwebfluxdemo.model.Employee;
//import reactor.core.publisher.Flux;
//
//public interface EmployeeRepository extends ReactiveCassandraRepository<Employee, Integer> {
//    @AllowFiltering
//    Flux<Employee> findByAgeGreaterThan(int age);
//}