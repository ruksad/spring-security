package blog.scarycoders.service;

import blog.scarycoders.model.Employee;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/**
 * Created by mohammad on 16/4/17.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {


  private static final AtomicLong counter = new AtomicLong();

  private static List<Employee> employees;

  static{
    employees= populateDummyEmployee();
  }


  @Override
  public Employee findById(long id) {
    for(Employee employe : employees){
      if(employe.getId() == id){
        return employe;
      }
    }
    return null;
  }


  @Override
  public Employee findByName(String name) {
    for(Employee employe : employees){
      if(employe.getName().equalsIgnoreCase(name)){
        return employe;
      }
    }
    return null;
  }

  @Override
  public void saveEmployee(Employee employe) {
    employe.setId(counter.incrementAndGet());
    employees.add(employe);
  }

  @Override
  public void updateEmployee(Employee employe) {
    int index = employees.indexOf(employe);
    employees.set(index, employe);
  }

  @Override
  public void deleteEmployeeById(long id) {
    for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext(); ) {
      Employee user = iterator.next();
      if (user.getId() == id) {
        iterator.remove();
      }
    }
  }

  @Override
  public List<Employee> findAllEmployees() {
    return employees;
  }

  @Override
  public void deleteAllEMployees() {
    employees.clear();
  }

  @Override
  public boolean isEmployeeExist(Employee employe) {
    return findByName(employe.getName())!=null;
  }


  private static List<Employee> populateDummyEmployee(){
    List<Employee> Employees = new ArrayList<Employee>();
    Employees.add(new Employee(counter.incrementAndGet(),"Derric",25, 20000));
    Employees.add(new Employee(counter.incrementAndGet(),"Kennen",30, 50000));
    Employees.add(new Employee(counter.incrementAndGet(),"Jovin",50, 310000));
    Employees.add(new Employee(counter.incrementAndGet(),"Rebecca",42, 410000));
    return Employees;
  }
}
