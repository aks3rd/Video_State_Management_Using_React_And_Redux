package com.thinking.machines.beans;
import java.util.*;
public class UserListBean
{
public List<UserBean> userList=null;
public UserListBean()
{
this.userList=new LinkedList<UserBean>();
}
public void setUserList(List<UserBean> userList)
{
this.userList=userList;
}
public List<UserBean> getUserList()
{
return this.userList;
}
public void addUser(UserBean user)
{
this.userList.add(user);
}
}
