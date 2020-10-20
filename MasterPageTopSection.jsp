<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/img/favicon.png">
    <title>My Groceries</title>
    <!-- Bootstrap Core CSS -->
    <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">


    <!-- toast CSS -->
    <link href="plugins/bower_components/toast-master/css/jquery.toast.css" rel="stylesheet">


    <!-- morris CSS -->
    <link href="plugins/bower_components/morrisjs/morris.css" rel="stylesheet">
    <!-- chartist CSS -->
    <link href="plugins/bower_components/chartist-js/dist/chartist.min.css" rel="stylesheet">
    <link href="plugins/bower_components/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="css/colors/default.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
    <script src='reduxjs/redux.js'></script>
    <script>
function loginValidation()
{
    let urlString=window.location.href;
    var user={username:"",password:"",videoList:null,lastVisitedURL:urlString};
    fetch("service/Login/loginValidation",
    {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials:"same-origin",
      headers:{
       "Content-Type":"application/json"
      },
      redirect:"follow",
      referrerPolicy:"no-referrer",
      body: JSON.stringify(urlString)
    }).then(response =>response.json()).then(function(data){
         //console.log(data);
         if(data.success)
         {
           document.getElementById("userProfileName").innerHTML=data.response.username.toUpperCase();
           document.getElementById("userProfileImage").src="plugins/images/users/"+data.response.username.trim()+".png";
         }
         else
         {
	    window.location.replace("login.html");
         }
       }).catch(function(error){
       console.error("Error:", error);
       window.location.replace("login.html");
    });
}
function logoutServlet()
{
    fetch("service/Logout/logout",
    {
      method: "POST",
      mode: "cors",
      cache: "no-cache",
      credentials:"same-origin",
      headers:{
       "Content-Type":"application/json"
      },
      redirect:"follow",
      referrerPolicy:"no-referrer"
    }).then(response =>response.json()).then(function(data){
         console.log(data);
         if(data.success)
         {
           window.location.replace("login.html");
         }
         else
         {
	    console.error("Error:",data.exception);
         }
       }).catch(function(error){
       console.error("Error:", error);
    });
}
window.addEventListener("load",loginValidation);

    </script>
</head>

<body class="fix-header">
    <!-- ============================================================== -->
    <!-- Preloader -->
    <!-- ============================================================== -->
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" />
        </svg>
    </div>
    <!-- ============================================================== -->
    <!-- Wrapper -->
    <!-- ============================================================== -->
    <div id="wrapper">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <div class="navbar-header">
                <div class="top-left-part">
                    <!-- Logo -->
                    <a class="logo" href="index.jsp">
                        <!-- Logo icon image, you can use font-icon also -->
                        <b>
                            <!--This is dark logo icon-->
                            <img src="plugins/img/admin-logo.png" alt="home" class="dark-logo" />
                            <!--This is light logo icon-->
                            <img src="plugins/img/admin-logo-dark.png" alt="home" class="light-logo" />
                        </b>
                        <!-- Logo text image you can use text also -->
                        <span class="hidden-xs">
                            <!--This is dark logo text-->
                            <img src="plugins/img/admin-text.png" alt="home" class="dark-logo" />
                            <!--This is light logo text-->
                            <img src="plugins/img/admin-text-dark.png" alt="home" class="light-logo" />
                        </span> 
                    </a>
                </div>
                <!-- /Logo -->
                <ul class="nav navbar-top-links navbar-right pull-right">
                    <li>
                        <a class="nav-toggler open-close waves-effect waves-light hidden-md hidden-lg" href="javascript:void(0)"><i class="fa fa-bars"></i></a>
                    </li>
                    <li>
                        <form role="search" class="app-search hidden-sm hidden-xs m-r-10">
                            <input type="text" placeholder="Search..." class="form-control"> 
                            <a href="">
                                <i class="fa fa-search"></i>
                            </a> 
                        </form>
                    </li>
                    <li>
                        <a class="profile-pic" href="#"> <img id='userProfileImage' src="plugins/images/users/username.png" alt="user-img" width="36" class="img-circle"><b id='userProfileName' class="hidden-xs">Username</b></a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-header -->
            <!-- /.navbar-top-links -->
            <!-- /.navbar-static-side -->
        </nav>
        <!-- End Top Navigation -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav slimscrollsidebar">
                <div class="sidebar-head">
                    <h3><span class="fa-fw open-close"><i class="ti-close ti-menu"></i></span> <span class="hide-menu">Navigation</span></h3>
                </div>
                <ul class="nav" id="side-menu">
                    <li style="padding: 70px 0 0;">
                        <a href="index.jsp" class="waves-effect"><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i>Dashboard</a>
                    </li>
                    <li>
                        <a href="#" class="waves-effect"><i class="fa fa-user fa-fw" aria-hidden="true"></i>Profile</a>
                    </li>
                    <li>
                        <a href="video1.jsp" class="waves-effect"><i class="fa fa-file-movie-o fa-fw" aria-hidden="true"></i>Video 1</a>
                    </li>
                    <li>
                        <a href="video2.jsp" class="waves-effect"><i class="fa fa-file-movie-o fa-fw" aria-hidden="true"></i>Video 2</a>
                    </li>
                    <li>
                        <a href="video3.jsp" class="waves-effect"><i class="fa fa-file-movie-o fa-fw" aria-hidden="true"></i>Video 3</a>
                    </li>
                    <li>
                        <a href="video4.jsp" class="waves-effect"><i class="fa fa-file-movie-o fa-fw" aria-hidden="true"></i>Video 4</a>
                    </li>
                    <li>
                        <a onclick="logoutServlet()" class="waves-effect"><i class="fa fa-file-movie-o fa-fw" aria-hidden="true"></i>Logout</a>
                    </li>

                </ul>
                
            </div>
            
        </div>
        <!-- ============================================================== -->
        <!-- End Left Sidebar -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page Content -->
        <!-- ============================================================== -->
        <div id="page-wrapper">
            <div class="container-fluid">
                

