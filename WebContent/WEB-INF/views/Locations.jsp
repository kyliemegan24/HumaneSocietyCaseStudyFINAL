<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Humane Society Employees</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <spring:url value="/resources/css/styles.css" var="mainCss"/>
	<link href="${mainCss}" rel="stylesheet"/>
    <script src="script.js"></script>
</head>
<style>
    body {
        background-color: #DFDBE5;
        background-image: url("data:image/svg+xml,%3Csvg width='60' height='60' viewBox='0 0 60 60' xmlns='http://www.w3.org/2000/svg'%3E%3Cg fill='none' fill-rule='evenodd'%3E%3Cg fill='%239C92AC' fill-opacity='0.4'%3E%3Cpath d='M36 34v-4h-2v4h-4v2h4v4h2v-4h4v-2h-4zm0-30V0h-2v4h-4v2h4v4h2V6h4V4h-4zM6 34v-4H4v4H0v2h4v4h2v-4h4v-2H6zM6 4V0H4v4H0v2h4v4h2V6h4V4H6z'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E");
    }

    .form-group {
        padding: 30px;
    }

</style>
<body onload='document.form1.text1.focus()'>

    <!--Bootstrap Navbar Code--> 
    
    <%@include file="navbar.jsp" %>
    
    <br>
  
    <!--Form for find location by id-->
    <div class="mail">
        <form:form action="./getLocation" method="post" modelAttribute="location">
            <div class="form-group">
            <h1>Find Location by Id</h1>
            <label for="exampleFormControlInput1">Enter Location Id</label>
            <form:input path="locId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
            <form:errors path="locId"/>
            <br>
            <button value="Get Location" type="submit" class="btn btn-primary">Submit</button>
            <br>
            <h3 class="main-body-text">${getLocSessionError}</h3>
		    <h3 class="main-body-text">${getLocError}</h3>
		    <h5 class="main-body-text">${locId}${name}${address}</h5>
		    </div>
        </form:form>
    </div>

    <br>
    

     <!--Form for adding a new location -->
     <div class="mail">
        <form:form action="./addLocation" method="post" modelAttribute="location">
            
            <div class="form-group">
             <h1>Add New Location</h1>
              <label for="exampleFormControlInput1">Enter a New Location Id</label>
              <form:input path="locId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
              <form:errors path="locId"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Location Name</label>
              <form:input path="name" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Highland Park Humane Society"/>
              <form:errors path="name"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Location Address</label>
              <form:input path="address" type="text" class="form-control" id="exampleFormControlInput1" placeholder="1111 Marc Street, Saint Paul, MN 55105"/>
              <form:errors path="address"/>
              <br>
              <button value="Add New Location" type="submit" class="btn btn-primary">Submit</button>
              
            <h3 class="main-body-text">${addLocationError}</h3>
		    <h3 class="main-body-text">${addLocationSessionError}</h3>
		   	<h3 class="main-body-text">${addLocationSuccess}</h3>
		   	
            </div>
        </form:form>
        </div>
      <br>

    <!-- form to delete location-->
    <div class="mail">
      <form:form action="./removeLocation" method="post" modelAttribute="location">
          <div class="form-group">
          <h1>Delete Location by Id</h1>
          <label for="exampleFormControlInput1">Enter Location Id</label>
          <form:input path="locId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
          <form:errors path="locId"/>
          <br>
          <button type="submit" class="btn btn-primary">Submit</button>
          <br>
          <h3 class="main-body-text">${removeLocationError}</h3>
		  <h3 class="main-body-text">${removeLocSessionError}</h3>
		  <h3 class="main-body-text">${removeLocSuccess}</h3>
		   	
          </div>
      </form:form>
  </div>
  <br>

  <!-- Form to update location details -->

  <div class="mail">
    <form:form action="./updateLocation" method="post" modelAttribute="location">
            
        <div class="form-group">
         <h1>Update Location Details</h1>
          <label for="exampleFormControlInput1">Enter Location Id</label>
          <form:input path="locId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
          <form:errors path="locId"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Enter Location Name</label>
          <form:input path="name" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Highland Park Humane Society"/>
          <form:errors path="name"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Enter Location Address</label>
          <form:input path="address" type="text" class="form-control" id="exampleFormControlInput1" placeholder="1111 Marc Street, Saint Paul, MN 55105"/>
          <form:errors path="address"/>
          <br>
          <button type="submit" class="btn btn-primary">Submit</button>
          <br>
           <h3 class="main-body-text">${updatetLocError}</h3>
		   <h3 class="main-body-text">${updateLocSessionError}</h3>
		   <h3 class="main-body-text">${updateLocSuccess}</h3>
        </div>
        
    </form:form>
    </div>
    
    <br>
    <!-- form for looking up employees by location -->
    
    <div class="mail">
    <form:form action="./getEmpList" method="post" modelAttribute="location">
            
        <div class="form-group">
         <h1>See Employees by Location Id</h1>
          <label for="exampleFormControlInput1">Enter a Location Id</label>
          <form:input path="locId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
          <form:errors path="locId"/>
       
          <br>
        <div>
          <button value="View Employees" type="submit" class="btn btn-primary">Submit</button>
          
           <h3 class="main-body-text">${empListLocError}</h3>
		   
		   	<h3 class="main-body-text">${empListSessionError}</h3>
		   	<div class="main-body-text">
		   		<c:forEach items = "${empList}" var="emps">
		   			<h5><c:out value="${emps}"/></h5>
		   			</c:forEach>
		   		
		   	
		   	</div>
        </div>
         </div>
    </form:form>
    </div>

    <!--Bootstrap sticky footer-->

    <footer class="footer mt-auto py-3">
        <div class="container">
            <span class="text-muted" style="margin:auto; display:table">HumaneSocietyDB 2021</span>
        </div>
    </footer>




    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<!--Bootstrap links-->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>