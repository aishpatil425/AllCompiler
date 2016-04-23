package com.example.AllCompiler;

public class RegisterUser {

	private int id;
	private String Name;
	private String Email;
	private String Pass;
	
	public RegisterUser(){}
	
	public RegisterUser(String Name, String Email, String Pass) {
		super();
		this.Name = Name;
		this.Email = Email;
		this.Pass = Pass;
	}
	
	//++++++++++++++++++++++++++++++++++++
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//++++++++++++++++++++++++++++++++++++
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	//++++++++++++++++++++++++++++++++++++
	public String getemail() {
		return Email;
	}
	public void setemail(String Email) {
		this.Email = Email;
	}
	
		
	//++++++++++++++++++++++++++++++++++++
	public String getPass() {
		return Pass;
	}
	public void setPass(String Pass) {
		this.Pass = Pass;
	}
	
	//@Override
	//public String toString() {
	//	//return "Register [id=" + id + ", Name=" + Name + ", Mob=" + Mob+ ", Aadhar=" + Aadhar+ ", Voting=" + Voting+ ", Pass=" + Pass + "]";
	//}
		
}
