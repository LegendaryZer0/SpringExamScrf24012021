<#ftl encoding="UTF-8"/>

<!DOCTYPE html>
<html>
<head>
    <title>Login Form</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
<h1>ЭТО РАЗЫГРЫШЬ, ЭТО НЕ СЛЕДУЮЩАЯ СТРАНИЦА,НОВИЧОК</h1>
<h2>PLEASE DONT REPEAT YOURSELF MAAAAAAAAN</h2><br>
<div class="login">
    <form id="loginIn" method="POST" action="loginIn" >
        <label><b>User Name
            </b>
        </label>
        <input type="text" name="id" id="id" placeholder="id">
        <br><br>
        <label><b>Password
            </b>
        </label>
        <input type="Password" name="Pass" id="Pass" placeholder="Password">
        <br><br>
        <#--<input type="button" name="log" id="log" value="Log In Here">
        <br><br>-->
        <p><input type="submit"  value="LOGIN"></p>
        <input type="checkbox" id="check">
        <input id ='csrf-token' name="_csrf_token" value="${_csrf_token}" />
        <span>Remember me</span>
        <br><br>
        Forgot <a href="#">Password</a>
    </form>
</div>
</body>
</html>