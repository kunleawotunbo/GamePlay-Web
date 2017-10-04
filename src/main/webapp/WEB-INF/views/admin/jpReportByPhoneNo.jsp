<%-- 
    Document   : jpReportByPhoneNo
    Created on : Oct 2, 2017, 5:16:38 PM
    Author     : Olakunle Awotunbo
--%>

<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/sidebar-menu.jsp" %>
<%@ include file="../includes/top-navigation.jsp" %>


<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Jackpot</h3>
            </div>
            <!--
                        <div class="title_right">
                            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">Go!</button>
                                    </span>
                                </div>
                            </div>
                        </div>-->
        </div>

        <div class="clearfix"></div>

        <div class="row">
            <!-- form input mask -->
            <!--              <div class="col-md-6 col-sm-12 col-xs-12">--> 

            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2> Players  Report by Period</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />


                        <c:if test="${error}">                                  
                            <div class="alert alert-danger" id="success-error">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>${message} </strong>
                            </div>
                        </c:if>  

                        <c:if test="${saved}">        
                            <div class="alert alert-success" id="success-alert">
                                <button type="button" class="close" data-dismiss="alert">x</button>
                                <strong>${message} </strong>
                            </div>
                        </c:if>  

                        <div id="feedback"></div>

                        <form:form modelAttribute="weeklyGamesAnswers" method="POST" class="form-horizontal form-label-left" id="report-form" data-parsley-validate="">  
                            <form:hidden path="id" id="id" name="id" />
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-3">Phone Number<span class="required">*</span></label>
                                <div class="col-md-3 col-sm-3 col-xs-3">
                                    <form:input path="userPhoneNo" id="userPhoneNo" type="text" class="form-control" name="userPhoneNo" placeholder="User Phone number" required ="required" />                                  
                                </div>
                            </div>  




                            <div class="ln_solid"></div>


                            <div class="form-group">
                                <div class="form-group">
                                    <div class="col-md-9 col-md-offset-3">
                                        <button type="reset" class="btn btn-primary">Cancel</button>
                                        <!--<button type="submit" id="bth-submit"  class="btn btn-success">Submit</button>-->
                                        <input type="submit" id="bth-submit" class="btn btn-success" value="Submit" /> 
                                    </div>
                                </div>
                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>


        <div class="row">
            <!-- form input mask -->
            <!--              <div class="col-md-6 col-sm-12 col-xs-12">--> 
            <div class="col-md-10 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>List of Submitted answer by ${userPhoneNo}</h2>

                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <h2>Total Entries of ${total}</h2>
                        <br />

                        <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>S/N</th>
                                    <th>PHONE No</th>
                                    <th>ANSWER</th>
                                    <th>DATE</th>
                                    <!--                                    <th></th>
                                                                        <th></th>-->

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="item">  
                                    <tr>  
                                        <td><c:out value="${item.id}"/></td>  
                                        <td><c:out value="${item.userPhoneNo}"/></td>  
                                        <td><c:out value="${item.userAnswer}"/></td> 
                                        <td><c:out value="${item.dateAnswered}"/></td> 
                                        
                                    </tr>  
                                </c:forEach>  
                            </tbody>
                        </table>


                    </div>
                </div>
            </div>
            <!-- /form input mask -->

        </div>         



        <%@ include file="../includes/footer.jsp" %>

      