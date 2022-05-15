<%@ page import="com.Payment" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

      <meta charset="ISO-8859-1">
      <script src="Components/jquery-3.2.1.min.js"></script>
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
      <title>Payment Management</title>
      
      <nav class="navbar navbar-expand-md navbar-dark" style="background-color:#26272b">
         <ul class="navbar-nav">
              <li><a href="payment.jsp" class="nav-link" style="color:#ffffff", "font-size:20px;", "size: 50px;" >ElectroGrid Online System</a></li>
         </ul>
    </nav>
    
         
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/Payment.js"></script>
</head>

<body>
<br>
<br>

   <div class="container">
        <div class="row">
           <br>
   <div class="container col-md-5">
        <div class="card">
           <div class="card-body">

     <h2><center>Payment Management</center></h2>
     <hr>
     <form id="formPayment" name="formPayment">
            Date Time: 
            <input id="date_time" name="date_time" type="text" 
                         class="form-control form-control-sm">
                         
            <br>User Address: 
            <input id="user_address" name="user_address" type="text" 
                         class="form-control form-control-sm">
                         
            <br> Amount: 
            <input id="amount" name="amount" type="text" 
                         class="form-control form-control-sm">
                         
            <br> Payment Method: 
            <input id="payment_method" name="payment_method" type="text" 
                         class="form-control form-control-sm">
                         
            <br>
            <input id="btnSave" name="btnSave" type="button" value="Save" 
                         class="btn btn-primary">
                         
            <input type="hidden" id="hidPayment_IDSave" 
                         name="hidPayment_IDSave" value="">
                         			            
     </form>
       </div>
       
   </div>
  </div>   
     
     <div id="alertSuccess" class="alert alert-success"></div>
     <div id="alertError" class="alert alert-danger"></div>
     
     <br>
     <div id="divPaymentsGrid">
           <%
                Payment paymentObj = new Payment(); 
                out.print(paymentObj.readPayment()); 
            %>
     </div>
     
</div> </div> </div> 
</body>
</html>