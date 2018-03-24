<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/jquery.min.js"></script>
<title>welcome</title>
</head>
<body>
<br>
<div class="container  h-100 card col-3 text-center">
<div class="card-body">
<br><div>Welcome,<%= (String)session.getAttribute("name") %>  </div>
<br>
<form action="wd.html" method="get">
<input type="submit" class="btn btn-primary" value="cash Withdrawl">
</form>
<br><form action="dp.html" method="get">
<input type="submit" class="btn btn-primary" value="deposit">
</form>
<br><form action="bal" method="get">
<input type="submit" class="btn btn-primary" value="balance">
</form>
</div>
</div>
</body>
</html>
