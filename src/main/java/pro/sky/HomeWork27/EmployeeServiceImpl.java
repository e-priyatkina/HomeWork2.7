package pro.sky.HomeWork27;

import org.springframework.stereotype.Service;
import pro.sky.HomeWork27.exception.EmployeeAlreadyAddedException;
import pro.sky.HomeWork27.exception.EmployeeNotFoundException;
import pro.sky.HomeWork27.exception.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //коллекция сотрудников
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    //максимально возможное количество сотрудников
    final int maxEmployees = 3;
    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(newEmployee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }
        employees.put(newEmployee.getFullName(), newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee removeEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(removeEmployee.getFullName())) {
            employees.remove(removeEmployee.getFullName());
            return removeEmployee;
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(findEmployee.getFullName())) {
            return employees.get(findEmployee.getFullName());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    @Override
    public Collection<Employee> printEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}

