package com.itmayiedu;

//构建任务 整合所有部件。
public class PersonDirector {

	public Person createPerson(PersonBuilder personBuilder) {
		personBuilder.builderHead();
		personBuilder.builderBody();
		personBuilder.builderFoot();
		return personBuilder.builderPerson();
	}

	public static void main(String[] args) {
		PersonDirector personDirector = new PersonDirector();
		Person person = personDirector.createPerson(new ManBuilder());
		System.out.println(person.getHead());
		System.out.println(person.getBody());
		System.out.println(person.getFoot());
	}

}
