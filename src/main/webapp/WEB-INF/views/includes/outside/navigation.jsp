<%-- 
    Document   : navigation
    Created on : Jun 21, 2017, 11:32:55 PM
    Author     : Olakunle Awotunbo
--%>

      <!--
        http://blog.codeply.com/2016/05/18/bootstrap-sidebar-responsive-examples/ 
        -->

    <nav class="navbar navbar-fixed-top navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=request.getContextPath()%>">WINGAMEPLAY</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<%=request.getContextPath()%>">Home</a></li>
                    <li><a href="<%=request.getContextPath()%>/about">About</a></li>
                    <li><a href="<%=request.getContextPath()%>/contact">Contact</a></li>
                </ul>
            </div><!-- /.nav-collapse -->
        </div><!-- /.container -->
    </nav><!-- /.navbar -->

