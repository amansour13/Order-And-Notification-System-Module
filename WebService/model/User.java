import java.util.Map;

public class User {
	private String email;
	private String phone;
	private String username;
	private String password;
	private String location;
	private Loc nearByLoc;
	private Map<Integer, ComponentOrder> orders;
	private Float balance;
	private boolean isLogged;

	
	
	@Override
	public String toString(){
		return email+"::"+username+"::"+phone;
	}
}
