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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
    <!-- View all employees form -->
    <div class="mail">
  <form:form action="./viewEmps" method="post" modelAttribute="employees">
			<div class="form-group">
				<h3>View All Employees</h3>
			
			<div>
			    <button type="submit" class="btn btn-primary" value="Get Employees">View Employees</button>
			</div>
			<h3 class="main-body-text">${viewEmpSessionError}</h3>
			<div class="main-body-text getAll" >
				<c:forEach items = "${empList}" var="emp">
					<h6><c:out value="${emp}"/></h6>
				</c:forEach>
				</div>
			</div>
		</form:form>
		
	</div>
		<br>
    <!--Form for getting employee by id-->
    <div class="mail">
        <form:form action="./getEmp" method="post" modelAttribute="employee">
            <div class="form-group">
            <h3>Find Employee by Id</h3>
            <label for="exampleFormControlInput1">Enter Employee Id</label>
            <form:input path="eId" type="number" class="form-control" id="exampleFormControlInput1"/>
            <form:errors path="eId"/>
            <br>
            <button type="submit" class="btn btn-primary" value="Get Employee">Find Employee</button>
            <br>
            <h4 class="main-body-text">${getEmpSessionError}</h4>
		    <h4 class="main-body-text">${getEmpError}</h4>
		    <h4 class="main-body-text">${eId}${firstName}${lastName}${salary}${position}${locationId}</h4>
		    </div>
        </form:form>
    </div>

    <br>
    

     <!--Form for adding a new employee -->
     <div class="mail">
        <form:form action="./addEmp" method="post" modelAttribute="employee">
            
            <div class="form-group">
             <h3>Add New Employee to Records</h3>
              <label for="exampleFormControlInput1">Enter a New Id</label>
              <form:input path="eId" type="number" class="form-control" id="exampleFormControlInput1"/>
              <form:errors path="eId"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Employee's First Name</label>
              <form:input path="firstName" type="text" class="form-control" id="exampleFormControlInput1"/>
              <form:errors path="firstName"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Employee Last Name</label>
              <form:input path="lastName" type="text" class="form-control" id="exampleFormControlInput1"/>
              <form:errors path="lastName"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Employee Salary (USD)</label>
              <form:input path="salary" type="number" class="form-control" id="exampleFormControlInput1"/>
              <form:errors path="salary"/>
            </div>

            <div class="form-group">
                <label for="exampleFormControlInput1">Enter Employee Position</label>
                <form:input path="position" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Adoption Assistant"/>
                <form:errors path="position"/>
            </div>
              
            <div class="form-group">
                <label for="exampleFormControlInput1">Enter Location Id</label>
                <form:input path="locationId" type="number" class="form-control" id="exampleFormControlInput1"/>
                <form:errors path="locationId"/>
            </div>
              
            <div class="form-group">
                <label for="exampleFormControlInput1">Enter Employee Password</label>
                <form:input path="password" type="text" class="form-control" id="exampleFormControlInput1" placeholder="EmployeePassword1!"/>
                <form:errors path="password"/>
                    
                <br>
                <button type="submit" class="btn btn-primary" value="Add Employee">Add Employee</button>
                <br>
                <h4 class="main-body-text">${addEmpNoDuplicate}</h4>
            	<h4 class="main-body-text">${addEmpLocError}</h4>
		   		<h4 class="main-body-text">${addEmpSessionError}</h4>
		   	    <h4 class="main-body-text">${errorMessage}</h4>
		   	    <h4 class="main-body-text">${successMessage}</h4>
            
            </div>
           	
        </form:form>
        </div>
      <br>

    <!-- form to delete employee-->
    <div class="mail">
      <form:form action="./removeEmp" method="post" modelAttribute="employee">
          <div class="form-group">
          <h3>Delete Employee by Id</h3>
          <label for="exampleFormControlInput1">Enter Employee Id</label>
          <form:input path="eId" type="number" class="form-control" id="exampleFormControlInput1"/>
          <form:errors path="eId"/>
          <br>
          <button value="Remove Employee" type="submit" class="btn btn-primary">Delete Employee</button>
          <br>
          
          <h4 class="main-body-text">${removeEmpSessionError}</h4>
		  <h4 class="main-body-text">${removeEmpError}</h4>
		  <h4 class="main-body-text">${removeEmpSuccess}</h4>
		  
		  </div>
      </form:form>
  </div>
  <br>

  <!-- Form to update employee -->

  <div class="mail">
    <form:form action="./updateEmp" method="post" modelAttribute="employee">
            
        <div class="form-group">
         <h3>Update Employee Information</h3>
          <label for="exampleFormControlInput1">Enter Employee Id</label>
          <form:input path="eId" type="number" class="form-control" id="exampleFormControlInput1"/>
          <form:errors path="eId"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Update Employee's First Name</label>
          <form:input path="firstName" type="text" class="form-control" id="exampleFormControlInput1"/>
          <form:errors path="firstName"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Update Employee Last Name</label>
          <form:input path="lastName" type="text" class="form-control" id="exampleFormControlInput1"/>
          <form:errors path="lastName"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Update Employee Salary (USD)</label>
          <form:input path="salary" type="number" class="form-control" id="exampleFormControlInput1"/>
          <form:errors path="salary"/>
        </div>

        <div class="form-group">
            <label for="exampleFormControlInput1">Update Employee Position</label>
            <form:input path="position" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Adoption Assitant"/>
            <form:errors path="position"/>
        </div>
          
        <div class="form-group">
            <label for="exampleFormControlInput1">Update Location Id</label>
            <form:input path="locationId" type="number" class="form-control" id="exampleFormControlInput1"/>
            <form:errors path="locationId"/>
        </div>
          
        <div class="form-group">
            <label for="exampleFormControlInput1">Update Employee Password</label>
            <form:input path="password" type="text" class="form-control" id="exampleFormControlInput1" placeholder="EmployeePassword1!"/>
            <form:errors path="password"/>
                
            <br>
            <button type="submit" class="btn btn-primary">Update Employee</button>
        	<br>
       	  <h4 class="main-body-text">${updateEmpSessionError}</h4>
       	  <h4 class="main-body-text">${addEmpNoDuplicate}</h4>
		  <h4 class="main-body-text">${updateEmpError}</h4>
		  <h4 class="main-body-text">${updateEmpSuccess}</h4>
		  
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