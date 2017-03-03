/**
 * 
 */
package com.all_union.es.esalarm.controller.rest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.all_union.es.esalarm.dao.user.UserQuery;
import com.all_union.es.esalarm.pojo.user.UserDo;
import com.all_union.es.esalarm.service.user.UserService;

/**
 * @Description: restful controller for user
 * @author kwm
 * @date 2017年2月26日 下午2:05:24
 * @version V1.0
 * 
 */
@RestController
@RequestMapping("/rest")
public class UserRestController {

	private static Logger logger = LogManager.getLogger(UserRestController.class);

	@Autowired
	UserService userService;

	// http://localhost:8080/esalarm/rest/user/
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<UserDo>> listAllUsers() {
		UserQuery query = new UserQuery();
		List<UserDo> users = userService.listUserByUserName(query);
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDo>>(HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<UserDo>>(users, HttpStatus.OK);
	}
	// get single user
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)  
    public ResponseEntity<UserDo> getUser(@PathVariable("id") int id) {  
        logger.debug("Fetching User with id " + id);  
        UserDo user = userService.getUserById(id);
        if (user == null) {  
            logger.debug("User with id " + id + " not found");  
            return new ResponseEntity<UserDo>(HttpStatus.NOT_FOUND);  
        }  
        return new ResponseEntity<UserDo>(user, HttpStatus.OK);  
    }
    
	// create a user
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody UserDo user, UriComponentsBuilder ucBuilder) {

		logger.debug("Creating User " + user.getUserName());
		
		if (userService.getUserById(user.getId()) != null) {
			logger.debug("A User with name " + user.getUserName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.insertSelective(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// Update a User
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDo> updateUser(@PathVariable("id") int id, @RequestBody UserDo user) {
		logger.debug("Updating User " + id);

		UserDo currentUser = userService.getUserById(id);

		if (currentUser == null) {
			logger.debug("User with id " + id + " not found");
			return new ResponseEntity<UserDo>(HttpStatus.NOT_FOUND);
		}

		currentUser.setUserName(user.getUserName());
		currentUser.setAge(user.getAge());
		currentUser.setPassword(user.getPassword());

		userService.updateByPrimaryKeySelective(currentUser);

		return new ResponseEntity<UserDo>(currentUser, HttpStatus.OK);
	}

	// Delete a User
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDo> deleteUser(@PathVariable("id") int id) {
		logger.debug("Fetching & Deleting User with id " + id);

		UserDo user = userService.getUserById(id);
		if (user == null) {
			logger.debug("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<UserDo>(HttpStatus.NOT_FOUND);
		}

		userService.deleteByPrimaryKey(id);
		return new ResponseEntity<UserDo>(HttpStatus.NO_CONTENT);
	}

	// Delete All Users
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<UserDo> deleteAllUsers() {
		logger.debug("Deleting All Users");

		// userService.deleteAllUsers();
		return new ResponseEntity<UserDo>(HttpStatus.NO_CONTENT);
	}
}
