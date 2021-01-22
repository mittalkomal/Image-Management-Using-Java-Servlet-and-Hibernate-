<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
		<h1>Login</h1>
		<form action="LoginCheck" method="post">

			<div class="form-group">
				<label for="uname">User Name: *</label> <input type="text"
					class="form-control" id="username" placeholder="User Name"
					name="username" required>
			</div>

			<div class="form-group">
				<label for="uname">Password: *</label> <input type="password"
					class="form-control" id="password" placeholder="Password"
					name="password" required>
			</div>



			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<script>
		function myFunction(errorMsg) {
			alert(errorMsg);
		}
	<%Cookie cookies[] = request.getCookies();
			Cookie deletedCookie = null;
			if (cookies == null) {
			} else {
				String errorMsg = null;
				for (Cookie c : cookies) {
					if (c.getName().equals("errorMsg")) {
						errorMsg = c.getValue();
						deletedCookie = c;
					}
				}
				if (deletedCookie != null) {
					deletedCookie.setMaxAge(0);
					response.addCookie(deletedCookie);
				}
				if (errorMsg != null && errorMsg.length() > 2) {
					errorMsg = "Please enter correct name and password";
					out.println("myFunction(\"" + errorMsg + " \")");
				}
			}%>
		
	</script>

</body>
</html>