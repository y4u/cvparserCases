package reader;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author agile
 *
 */
public class Student {
	
	private String name ;
	private LocalDate birthDay ;
	private int age ;
	public Student(String name, LocalDate localDate, int age) {
		super();
		this.name = name;
		this.birthDay = localDate;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", birthDay=" + birthDay + ", age=" + age + "]";
	}
	
	
}
