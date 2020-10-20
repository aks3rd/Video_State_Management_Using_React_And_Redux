package com.thinking.machines.beans;
import java.util.*;
public class VideoListBean
{
public List<VideoBean> listOfVideos=null;
public VideoListBean()
{
this.listOfVideos=new LinkedList<VideoBean>();
}
public void addVideo(VideoBean video)
{
this.listOfVideos.add(video);
}
public void setList(List<VideoBean> list)
{
this.listOfVideos=list;
}
public List<VideoBean> getList()
{
return this.listOfVideos;
}
}
