<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Humane Society Manager</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <spring:url value="/resources/css/styles.css" var="mainCss"/>
<link href="${mainCss}" rel="stylesheet"/>
    <script src="script.js"></script>
</head>

<style>

    button {
        text-align: center;
        margin: 40px;
    }

    .buttondiv {
        text-align: center;
        margin: auto;
    }
    body {
        background-color: #DFDBE5;
        background-image: url("data:image/svg+xml,%3Csvg width='60' height='60' viewBox='0 0 60 60' xmlns='http://www.w3.org/2000/svg'%3E%3Cg fill='none' fill-rule='evenodd'%3E%3Cg fill='%239C92AC' fill-opacity='0.4'%3E%3Cpath d='M36 34v-4h-2v4h-4v2h4v4h2v-4h4v-2h-4zm0-30V0h-2v4h-4v2h4v4h2V6h4V4h-4zM6 34v-4H4v4H0v2h4v4h2v-4h4v-2H6zM6 4V0H4v4H0v2h4v4h2V6h4V4H6z'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E");
    }

</style>

<body>
    
    <%@include file="navbar.jsp" %>
    
    <br>
    <div>
        <h1 style="text-align: center; font-family: Georgia, 'Times New Roman', Times, serif">
            Welcome to Humane Society Manager
        </h1>
        <br>
        <p style="text-align: center;">
            Use the navigation bar to find what you're looking for! Update, find, and create employees, cats, dogs, and locations-- everything that keeps 
            our Humane Societies running.
        </p>
    </div>
    <br>

    <!-- Bootstrap Carousel of Pet Images --> 

    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="https://www.helpguide.org/wp-content/uploads/dog-resting-chin-on-persons-hand-768.jpg" class="d-block w-100" alt="dog being pet by owner">
          </div>
          <div class="carousel-item">
            <img src="https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/slideshows/10_things_you_can_learn_from_your_pets_slideshow/1800x1200_10_things_you_can_learn_from_your_pets_slideshow_alt1.jpg" class="d-block w-100" alt="cat looking in goldfish bowl">
          </div>
          <div class="carousel-item">
            <img src="https://2m9wu34e3my91ybvq416sfd1-wpengine.netdna-ssl.com/wp-content/uploads/2020/04/bearded_dragon_1481905554.jpg" class="d-block w-100" alt="bearded dragon on log">
          </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
    </div>
    <br><br><br>

    <!--Random Pet joke generator button-->
    <div class="buttondiv">
        <button style="background-color: purple" type="button" class="btn btn-primary" onclick="displayJoke()">Click me for some random pet jokes!</button>
    </div>

    <!--Bootstrap sticky footer-->

    <footer class="footer mt-auto py-3">
        <div class="container">
            <span class="text-muted" style="margin:auto; display:table">HumaneSocietyDB 2021</span>
        </div>
    </footer>





<!--Bootstrap links-->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>