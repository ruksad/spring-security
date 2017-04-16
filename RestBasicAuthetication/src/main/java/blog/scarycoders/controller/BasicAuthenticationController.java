package blog.scarycoders.controller;

import blog.scarycoders.model.Employee;
import blog.scarycoders.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by mohammad on 16/4/17.
 */
@RestController
public class BasicAuthenticationController {

  @Autowired
  EmployeeService employeeService;  //Service which will do all data retrieval/manipulation work


  //-------------------Retrieve All Users--------------------------------------------------------

  @RequestMapping(value = "/user/", method = RequestMethod.GET)
  public ResponseEntity<List<Employee>> listAllUsers() {
    List<Employee> users = employeeService.findAllEmployees();
    if(users.isEmpty()){
      return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
    }
    return new ResponseEntity<List<Employee>>(users, HttpStatus.OK);
  }


  //-------------------Retrieve Single User--------------------------------------------------------

  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Employee> getUser(@PathVariable("id") long id) {
    System.out.println("Fetching User with id " + id);
    Employee user = employeeService.findById(id);
    if (user == null) {
      System.out.println("User with id " + id + " not found");
      return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Employee>(user, HttpStatus.OK);
  }



  //-------------------Create a User--------------------------------------------------------

  @RequestMapping(value = "/user/", method = RequestMethod.POST)
  public ResponseEntity<Void> createUser(@RequestBody Employee user, UriComponentsBuilder ucBuilder) {
    System.out.println("Creating User " + user.getName());

    if (employeeService.isEmployeeExist(user)) {
      System.out.println("A User with name " + user.getName() + " already exist");
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    employeeService.saveEmployee(user);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }


  //------------------- Update a User --------------------------------------------------------

  @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Employee> updateUser(@PathVariable("id") long id, @RequestBody Employee user) {
    System.out.println("Updating User " + id);

    Employee currentUser = employeeService.findById(id);

    if (currentUser==null) {
      System.out.println("User with id " + id + " not found");
      return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

    currentUser.setName(user.getName());
    currentUser.setAge(user.getAge());
    currentUser.setSalary(user.getSalary());

    employeeService.updateEmployee(currentUser);
    return new ResponseEntity<Employee>(currentUser, HttpStatus.OK);
  }

  //------------------- Delete a User --------------------------------------------------------

  @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Employee> deleteUser(@PathVariable("id") long id) {
    System.out.println("Fetching & Deleting User with id " + id);

    Employee user = employeeService.findById(id);
    if (user == null) {
      System.out.println("Unable to delete. User with id " + id + " not found");
      return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

    employeeService.deleteEmployeeById(id);
    return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
  }


  //------------------- Delete All Users --------------------------------------------------------

  @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
  public ResponseEntity<Employee> deleteAllUsers() {
    System.out.println("Deleting All Users");

    employeeService.deleteAllEMployees();
    return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
  }
}
