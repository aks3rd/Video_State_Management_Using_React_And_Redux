package com.thinking.machines.beans;
import java.util.*;
public class VideoBean
{
private String src;
private boolean playMode;
private int currentTime;
private String visitedURL;
public VideoBean()
{
this.src="";
this.playMode=false;
this.visitedURL=null;
}
public void setSRC(String src)
{
this.src=src;
}
public String getSRC()
{
return this.src;
}
public void setPlayMode(boolean playMode)
{
this.playMode=playMode;
}
public boolean getPlayMode()
{
return this.playMode;
}
public void setCurrentTime(int currentTime)
{
this.currentTime=currentTime;
}
public int getCurrentTime()
{
return this.currentTime;
}
public void setVisitedURL(String visitedURL)
{
this.visitedURL=visitedURL;
}
public String getVisitedURL()
{
return this.visitedURL;
}
}
