package com.example.springboot.controller;

import com.example.springboot.model.Employee;
import com.example.springboot.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("")
    //build create employee REST API
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //build get all employees REST API
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    // build get employee by ID
    // http://localhost:8080/api/employees/1
    @GetMapping("{employeeID}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeID") long employeeID) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeID), HttpStatus.OK);
    }

    //build update employee REST API
    @PutMapping("{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,
                                                   @PathVariable("employeeId") long id) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    //build delete employee REST API

    @DeleteMapping("{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }

}
