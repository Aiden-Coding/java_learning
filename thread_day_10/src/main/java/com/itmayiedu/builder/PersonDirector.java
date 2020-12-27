package com.itmayiedu.builder;

public class PersonDirector {

	public Person constructPerson(PersonBuilder pb) {
		pb.builderHead();
		pb.builderBody();
		pb.builderFoot();
		return pb.BuilderPersion();
	}

	public static void main(String[] args) {
		PersonDirector pb = new PersonDirector();
		Person person = pb.constructPerson(new ManBuilder());
		System.out.println(person.getHead());
		System.out.println(person.getBody());
		System.out.println(person.getFoot());
	}
}
