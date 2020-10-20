package com.thinking.machines.userservlets;
import com.thinking.machines.beans.*;
import com.thinking.machines.annotations.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.google.gson.*;
//import org.json.simple.*;
//import org.json.simple.parser.*;
import java.sql.*;
@Path("/Login")
public class LoginValidationServlet
{
@ResponseType("JSON")
@Path("/loginValidation")
public AJAXResponse loginValidation(HttpServletRequest request,HttpServletResponse response,ServletContext servletContext,String urlString)
{
System.out.println("\n\n\n\nLoginValidationServlet is processed\n\n\n\n");
//UserBean otherUser
//System.out.println(otherUser.getLastVisitedURL());
System.out.println(urlString);
UserBean ub=null;

AJAXResponse ajaxResponse=null;
Gson gson=new Gson();
try
{
HttpSession hs=request.getSession(false);
if(hs!=null)
{
ub=(UserBean)hs.getAttribute("loggedUser");
String userId=ub.getUsername();
String password=ub.getPassword();
if(ub!=null)
{

String realPath=servletContext.getRealPath("");
System.out.println("Real path : "+realPath);
String fileName="";
File file=null;


fileName=realPath+"WEB-INF"+File.separator+"useraccount"+File.separator+"users.data";
file=new File(fileName);
System.out.println("Yaha tak aaya 1");
if(file.exists())
{
System.out.println("Yaha tak aaya 2");
   FileInputStream fis =new FileInputStream(file);
   byte[] data = new byte[(int)file.length()];
   fis.read(data);
   fis.close();
   String str = new String(data, "UTF-8");	
   Gson g=new Gson();
   UserListBean userList=g.fromJson(str,UserListBean.class);
   java.util.List<UserBean> listOfUsers=userList.getUserList();
   boolean found=false;
   for(UserBean user: listOfUsers)
   {
	System.out.println("User : "+user.getUsername());
	if(userId.trim().equals(user.getUsername()))
        {
          user.setLastVisitedURL(urlString);
	  ub.setLastVisitedURL(user.getLastVisitedURL());
          ub.setVideoList(user.getVideoList());
          found=true;
          break;
        }
   }
   if(found)
   {
     file.delete();
     file.createNewFile();
     FileWriter fileWriter=new FileWriter(file);
     gson.toJson(userList,fileWriter);
     fileWriter.close();
   }
}
ajaxResponse=new AJAXResponse();
ajaxResponse.setResponse(ub);
ajaxResponse.setSuccess(true);
ajaxResponse.setException("");
return ajaxResponse;
}
else
{
ajaxResponse=new AJAXResponse();
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException("Please go to login page");
return ajaxResponse;
}//ub if ends
}
else
{
ajaxResponse=new AJAXResponse();
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException("Session not found");
return ajaxResponse;
}//hs id ends

}catch(Exception exception)
{
ajaxResponse=new AJAXResponse();
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException(exception.getMessage());
return ajaxResponse;
}

//return ajaxResponse;
}

@ResponseType("JSON")
@Path("/login")
public AJAXResponse login(HttpServletRequest request,HttpServletResponse response,ServletContext servletContext,UserBean userBean)
{
AJAXResponse ajaxResponse=null;
try
{
System.out.println("\n\n\n\nLoginServlet is processed\n\n\n\n");
System.out.println("User : "+userBean.getUsername()+",Password : "+userBean.getPassword()+",VideList : "+userBean.getVideoList());
String userId=userBean.getUsername();
String password=userBean.getPassword();

if( ( userId.equals("akashsoni") || userId.equals("viveksoni") ) && !(password.equals("akashsoni")) && !(password.equals("viveksoni")) )
{
ajaxResponse=new AJAXResponse();	
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException("Invalid Password");
return ajaxResponse;
}
else if(!(userId.equals("akashsoni")||userId.equals("viveksoni")))
{
System.out.println("User id invalid\n\n\n");
ajaxResponse=new AJAXResponse();	
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException("Invalid UserId..");

return ajaxResponse;
}

if((userId.equals("akashsoni")||userId.equals("viveksoni")) && ( (password.equals("akashsoni")) || (password.equals("viveksoni")) ))
{
UserBean ub=new UserBean();
ub.setUsername(userId);
ub.setPassword(password);
ub.setLastVisitedURL("");
  
Gson gson=new Gson();

String realPath=servletContext.getRealPath("");
System.out.println("Real path : "+realPath);
String fileName="";
File file=null;


fileName=realPath+"WEB-INF"+File.separator+"useraccount"+File.separator+"users.data";
file=new File(fileName);
System.out.println("Yaha tak aaya 1");
if(file.exists())
{
System.out.println("Yaha tak aaya 2");
   FileInputStream fis =new FileInputStream(file);
   byte[] data = new byte[(int)file.length()];
   fis.read(data);
   fis.close();
   String str = new String(data, "UTF-8");	
   Gson g=new Gson();
   UserListBean userList=g.fromJson(str,UserListBean.class);
   java.util.List<UserBean> listOfUsers=userList.getUserList();
   boolean found=false;
   for(UserBean user: listOfUsers)
   {
	System.out.println("User : "+user.getUsername()+",UserLastVisitedURL : "+user.getLastVisitedURL());
	if(userId.trim().equals(user.getUsername()))
        {
          System.out.println("Yaha tak aaya 3");
	  ub.setLastVisitedURL(user.getLastVisitedURL());
          ub.setVideoList(user.getVideoList());
          found=true;
          break;
        }
   }
   if(!found)
   {
     userList.addUser(ub);
     file.delete();
     file.createNewFile();
     FileWriter fileWriter=new FileWriter(file);
     gson.toJson(userList,fileWriter);
     fileWriter.close();
   }
}
else
{
System.out.println("Yaha tak aaya 4");
  UserListBean userList=new UserListBean();
  ub.setLastVisitedURL("");
  userList.addUser(ub);

  if(!file.getParentFile().exists())file.getParentFile().mkdirs(); 
  if(file.createNewFile())
  {
   FileWriter fileWriter=new FileWriter(file);
System.out.println("Yaha tak aaya 5");
   gson.toJson(userList,fileWriter);
   fileWriter.close();
   String responseString=gson.toJson(userList);
   System.out.println(responseString);
  }
}

HttpSession hs=request.getSession();
hs.setAttribute("loggedUser",ub);
hs.setMaxInactiveInterval(60*10);

ajaxResponse=new AJAXResponse();	
ajaxResponse.setResponse(ub);
ajaxResponse.setSuccess(true);
ajaxResponse.setException("");
return ajaxResponse;
}

}catch(Exception exception)
{
try{
ajaxResponse=new AJAXResponse();	
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException(exception.getMessage());
return ajaxResponse;
}catch(Exception e){}
System.out.println("LOGIN : "+exception);
}
/*
String dbFileName="/home/asoni/c/TMORMFramework/db/dbconf.json";
   File dbListFile=new File(dbFileName);
   FileInputStream fis =new FileInputStream(dbListFile);
   byte[] data = new byte[(int) dbListFile.length()];
   fis.read(data);
   fis.close();
   String str = new String(data, "UTF-8");	
   Gson g=new Gson();
   DatabaseList databaseList=g.fromJson(str,DatabaseList.class);
   java.util.List<DAOConnection> listOfDatabases=databaseList.getList();
   for(DAOConnection database: listOfDatabases)
   {
     	DBAnalyzer.analyzeDatabase(database);
	DBAnalyzer.printDescription();
   }

*/
/*

try
{
String userId=userBean.getUsername();
String password=userBean.getPassword();
System.out.println("User ID : "+userId+", Password : "+password);
//ServletContext sc=getServletContext();
ServletContext sc=servletContext;
StudentModel em=(StudentModel)sc.getAttribute("model");
Connection c=em.getDAOConnection().getConnection();

if(c!=null)
{
ResultSet resultSet;
PreparedStatement ps=c.prepareStatement("select * from administrator where username=?;");
ps.setString(1,userId);
resultSet=ps.executeQuery();
if(resultSet.next())
{
String user=resultSet.getString("username");
String encryptPassword=resultSet.getString("password");
String passwordKey=resultSet.getString("password_key");
String decryptPassword=AES.decrypt(encryptPassword,passwordKey);
System.out.println("User : "+user+",Encrypt Password : "+encryptPassword+", Password Key : "+passwordKey+",Decrypt Password : "+decryptPassword);
if(userId.equals(user) && !(password.equals(decryptPassword)))
{
ajaxResponse=new AJAXResponse();	
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException("Invalid Password");
ps.close();
resultSet.close();
return ajaxResponse;
}
if(userId.equals(user) && password.equals(decryptPassword))
{
UserBean ub=new UserBean();
ub.setUsername(user);
ub.setPassword(decryptPassword);
HttpSession hs=request.getSession();
hs.setAttribute("loggedUser",ub);
hs.setMaxInactiveInterval(60*10);

ajaxResponse=new AJAXResponse();	
ajaxResponse.setResponse(ub);
ajaxResponse.setSuccess(true);
ajaxResponse.setException("");
ps.close();
resultSet.close();
return ajaxResponse;
}
}
else
{
System.out.println("User id invalid\n\n\n");
ajaxResponse=new AJAXResponse();	
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException("Invalid UserId..");
ps.close();
resultSet.close();
return ajaxResponse;
}
}
else
{
ajaxResponse=new AJAXResponse();	
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException("Connection Failed..");
return ajaxResponse;
}
}catch(Exception exception)
{
try{
ajaxResponse=new AJAXResponse();	
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException(exception.getMessage());
return ajaxResponse;
}catch(Exception e){}
System.out.println("LOGIN : "+exception);
}
*/
return ajaxResponse;
}



}
