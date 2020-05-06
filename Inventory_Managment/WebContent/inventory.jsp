<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.inventory"  %>   
    
    
    <!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/inventory.js"></script>
<meta charset="ISO-8859-1">


</head>
<body>


<div class="container">
<div class="row">
<div class="col-6">

<h1>Inventory Management</h1>
<form id="formProduct" name="formProduct" method="post" action="inventory.jsp">
Product code:
<input id="productCode" name="productCode" type="text"
class="form-control form-control-sm">
<br> Product name:
<input id="ProductName" name="ProductName" type="text"
class="form-control form-control-sm">
<br> Product price:
<input id="productPrice" name="productPrice" type="text"
class="form-control form-control-sm">
<br> Product description:
<input id="ProductDesc" name="ProductDesc" type="text"
class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">
<input type="hidden" id="hidProductIDSave" name="hidProductIDSave" value="">
</form>


<div id="alertSuccess" class="alert alert-success"></div>

<div id="alertError" class="alert alert-danger"></div>
  
   <br>
   
   <%
      	inventory productobj = new inventory();
            out.print(productobj.readProduct());
      %>
   
   
   </div>
   </div>
   </div>


</body>
</html>