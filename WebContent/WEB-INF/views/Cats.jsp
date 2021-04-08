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
    <title>Humane Society Cats</title>
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
    
    
    <div class="mail">
        <form:form action="./viewCats" method="post" modelAttribute="cat">
            <div class="form-group">
            <h1>View All Cats</h1>
            <br>
            <button value="View Cats" type="submit" class="btn btn-primary">View Cats</button>
            <br>
            <h3 class="main-body-text">${viewCatSessionError}</h3>
		    <div class="main-body-text" >
				<c:forEach items = "${catList}" var="cats">
					<h5><c:out value="${cats}"/></h5>
				</c:forEach>
				</div>
		    </div>
        </form:form>
    </div>
  	<br>
    <!--Form for finding cat by id-->
    <div class="mail">
        <form:form action="./getCat" method="post" modelAttribute="cat">
            <div class="form-group">
            <h1>Find Cat by Id</h1>
            <label for="exampleFormControlInput1">Enter Cat Id</label>
            <form:input path="cId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
            <form:errors path="cId"/>
            <br>
            <button value="Get Cat" type="submit" class="btn btn-primary">Submit</button>
            <br>
            <h3 class="main-body-text">${getCatSessionError}</h3>
		    <h3 class="main-body-text">${getCatError}</h3>
		    <h5 class="main-body-text">${cId}${name}${age}${breed}${upToDateShots}${gender}${locationId}</h5>
       		</div>
        </form:form>
    </div>

    <br>
    

     <!--Form for adding new cat-->
     <div class="mail">
        <form:form action="./addCat" method="post" modelAttribute="cat">
            
            <div class="form-group">
             <h1>Add New Cat to Records</h1>
              <label for="exampleFormControlInput1">Enter a New Id</label>
              <form:input path="cId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
              <form:errors path="cId"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter a New Name</label>
              <form:input path="name" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Hila"/>
              <form:errors path="name"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Cat Age (in years)</label>
              <form:input path="age" type="number" class="form-control" id="exampleFormControlInput1" placeholder="3"/>
              <form:errors path="age"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Cat Breed</label>
              <form:input path="breed" type="text" class="form-control" id="exampleFormControlInput1" placeholder="orange tabby"/>
              <form:errors path="breed"/>
            </div>
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Is this cat up to date on shots? (enter 1 for YES, or 2 for NO)</label>
              <form:input path="upToDateShots" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1"/>
              <form:errors path="upToDateShots"/>
            </div>
              
              
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Cat Gender (1 for MALE, or 2 for FEMALE)</label>
              <form:input path="gender" type="number" class="form-control" id="exampleFormControlInput1" placeholder="2"/>
              <form:errors path="gender"/>
              
            </div>    
            
            <div class="form-group">
              <label for="exampleFormControlInput1">Enter the location id of the location where this cat is being held</label>
              <form:input path="locationId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1"/>
              <form:errors path="locationId"/>
            
            
             <%-- <div class="form-group">
              <label for="exampleFormControlInput1">Enter image for cat</label>
              <form:input path="catPicture" type="text" class="form-control" id="exampleFormControlInput1" placeholder="1"/>
              <form:errors path="catPicture"/>
            </div> --%>
            
            <br>
            <button value="Add Cat" type="submit" class="btn btn-primary">Submit</button>    
            <br>
              
            <h3 class="main-body-text">${addCatLocError}</h3>
		    <h3 class="main-body-text">${addCatSessionError}</h3>
		   	<h3 class="main-body-text">${addCatError}</h3>
		   	<h3 class="main-body-text">${successMessage}</h3>
           </div>
        </form:form>
        </div>
      <br>

    <!-- form to delete cat-->
    <div class="mail">
      <form:form action="./removeCat" method="post" modelAttribute="cat">
          <div class="form-group">
          <h1>Delete Cat by Id</h1>
          <label for="exampleFormControlInput1">Enter Cat Id</label>
          <form:input path="cId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
          <form:errors path="cId"/>
          <br>
          <button value="Delete Cat" type="submit" class="btn btn-primary">Submit</button>
          <br>
          <h3 class="main-body-text">${RemoveCatError}</h3>
		  <h3 class="main-body-text">${removeCatSessionError}</h3>
		  <h3 class="main-body-text">${removeCatSuccess}</h3>
		  </div>
      </form:form>
  </div>
  <br>

  <!-- Form to update cat -->

  <div class="mail">
    <form:form action="./updateCat" method="post" modelAttribute="cat">
        
        <div class="form-group">
         <h1>Update Existing Cat</h1>
          <label for="exampleFormControlInput1">Enter Cat Id</label>
          <form:input path="cId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1234"/>
          <form:errors path="cId"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Enter Cat Name</label>
          <form:input path="name" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Hila"/>
          <form:errors path="name"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Enter Cat Age (in years)</label>
          <form:input path="age" type="number" class="form-control" id="exampleFormControlInput1" placeholder="3"/>
          <form:errors path="age"/>
        </div>
          
        <div class="form-group">
          <label for="exampleFormControlInput1">Enter Cat Breed</label>
          <form:input path="breed" type="text" class="form-control" id="exampleFormControlInput1" placeholder="orange tabby"/>
          <form:errors path="breed"/>
        </div>
        
         <div class="form-group">
             <label for="exampleFormControlInput1">Is this cat up to date on shots? (enter 1 for YES, or 2 for NO)</label>
             <form:input path="upToDateShots" type="number" class="form-control" id="exampleFormControlInput1" placeholder="1"/>
             <form:errors path="upToDateShots"/>
        </div>
            

            <div class="form-group">
              <label for="exampleFormControlInput1">Enter Cat Gender (1 for MALE, or 2 for FEMALE)</label>
              <form:input path="gender" type="number" class="form-control" id="exampleFormControlInput1" placeholder="2"/>
              <form:errors path="gender"/>
            </div>  
            
             <div class="form-group">
              <label for="exampleFormControlInput1">Enter Location Id </label>
              <form:input path="locationId" type="number" class="form-control" id="exampleFormControlInput1" placeholder="2"/>
              <form:errors path="locationId"/>
            <br>
            <button value="Update Cat" type="submit" class="btn btn-primary">Submit</button>    
            <br>
                
            <h3 class="main-body-text">${updateCatLocError}</h3>
		    <h3 class="main-body-text">${updateCatSessionError}</h3>
		   	<h3 class="main-body-text">${updateCatError}</h3>
		   	<h3 class="main-body-text">${updateCatSuccess}</h3>
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