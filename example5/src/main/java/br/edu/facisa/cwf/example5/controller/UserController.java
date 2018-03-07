package br.edu.facisa.cwf.example5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.facisa.cwf.example5.domain.User;
import br.edu.facisa.cwf.example5.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {

		List<User> listaUsuarios = userService.listAllUsers();

		HttpStatus status;

		status = listaUsuarios != null && !listaUsuarios.isEmpty() 
				? HttpStatus.OK : HttpStatus.NOT_FOUND;

		return new ResponseEntity<List<User>>(listaUsuarios, status);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable String id) {

		User user = userService.getById(id);

		return user == null ? new ResponseEntity<User>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody User user) {

		HttpStatus status;
		
		try {
			userService.save(user);
			status = HttpStatus.CREATED;

		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(status);
	}

	public UserService getUserService() {
		return userService;
	}
}
