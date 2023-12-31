package com.example.notificationorderapp.model;

public class User {
	private String email;
	private String phone;
	private String username;
	private String password;
	private String location;
	private Locations nearByLoc;
	private ComponentOrder order;
	private Double balance = 0.0;

	public User() {}
	public User(String username, String email, String password, String phone, String location, Double balance, Locations nearByLocation) {
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.location = location;
		this.balance = balance;
		this.nearByLoc = nearByLocation;
	}
	
	public ComponentOrder getOrder() {
		return order;
	}
	public void setOrder(ComponentOrder order) {
		this.order = order;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Locations getNearByLoc() {
		return nearByLoc;
	}
	public void setNearByLoc(Locations nearByLoc) {
		this.nearByLoc = nearByLoc;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString(){
		return email+"::"+username+"::"+phone+"::"+location+"::"+Double.toString(balance);
	}
}
