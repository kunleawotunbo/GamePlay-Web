<%-- 
    Document   : footer
    Created on : Jun 21, 2017, 11:38:46 PM
    Author     : Olakunle Awotunbo
--%>

  </div><!--/row-->

    <hr>

    <footer>
        <p>&copy; 2017 GamePlay, Inc.</p>
    </footer>

</div><!--/.container-->


<script src="<c:url value='/resources/js/jquery.min.js' />"></script> 
<!-- PNotify -->
<script src="<c:url value='/resources/js/pnotify.js' />"></script> 
<script src="<c:url value='/resources/js/pnotify.buttons.js' />"></script> 
<script src="<c:url value='/resources/js/pnotify.nonblock.js' />"></script> 


<script>
    $(document).ready(function () {
        console.log("Clicked..");
        $('[data-toggle="offcanvas"]').click(function () {
            $('.row-offcanvas').toggleClass('active')
        });
    });

</script>






</body>
</html>