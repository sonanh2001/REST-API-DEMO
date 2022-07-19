package com.example.springboot.service.impl;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Employee;
import com.example.springboot.repository.EmployeeRepository;
import com.example.springboot.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
//        Optional<Employee> employeeOptional = employeeRepository.findById(id);
//        if(employeeOptional.isPresent()) {
//            return employeeOptional.get();
//        } else {
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // check employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        // check employee with given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.delete(existingEmployee);
    }

}
