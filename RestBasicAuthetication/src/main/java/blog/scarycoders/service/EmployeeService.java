package blog.scarycoders.service;

import blog.scarycoders.model.Employee;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by mohammad on 16/4/17.
 */

public interface EmployeeService {

  Employee findById(long id);

  Employee findByName(String name);

  void saveEmployee(Employee user);

  void updateEmployee(Employee user);

  void deleteEmployeeById(long id);

  List<Employee> findAllEmployees();

  void deleteAllEMployees();

  public boolean isEmployeeExist(Employee user);
}
