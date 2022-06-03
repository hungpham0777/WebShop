<%@page import="service.CartServive"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
        <script type="text/javascript">
        function showPreview(event){
        	  if(event.target.files.length > 0){
        	    var src = URL.createObjectURL(event.target.files[0]);
        	    var preview = document.getElementById("url-image");
        	    preview.src = src;
        	  }
        	}
        </script>
<style type="text/css">
.background {
    display: flex;
    min-height: 100vh;
  }
  
  .container {
    flex: 0 1 700px;
    margin: auto;
    padding: 10px;
  }
  
  .screen {
    position: relative;
    background: #3e3e3e;
    border-radius: 15px;
  }
  
  .screen:after {
    content: '';
    display: block;
    position: absolute;
    top: 0;
    left: 20px;
    right: 20px;
    bottom: 0;
    border-radius: 15px;
    box-shadow: 0 20px 40px rgba(0, 0, 0, .4);
    z-index: -1;
  }
  
  .screen-header {
    display: flex;
    align-items: center;
    padding: 10px 20px;
    background: #4d4d4f;
    border-top-left-radius: 15px;
    border-top-right-radius: 15px;
  }
  
  .screen-header-left {
    margin-right: auto;
  }
  
  .screen-header-button {
    display: inline-block;
    width: 8px;
    height: 8px;
    margin-right: 3px;
    border-radius: 8px;
    background: white;
  }
  
  .screen-header-button.close {
    background: #ed1c6f;
  }
  
  .screen-header-button.maximize {
    background: #e8e925;
  }
  
  .screen-header-button.minimize {
    background: #74c54f;
  }
  
  .screen-header-right {
    display: flex;
  }
  
  .screen-header-ellipsis {
    width: 3px;
    height: 3px;
    margin-left: 2px;
    border-radius: 8px;
    background: #999;
  }
  
  .screen-body {
    display: flex;
  }
  
  .screen-body-item {
    flex: 1;
    padding: 50px;
  }
  
  .screen-body-item.left {
    display: flex;
    flex-direction: column;
  }
    .screen-body-item.right {
	padding-left: 10px!important;
  }
  .app-title {
    display: flex;
    flex-direction: column;
    position: relative;
    color: #ea1d6f;
    font-size: 26px;
  }
  
  .app-title:after {
    content: '';
    left: 0;
    bottom: -10px;
    width: 100%;
    height: 4px;
    background: #ea1d6f;
  }
  
  .app-contact {
    margin-top: auto;
    font-size: 8px;
    color: #888;
  }
  
  .app-form-group {
    margin-bottom: 15px;
    color: #FFFF;
  }
  
  .app-form-group.message {
    margin-top: 40px;
  }
  
  .app-form-group.buttons {
    margin-bottom: 0;
    text-align: right;
  }
  
  .app-form-control {
    width: 100%;
    padding: 10px 0;
    background: none;
    border: none;
    border-bottom: 1px solid #666;
    color: #ddd;
    font-size: 14px;
    outline: none;
    transition: border-color .2s;
  }
  
  .app-form-control::placeholder {
    color: #666;
  }
  
  .app-form-control:focus {
    border-bottom-color: #ddd;
  }
  
  .app-form-button {
    background: none;
    border: none;
    color: #ea1d6f;
    font-size: 14px;
    cursor: pointer;
    outline: none;
  }
  
  .app-form-button:hover {
    color: #b9134f;
  }
  
  .credits {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
    color: #ffa4bd;
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 16px;
    font-weight: normal;
  }
  
  .credits-link {
    display: flex;
    align-items: center;
    color: #fff;
    font-weight: bold;
    text-decoration: none;
  }
  
  .dribbble {
    width: 20px;
    height: 20px;
    margin: 0 5px;
  }
  
  @media screen and (max-width: 520px) {
    .screen-body {
      flex-direction: column;
    }
  
    .screen-body-item.left {
      margin-bottom: 30px;
    }
  
    .app-title {
      flex-direction: row;
    }
  
    .app-title span {
      margin-right: 12px;
    }
  
    .app-title:after {
      display: none;
    }
  }
  
  @media screen and (max-width: 600px) {
    .screen-body {
      padding: 40px;
    }
  
    .screen-body-item {
      padding: 0;
    }
  }
  
    </style>
</head>
<body>
<%
	HttpSession ses=request.getSession();
	String accesser=(String)ses.getAttribute("accesser");
	Client client=null;
	if(accesser!=null && accesser.equals("user")){
		client=(Client) ses.getAttribute("user");
	}
%>
	    <div class="background">
        <div class="container">
          <div class="screen">
            <div class="screen-header">
              <div class="screen-header-left">
                <div class="screen-header-button close"></div>
                <div class="screen-header-button maximize"></div>
                <div class="screen-header-button minimize"></div>
              </div>
              <div class="screen-header-right">
                <div class="screen-header-ellipsis"></div>
                <div class="screen-header-ellipsis"></div>
                <div class="screen-header-ellipsis"></div>
              </div>
            </div>
            <div class="screen-body" >
              <div class="screen-body-item left">
                <div class="app-title">
                  <span>ACCOUNT</span>
                  <span id="image" style="font-size: 200px"><img style="width: 270px; height: 240px" id="url-image" src="<%=client.getImage()!=null ? request.getContextPath()+"/DataImage/user/avatar_userID"+client.getId():request.getContextPath()+"/DataImage/general/—Pngtree—vector add user icon_4102440.png" %>" alt="your image" ></span>
                </div>
                <input id="form-file" form="update-form" type="file" name="image" accept="image/*" onchange="showPreview(event)">
                <div class="app-contact" style="display: flex;"><form action="http://localhost:8080/WebShop/Trangchu/SignUpIn"><button style="padding: 16px">LOG-OUT <i class="fa fa-sign-out" aria-hidden="true"></i></button></form>
                <form action="http://localhost:8080/WebShop/Trangchu"><button style="padding: 16px">BACK HOME <i class="fa fa-home" aria-hidden="true"></i></button></form>
                </div>
              </div>
              <div class="screen-body-item right">
                <form id="update-form" class="app-form" method="post" enctype="multipart/form-data">
                <input style="display: none" name="clientID" value="<%=client.getId() %>">
                <input style="display: none" name="money" value="<%=client.getMoney()%>">
                  <div class="app-form-group">
                  USER
                    <input class="app-form-control" name="user" value="<%=client.getUser()%>">
                  </div>
                  <div class="app-form-group">
                  PASS
                    <input class="app-form-control" type="password" name="pass" value="<%=client.getPassword()%>">
                  </div>
                  <div class="app-form-group">
                  FULLNAME
                    <input class="app-form-control" name="fullname" value="<%=client.getFullName()%>">
                  </div>
                  <div class="app-form-group">
                  ADDRESS
                    <input class="app-form-control" name="address" value="<%=client.getAddress()%>">
                  </div>
                  <div class="app-form-group">
                  PHONE
                    <input class="app-form-control" name="phone" value="<%=client.getPhone()%>">
                  </div>
                  <div class="app-form-group message" >
                   BIRTHDAY
                    <input type="date" placeholder="Birthday" name="birthday" value="<%=client.getBirthday() %>"  style="margin-bottom: 20px;
   					margin-bottom: 20px; padding: 5px; color: #FFF; BACKGROUND: initial;"required/>
                  </div>
                  <div class="app-form-group buttons">
                    <button class="app-form-button">SAVE CHANGE</button>
                    
                  </div>
                </form>
              </div>
            </div>
          </div>
          <div class="credits">

          </div>
        </div>
      </div>
</body>
</html>