<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <nav style="background-color: rgb(65, 26, 156);" class="navbar navbar-expand-lg">
      <a class="navbar-brand" href="#">Humane Society Manager</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="index.html">Home </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="Cats.html">Cats</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Dogs.html">Dogs</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Employees.html">Employees<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="Locations.html">Locations</a>
        </li>
          <li class="nav-item">
            <a class="nav-link" href="LogIn.html">Log In</a>
          </li>
        </ul>
      </div>
  </nav>
    <br>
  
    <!--Form for getting employee by id-->
    <div class="mail">
        <form:form action="./getEmp" method="post" modelAttribute="employee">
            <div class="form-group">
            <h1>Find Employee by Id</h1>
            <label for="exampleFormControlInput1">Enter Employee Id</label>
            <form:input path="eId" type="text" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
            <form:errors path="eId"/>
            <br>
            <button type="submit" class="btn btn-primary" value="Get Employee">Submit</button>
            </div>
            <h3 class="main-body-text">${getEmpSessionError}</h3>
		    <h3 class="main-body-text">${getEmpError}</h3>
		    <h5 class="main-body-text">${eId}${firstName}${lastName}${salary}${position}${locationId}${password}</h5>
        </form:form>
    </div>

    <br>
    

     <!--Form for adding a new employee -->
     <div class="mail">
        <form:form action="./addEmp" method="post" modelAttribute="employee">
            
            <div class="form-group">
             <h1>Add New Employee to Records</h1>
              <label for="exampleFormControlInput1">Enter a New Id</label>
              <form:input path="eId" type="text" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
              <form:errors path="eId"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Employee's First Name</label>
              <form:input path="firstName" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Abby"/>
              <form:errors path="firstName"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Employee Last Name</label>
              <form:input path="lastName" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Baka"/>
              <form:errors path="lastName"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Employee Salary (USD)</label>
              <form:input path="salary" type="text" class="form-control" id="exampleFormControlInput1" placeholder="100000"/>
              <form:errors path="salary"/>
            </div>

            <div class="form-group">
                <label for="exampleFormControlInput1">Enter Employee Position</label>
                <form:input path="position" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Adoption Assitant"/>
                <form:errors path="position"/>
            </div>
              
            <div class="form-group">
                <label for="exampleFormControlInput1">Enter Location Id</label>
                <form:input path="locationId" type="text" class="form-control" id="exampleFormControlInput1" placeholder="1"/>
                <form:errors path="locationId"/>
            </div>
              
            <div class="form-group">
                <label for="exampleFormControlInput1">Enter Employee Password</label>
                <form:input path="password" type="text" class="form-control" id="exampleFormControlInput1" placeholder="EmployeePassword1!"/>
                <form:errors path="password"/>
                    
                <br>
                <button type="submit" class="btn btn-primary" value="Add Employee">Submit</button>
            </div>
           	<h3 class="main-body-text">${addEmpLocError}</h3>
		    <h3 class="main-body-text">${addEmpSessionError}</h3>
		   	<h3 class="main-body-text">${errorMessage}</h3>
		   	<h3 class="main-body-text">${successMessage}</h3>
        </form:form>
        </div>
      <br>

    <!-- form to delete employee-->
    <div class="mail">
      <form:form action="./removeEmp" method="post" modelAttribute="employee">
          <div class="form-group">
          <h1>Delete Employee by Id</h1>
          <label for="exampleFormControlInput1">Enter Employee Id</label>
          <form:input path="eId" type="text" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
          <form:errors path="eId"/>
          <br>
          <button value="Remove Employee" type="submit" class="btn btn-primary">Submit</button>
          </div>
          
          <h3 class="main-body-text">${removeEmpSessionError}</h3>
		  <h3 class="main-body-text">${removeEmpError}</h3>
		  <h3 class="main-body-text">${removeEmpSuccess}</h3>
      </form:form>
  </div>
  <br>

  <!-- Form to update employee -->

  <div class="mail">
    <form:form action="./updateEmp" method="post" modelAttribute="employee">
            
        <div class="form-group">
         <h1>Update Employee Information</h1>
          <label for="exampleFormControlInput1">Enter a New Id</label>
          <form:input path="eId" type="text" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
          <form:errors path="eId"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Enter Employee's First Name</label>
          <form:input path="firstName" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Abby"/>
          <form:errors path="firstName"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Enter Employee Last Name</label>
          <form:input path="lastName" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Baka"/>
          <form:errors path="lastName"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Enter Employee Salary (USD)</label>
          <form:input path="salary" type="text" class="form-control" id="exampleFormControlInput1" placeholder="100000"/>
          <form:errors path="salary"/>
        </div>

        <div class="form-group">
            <label for="exampleFormControlInput1">Enter Employee Position</label>
            <form:input path="position" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Adoption Assitant"/>
            <form:errors path="position"/>
        </div>
          
        <div class="form-group">
            <label for="exampleFormControlInput1">Enter Location Id</label>
            <form:input path="locationId" type="text" class="form-control" id="exampleFormControlInput1" placeholder="1"/>
            <form:errors path="locationId"/>
        </div>
          
        <div class="form-group">
            <label for="exampleFormControlInput1">Enter Employee Password</label>
            <form:input path="password" type="text" class="form-control" id="exampleFormControlInput1" placeholder="EmployeePassword1!"/>
            <form:errors path="password"/>
                
            <br>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
       	  <h3 class="main-body-text">${updateEmpSessionError}</h3>
		  <h3 class="main-body-text">${updateEmpError}</h3>
		  <h3 class="main-body-text">${updateEmpSuccess}</h3>
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