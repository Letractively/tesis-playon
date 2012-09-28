package tesis.playon.restful.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Person;

@Service("personService")
public class PersonService {

    // In-memory list
    private List<Person> persons = new ArrayList<Person>();

    public PersonService() {

	// Create in-memory list
	Person person1 = new Person();
	person1.setId(0L);
	person1.setFirstName("John");
	person1.setLastName("Smith");
	person1.setMoney(1000.0);

	Person person2 = new Person();
	person2.setId(1L);
	person2.setFirstName("Jane");
	person2.setLastName("Adams");
	person2.setMoney(2000.0);

	Person person3 = new Person();
	person3.setId(2L);
	person3.setFirstName("Jeff");
	person3.setLastName("Mayer");
	person3.setMoney(3000.0);

	persons.add(person1);
	persons.add(person2);
	persons.add(person3);
    }

    /**
     * Retrieves all persons
     */
    public List<Person> getAll() {
	return persons;
    }

    /**
     * Retrieves a single person
     */
    public Person get(Long id) {
	for (Person person : persons) {
	    if (person.getId().longValue() == id.longValue()) {
		return person;
	    }
	}
	return null;
    }

    /**
     * Adds a new person
     */
    public Person add(Person person) {
	try {
	    // Find suitable id
	    Long newId = 0L;
	    Boolean hasFoundSuitableId = false;
	    while (hasFoundSuitableId == false) {
		for (int i = 0; i < persons.size(); i++) {
		    if (persons.get(i).getId().longValue() == newId.longValue()) {
			newId++;
			break;
		    }

		    // Exit while loop
		    if (i == persons.size() - 1) {
			hasFoundSuitableId = true;
		    }
		}
	    }

	    // Assign new id
	    person.setId(newId);
	    // Add to list
	    persons.add(person);

	    return person;
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * Deletes an existing person
     */
    public Boolean delete(Long id) {
	try {
	    for (Person person : persons) {
		if (person.getId().longValue() == id.longValue()) {
		    persons.remove(person);
		    return true;
		}
	    }
	    return false;
	} catch (Exception e) {
	    return false;
	}
    }

    /**
     * Edits an existing person
     */
    public Boolean edit(Person person) {
	try {
	    for (Person p : persons) {
		if (p.getId().longValue() == person.getId().longValue()) {
		    persons.remove(p);
		    persons.add(person);
		    return true;
		}
	    }
	    return false;
	} catch (Exception e) {
	    return false;
	}
    }
}