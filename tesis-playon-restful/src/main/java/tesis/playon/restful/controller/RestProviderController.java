package tesis.playon.restful.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.Person;
import tesis.playon.restful.domain.PersonList;
import tesis.playon.restful.service.PersonService;

/**
 * REST service provider
 * 
 * Only GET and POST will return values PUT and DELETE will not.
 */
@Controller
public class RestProviderController {

    @Resource(name = "personService")
    private PersonService personService;

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, headers = "Accept=image/jpeg, image/jpg, image/png, image/gif")
    public @ResponseBody
    byte[] getPhoto(@PathVariable("id") Long id) {

	// Call service here
	try {
	    // Retrieve image from the classpath
	    InputStream is = this.getClass().getResourceAsStream("/bella.jpg");

	    // Prepare buffered image
	    BufferedImage img = ImageIO.read(is);

	    // Create a byte array output stream
	    ByteArrayOutputStream bao = new ByteArrayOutputStream();

	    // Write to output stream
	    ImageIO.write(img, "jpg", bao);

	    return bao.toByteArray();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, headers = "Accept=application/html, application/xhtml+xml")
    public String getPersonHTML(@PathVariable("id") Long id, Model model) {
	// Call service to here
	model.addAttribute("person", personService.get(id));

	// This will resolve to /WEB-INF/jsp/getpage.jsp
	return "getpage";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    PersonList getPerson() {
	// Call service here
	PersonList result = new PersonList();
	result.setData(personService.getAll());
	return result;
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    Person getPerson(@PathVariable("id") Long id) {
	// Call service here
	return personService.get(id);
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    Person addPerson(@RequestBody Person person) {
	// Call service to here
	return personService.add(person);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    String updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
	// Call service here
	person.setId(id);
	return personService.edit(person).toString();
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    String deleteEmployee(@PathVariable("id") Long id) {
	// Call service here
	return personService.delete(id).toString();
    }
}
