package entity;

public class Employee {
	@Override
	public String toString() {
		return "employee [id=" + id + ", name=" + name + ", department=" + department + ", Salary=" + Salary + ", city="
				+ city + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSalary() {
		return Salary;
	}
	public void setSalary(String salary) {
		Salary = salary;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Employee(String id, String name, String department, String salary, String city) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.Salary = salary;
		this.city = city;
	}
	String id;
	String name;
	String department;
	String Salary;
	String city;
}
