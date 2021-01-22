<%@page
	import="com.nagarro.imageManagement.serviceImpl.ImageManagementImpl"%>
<%@page
	import="com.nagarro.imageManagement.service.ImageManagementService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.List"%>
<%@page import="com.nagarro.imageManagement.model.Image"%>
<%@page import="com.nagarro.imageManagement.constants.Constants"%>

<%
	Cookie cookies[] = request.getCookies();
	if (cookies == null) {
		System.out.println("Kindly login with correct credentials");
		response.sendRedirect("loginPage.html");
		return;
	}
	String userName = null;
	for (Cookie c : cookies) {
		if (c.getName().equals("username"))
			userName = c.getValue();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Image Management</title>
<style type="text/css">
body {
	display: inline;
	margin: 0px;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}

img {
	margin: 5px;
}

#hiddenDiv {
	position: fixed;
	display: none;
	top: 33%;
	left: 45%;
	background-color: white;
}

#overlay {
	width: 100%;
	height: 100%;
	background-color: grey;
	opacity: 0.7;
	position: fixed;
	display: none;
}

legend {
	margin: auto;
}
</style>
</head>
<body>
	<div id="overlay"></div>
	<div id="hiddenDiv">
		<form id="changeImgNameForm" method="post" >
			<fieldset>
				<legend>Change Image Name</legend>
				<label> Enter Name: <input name="name" required></label><br>
				<br> <button type="submit" 
				class="btn btn-primary">Submit</button>

			</fieldset>
		</form>
	</div>
		<h1 style="text-align:center;">Image Management Utility</h1>
	<br>
	<div>
	<p>Please select Image file to upload (Max Size 1 MB)
		<form method="post" enctype="multipart/form-data" action="SaveImage"
			id="imgSaveForm">
			<input type="file" name="imgFile"
				style="margin-right: 400px;" accept="image/*"
				required />
			<button type="submit" 
				class="btn btn-primary">Submit</button>
			<input type="reset" style="margin-right: 5px">
		</form>
	</div>
	<div>
		<h2 style="margin-top: 40px">Uploaded Images</h2>
		<table style="width: 100%">
			<tr>
				<th>S.NO</th>
				<th>Name</th>
				<th>Size</th>
				<th>Preview</th>
				<th>Action</th>
			</tr>
			<%
		ImageManagementService service = new ImageManagementImpl();
		List<Image> imageList = service.getImageListForUser(userName);

		if (imageList == null) {
		} else {
					String id, name, size, preview, action, imgPath,action2;
					action = "<img width='10px' height = '10px' src= \'"+Constants.IMAGE_LOCATION +"\\cross-mark.png\' class = 'del'>";
					action2 = "<img width='10px' height = '10px' src= \'"+Constants.IMAGE_LOCATION +"\\edit_image.png\' class = 'edit'>";
					for (Image i : imageList) {
						id = i.getId() + "";
						name = i.getImgName();
						size = i.getImgSize() + " KB";
						imgPath = i.getImagePath();
						out.println("<tr>");
						out.println("<td>" + id + "</td>");
						out.println("<td class='ImgName'>" + name + "</td>");
						out.println("<td>" + size + "</td>");
						out.println("<td><img width='120px' height = '120px' src = '" + imgPath + "'></td>");
						out.println("<td>"+action+action2+ "</td>");
						out.println("</tr>");
					}
				}
			%>
		</table>
		<p> Total size of Images are :
		<%
		Double totalImgSize= service.getTotalImageSizeForUsername(userName);
		out.println( totalImgSize +"KB </p>");
		%>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>
	<script>
		var glObj = null;
		$(".edit").on("click", function() {
			glObj = $(this).parents("tr");
			changeView();
			var str = glObj.children("td.ImgName").html();
		});
		$(".del").on("click", function() {
			var id = $(this).parents("tr").children("td:first").html();
			$(this).parents("tr").remove();
			$.ajax({
				url : "DeleteImage",
				method : "post",
				data : {
					imageId : id
				},
				success : function(data) {
					location.reload(true);
				}
			});
		});
		$("#changeImgNameForm").on("submit", function(e) {
			e.preventDefault();
			var str = $("Input[name='name']").val();
			var id = glObj.children("td:first").html();
			changeView();
			$.ajax({
				url : "EditImage",
				method : "post",
				data : {
					imageId : id,
					newName : str
				},
				
		success : function(data) {
					location.reload(true);
				}
			});

		});
		$("#overlay").click(changeView);
		function changeView() {
			$("#overlay").toggle();
			$("#hiddenDiv").toggle();
			$("Input[name='name']").val("");
		}
	</script>
</body>
</html>
