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