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
@Path("/Logout")
public class LogoutServlet
{
@ResponseType("JSON")
@Path("/logout")
public AJAXResponse logout(HttpServletRequest request,HttpServletResponse response,ServletContext servletContext)
{
System.out.println("\n\n\n\nLogoutServlet is processed\n\n\n\n");
AJAXResponse ajaxResponse=null;
try
{
HttpSession hs=request.getSession(false);
if(hs!=null)
{
UserBean ub=(UserBean)hs.getAttribute("loggedUser");
if(ub!=null)
{
String user=ub.getUsername();
String password=ub.getPassword();
System.out.println("User ID : "+user+", Password : "+password);

hs.removeAttribute("loggedUser");
hs.setMaxInactiveInterval(0);
//hs.invalidate();
ajaxResponse=new AJAXResponse();
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(true);
ajaxResponse.setException("");

System.out.println("\n\n\n\nLogoutServlrt Process Complete....");
return ajaxResponse;

}// if ends of userBean

}// if ends of hs


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
return ajaxResponse;
}
}
