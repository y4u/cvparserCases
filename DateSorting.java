package reader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class DateSorting {

	public static void main(String[] args) {
		
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("hari", LocalDate.of(2015, 03, 20),25));
		students.add(new Student("krishna", LocalDate.of(2016, 04, 20),25));
		students.add(new Student("vamsi", LocalDate.of(2015, 03, 21),25));
		students.add(new Student("VamsiKrishna", LocalDate.of(2016, 04, 19),25));
		
		students.forEach((s)-> System.out.println(s));
		
		System.out.println("***********************SortedData*****************");	
		
		
		students.sort(Comparator.comparing(Student::getBirthDay));
		students.forEach((s)-> System.out.println(s));
		
		System.out.println("***********************SortedData*****************");	
		
		students.sort((Student s1 , Student s2)->s1.getBirthDay().compareTo(s2.getBirthDay()));
		
		System.out.println("***********************SortedData*****************");	
		
		students.forEach((s)-> System.out.println(s));
	
	}

}
