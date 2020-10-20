<jsp:include page='/MasterPageTopSection.jsp' />
<script src="reduxjs/redux.js"></script>
<script src="myjs/video1.js">
</script>
<div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Video 2</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        
                        <ol class="breadcrumb">
                            <li><a>Dashboard</a></li>
                            <li class="active">Video 2</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <!-- ============================================================== -->
                <!-- Different data widgets -->
                <!-- ============================================================== -->
                <!-- .row -->
                <div class="row">
                <!-- ============================================================== -->
                <!-- video & video play events -->
                <!-- ============================================================== -->
                <!-- .col -->
                    <div class="col-md-12 col-lg-8 col-sm-12">
                        <div class="white-box">
                            <h3 class="box-title">Style Four</h3><hr/>
                            <video id="video1" width="100%" controls>
                               <source src="videos/stylefour.mp4" type='video/webm; codecs="vp8.0, vorbis"'>
                               <source src="videos/stylefour.mp4" type='video/ogg; codecs="theora, vorbis"'>
                               <source src="videos/stylefour.mp4" type=video/mp4>
                            </video>
                        </div>
                    </div>



                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <div class="panel">
                            <div class="sk-chat-widgets">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        EVENT/PROPERTIES
                                    </div>
                                    <div class="panel-body">
                                        <ul class="chatonline">
                                            <li>
                                               SRC Property : <span id="videoSRC"></span>
                                            </li>
                                            <li>
                                               Play/Pause Event : <span id="videoPlayEvent"></span>
                                            </li>
                                            <li>
                                               Playing Event : <span id="videoPlayingEvent"></span>
                                            </li>
                                            <li>
                                               TimeUpdate Event : <span id="videoTimeUpdateEvent"></span>
                                            </li>

                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>




<jsp:include page='/MasterPageBottomSection.jsp' />
