package entities;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String role;
	private String password;
	private String name;
	public void setRole(String role) {
		this.role = role;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User(int id,String name,String password,String role) {
		this.role = role;
		this.password = password;
		this.name = name;
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "User [role=" + role + ", password=" + password + ", name="
				+ name + "]";
	}
	/*@Override
	public int compareTo(User o) {
		if(this.name.equals(o.getName())&&this.password.equals(o.getPassword())&&this.role.equals(o.getRole()))
			return 0;
		return 1;
	}*/
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		User o = (User)obj;
		
		return this.name.equals(o.getName())&&this.password.equals(o.getPassword())&&this.role.equals(o.getRole());
	}
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
}
