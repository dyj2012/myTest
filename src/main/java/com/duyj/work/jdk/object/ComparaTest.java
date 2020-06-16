package com.duyj.work.jdk.object;

import java.util.*;

public class ComparaTest {

	public static void main(String[] args) {
		Student s1 = new Student(10, "5li");
		Student s2 = new Student(10, "4wang");
		Student s3 = new Student(9, "3zh");
		Student s4 = new Student(8, "2omg");
		Student s5 = new Student(19, "1hehe");

		// 比较器是必须的，否则抛异常class cannot be cast to java.lang.Comparable
		Set set = new TreeSet(new myComparator());
		set.add(s5);
		set.add(s4);
		set.add(s3);
		set.add(s2);
		set.add(s1);
		System.out.println(set);

		Teacher t1 = new Teacher(11, "5li");
		Teacher t2 = new Teacher(10, "4wang");
		Teacher t3 = new Teacher(16, "3zh");
		Teacher t4 = new Teacher(10, "2omg");
		Teacher t5 = new Teacher(19, "1hehe");
		List l = new ArrayList();
		l.add(t5);
		l.add(t4);
		l.add(t3);
		l.add(t2);
		l.add(t1);
		Collections.sort(l);
		System.out.println(l);
	}

	static class Student {
		int age;
		String name;

		Student(int age, String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public String toString() {
			return "[" + age + " , " + name + "]";
		}
	}

	static class myComparator implements Comparator<Student> {
		@Override
		public int compare(Student o1, Student o2) {
			if (o1.age != o2.age) {
				return o1.age - o2.age;
			}
			if (!o1.name.equals(o2.name)) {
				return o1.name.compareTo(o2.name);
			}
			return 0;
		}
	}

	static class Teacher implements Comparable<Teacher> {
		private int age;
		private String name;

		Teacher(int age, String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(Teacher o) {
			if (age != o.age) {
				return age - o.age;
			}
			if (!name.equals(o.name)) {
				return name.compareTo(o.name);
			}
			return 0;
		}

		@Override
		public String toString() {
			return "[" + age + " , " + name + "]";
		}
	}
}
