<%@include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	
	<script>	
		function preview() {
			var preview = document.querySelector('img');
			var file    = document.querySelector('input[type=file]').files[0];
			var reader  = new FileReader();

			reader.addEventListener("load", function () {
				preview.src = reader.result;
			}, false);

			if (file) {
				reader.readAsDataURL(file);
			}
		}
	</script>
	
</head>

	<body>	
        <f:form id="userForm" method="POST" modelAttribute="user" enctype="multipart/form-data">
        	<table cellspacing="0">
				<tr>
					<th>User</th>
				</tr>
				<tr>
            		<td><f:label path="username">Username:</f:label></td>
            		<td><f:input path="username"/>&nbsp;<f:errors path="username" cssClass="error"/></td>
            	</tr>
            	<tr>
            		<td><f:label path="password">Password:</f:label></td>
            		<td><f:password path="password"/>&nbsp;<f:errors path="password" cssClass="error"/></td>
            	</tr>
            	<tr>
            		<td><f:label path="firstName">First Name:</f:label></td>
            		<td><f:input path="firstName"/>&nbsp;<f:errors path="firstName" cssClass="error"/></td>
            	</tr>
            	<tr>
            		<td><f:label path="lastName">Last Name:</f:label></td>
            		<td><f:input path="lastName"/>&nbsp;<f:errors path="lastName" cssClass="error"/></td>
            	</tr>
            	<tr>
            		<td><f:label path="admin">Is Admin</f:label></td>
            		<td><f:checkbox path="admin"/></td>
            	</tr>
            	<tr>
            		<td><label for="image1">Image:</label></td>
            		<td><input name="image1" type="file" accept="image/jpeg" onchange="preview()"/></td>
            		<c:choose>
            			<c:when test="${not empty image1}">
							<td><img src="data:image/jpeg;base64,${image1}" style="width:128px;height:128px;"/></td>
						</c:when>
						<c:otherwise>
							<td><img style="width:128px;height:128px;"/></td>
    					</c:otherwise>
    				</c:choose>
            	</tr>
            	<tr>
            		<td><input type="submit" value="Save"/><td>
            	</tr>
            	<tr>
					<th><c:if test="${not empty msg}"><c:out value="${msg}"/></c:if></th>
				</tr>
            </table>
        </f:form>
    </body>
</html>


