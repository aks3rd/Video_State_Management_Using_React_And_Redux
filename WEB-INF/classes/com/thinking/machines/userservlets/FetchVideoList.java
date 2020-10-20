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
@Path("/Video")
public class FetchVideoList
{
@ResponseType("JSON")
@Path("/update")
public void update(HttpServletRequest request,HttpServletResponse response,ServletContext servletContext,VideoBean videoBean)
{
System.out.println("\n\n\n\nFetchVideoList is processed\n\n\n\n");
AJAXResponse ajaxResponse=null;
Gson gson=new Gson();
try
{
HttpSession hs=request.getSession(false);
if(hs!=null)
{
UserBean ub=(UserBean)hs.getAttribute("loggedUser");
if(ub!=null)
{
String userId=ub.getUsername();
String password=ub.getPassword();
System.out.println("User ID : "+userId+", Password : "+password);

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
          VideoListBean vib=user.getVideoList();
          java.util.List<VideoBean> viList=vib.getList();
          for(VideoBean video:viList)
          {
             if(video.getSRC().equals(videoBean.getSRC()))
             {
               System.out.println("CurrentTime : "+videoBean.getCurrentTime());
               video.setCurrentTime(videoBean.getCurrentTime());
               video.setPlayMode(videoBean.getPlayMode());
               found=true;
               break;
             }
          }
          if(!found)
          {
            vib.addVideo(videoBean);
            break;
          }
          else 
          {
            break;
          }
        }
   }
   file.delete();
   file.createNewFile();
   FileWriter fileWriter=new FileWriter(file);
   gson.toJson(userList,fileWriter);
   fileWriter.close();
}

ajaxResponse=new AJAXResponse();
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(true);
ajaxResponse.setException("");

System.out.println("\n\n\n\nFetchVideoList Process Complete....");

}// if ends of userBean

}// if ends of hs


}catch(Exception exception)
{
try{
ajaxResponse=new AJAXResponse();
ajaxResponse.setResponse("");
ajaxResponse.setSuccess(false);
ajaxResponse.setException(exception.getMessage());
}catch(Exception e){}
System.out.println("LOGIN : "+exception);
}
}

@ResponseType("JSON")
@Path("/load")
public AJAXResponse loadVideo(HttpServletRequest request,HttpServletResponse response,ServletContext servletContext)
{
System.out.println("\n\n\n\nloadVideo is processed\n\n\n\n");
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
	  ub.setLastVisitedURL(user.getLastVisitedURL());
          ub.setVideoList(user.getVideoList());
          found=true;
          break;
        }
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
ajaxResponse.setException("Nothing");
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


}
