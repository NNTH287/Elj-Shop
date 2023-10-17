<%-- 
    Document   : ManagerProduct
    Created on : Dec 28, 2020, 5:19:02 PM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Elj Shop - Online Art Supplies Shop</title>
        <!<!-- Favicon -->
        <link rel="icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/icon type">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="${pageContext.request.contextPath}/css/manager.css?version=1" rel="stylesheet"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div class="row p-3 text-right">
                <a href="profile" class="btn btn-primary">Profile</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">Log Out</a>
            </div>

            <!-- Notification Start -->
            <c:if test="${notification != null}">
                <div class="row p-3">
                    <div class="col-lg-12">
                        <div class="alert <c:choose><c:when test="${notiType == 'RED'}">alert-danger</c:when><c:otherwise>alert-success</c:otherwise></c:choose>">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                        <strong><%= session.getAttribute("notification")%></strong>
                            <%session.removeAttribute("notification");%>
                            <%session.removeAttribute("notiType");%>
                        </div>
                    </div>
                </div>
            </c:if>
            <!-- Notification End -->

            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <a style="color: #FFF" href="home">
                            <h2>Manage <b>User</b></h2>
                            </a>
                        </div>
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add</span></a>					
                        </div>
                    </div>
                </div>
                <div class="row text-right">
                    <form action="home" method='GET'>
                        <label>Role: </label>
                        <select name="roleFilter" onchange="this.form.submit()">
                            <option value="All" <c:if test="${param.roleFilter != 'All'}">selected</c:if> value>All</option>
                            <option value="Admin" <c:if test="${param.roleFilter == 'Admin'}">selected</c:if>>Admin</option>
                            <option value="Customer" <c:if test="${param.roleFilter == 'Customer'}">selected</c:if>>Customer</option>
                            <option value="Manager" <c:if test="${param.roleFilter == 'Manager'}">selected</c:if>>Manager</option>
                            <option value="Seller" <c:if test="${param.roleFilter == 'Seller'}">selected</c:if>>Seller</option>
                            <option value="Storage Staff" <c:if test="${param.roleFilter == 'Storage Staff'}">selected</c:if>>Storage Staff</option>
                            <option value="Marketing Staff" <c:if test="${param.roleFilter == 'Marketing Staff'}">selected</c:if>>Marketing Staff</option>
                            </select>
                            <input style="color: black" name = "searchName" type="text" class="search-bar ml-3" placeholder="Enter user's name" value="${param.searchName}">
                            <input style="color: #000000" type="submit" name = "searchUserSubmit" value="Search">
                        </form>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Role</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Date Of Birth</th>
                                <th>Street</th>
                                <th>City</th>
                                <th>Province</th>
                                <th>Country</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Update/Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.getRole()}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.dateOfBirth}</td>
                                <td>${user.street}</td>
                                <td>${user.city}</td>
                                <td>${user.province}</td>
                                <td>${user.country}</td>
                                <td>${user.phone}</td>
                                <td>${user.email}</td>
                                <td>
                                    <a href="update-user?userId=${user.id}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Update">&#xE254;</i></a>
                                    <a href="delete-user?userId=${user.id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="add" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Title</label>
                                <textarea name="title" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <select name="category" class="form-select" aria-label="Default select example">
                                    <c:forEach begin="1" end="3" var="o">
                                        <option value="1">Giày Adidas</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </a>
    <script src="${pageContext.request.contextPath}/js/manager.js" type="text/javascript"></script>
</body>
</html>