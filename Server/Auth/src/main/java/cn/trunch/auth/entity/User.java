package cn.trunch.auth.entity;


public class User {

  private String userId;
  private String userPassword;
  private String userName;
  private String userEmail;
  private long userPhone;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public long getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(long userPhone) {
    this.userPhone = userPhone;
  }

}
