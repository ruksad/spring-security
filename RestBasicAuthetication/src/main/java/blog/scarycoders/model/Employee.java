package blog.scarycoders.model;

/**
 * Created by mohammad on 16/4/17.
 */
public class Employee {
  private long id;

  private String name;

  private int age;

  private double salary;

  public Employee(long id, String name, int age, double salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Employee employee = (Employee) o;

    if (id != employee.id) {
      return false;
    }
    if (age != employee.age) {
      return false;
    }
    if (Double.compare(employee.salary, salary) != 0) {
      return false;
    }
    return name != null ? name.equals(employee.name) : employee.name == null;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = (int) (id ^ (id >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + age;
    temp = Double.doubleToLongBits(salary);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
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
