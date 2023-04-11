package join;

import java.sql.Date;

public class CustomerVO {
   private String id;
   private String password;
   private String name;
   private Date birthDate;
   private String phonenumber;
   private String email;
   private String address;
   private double loansnumber;
   
   public CustomerVO() {
   }
   
   public CustomerVO(String id, String password) {
  	 this.id = id;
  	 this.password = password;
   }
   
   public CustomerVO (String id, String password, String name, Date birthDate, String phonenumber, String email, String address, double loansnumber) {
  	 this.id = id;
  	 this.password = password;
  	 this.name = name;
  	 this.birthDate = birthDate;
  	 this.phonenumber = phonenumber;
  	 this.email = email;
  	 this.address = address;
  	 this.loansnumber = loansnumber;
   }
   
   public String getId() {
  	 return id;
   }
   
   public void setId(String id) {
  	 this.id = id;
   }
   
   public String getPassword() {
  	 return password;
   }
   
   public void setPassword(String password) {
  	 this.password = password;
   }
   
   public String getName() {
  	 return name;
   }
   
   public void setName(String name) {
  	 this.name = name;
   }
   
   public Date getBirthDate() {
  	 return birthDate;
   }
   
   public void setBirthDate(Date birthDate) {
  	 this.birthDate = birthDate;
   }
   
   public String getPhonenumber() {
  	 return phonenumber;
   }
   
   public void setPhonenumber(String phonenumber) {
  	 this.phonenumber = phonenumber;
   }
   
   public String getEmail() {
  	 return email;
   }
   
   public void setEmail(String email) {
  	 this.email = email;
   }
   
   public String getAddress() {
  	 return address;
   }
   
   public void setAddress(String address) {
  	 this.address = address;
   }
   
   public double getLoansnumber() {
  	 return loansnumber;
   }
   
   public void setLoansnumber(double loansnumber) {
  	 this.loansnumber = loansnumber;
   }
}
