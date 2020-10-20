package com.thinking.machines.beans;
public class UserBean implements java.io.Serializable
{
private String username;
private String password;
private VideoListBean videoList;
private String lastVisitedURL=null;
public UserBean()
{
this.username=null;
this.password=null;
this.videoList=new VideoListBean();
this.lastVisitedURL="";
}
public void setLastVisitedURL(String lastVisitedURL)
{
this.lastVisitedURL=lastVisitedURL;
}
public String getLastVisitedURL()
{
return this.lastVisitedURL;
}

public void setUsername(String username)
{
this.username=username;
}
public String getUsername()
{
return this.username;
}
public void setPassword(String password)
{
this.password=password;
}
public String getPassword()
{
return this.password;
}
public void setVideoList(VideoListBean videoList)
{
this.videoList=videoList;
}
public VideoListBean getVideoList()
{
return this.videoList;
}
public void addVideo(VideoBean video)
{
this.videoList.addVideo(video);
}
}
