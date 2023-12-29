/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.service;

import src.model.User;

/**
 *
 * @author ahmed.mansour
 */
public interface UserService {

	// register
	public Boolean addUser(User p);
	
	// check user (Login)
	public User getUser(String username,  String password);
	
	// add balance
	public Boolean addBalance (User user, Double balance);

}
