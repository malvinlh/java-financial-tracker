<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Expense List</title>
  <%@ include file="/component/all_css.jsp" %>
  <style>.card-sh { box-shadow: 0 .75rem 1.5rem rgba(0,0,0,.1); }</style>
</head>
<body class="bg-light">
  <%@ include file="/component/navbar.jsp" %>

  <div class="container py-5">
    <h4 class="mb-4">Expense List</h4>

    <!-- Flash message -->
    <c:if test="${not empty msg}">
      <div class="alert alert-${msgType} alert-dismissible fade show" role="alert">
        ${msg}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </c:if>

    <div class="table-responsive">
      <table class="table table-bordered table-striped table-hover">
        <thead class="table-light">
          <tr>
            <th>#</th><th>Title</th><th>Description</th>
            <th>Date</th><th>Time</th><th>Price</th><th>Action</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty expenses}">
              <c:forEach var="exp" items="${expenses}" varStatus="st">
                <tr>
                  <td>${st.index + 1}</td>
                  <td>${exp.title}</td>
                  <td>${exp.description}</td>
                  <td>${exp.date}</td>
                  <td>${exp.time}</td>
                  <td>${exp.price}</td>
                  <td>
                    <a href="<c:url value='/editExpense?id=${exp.id}'/>"
                       class="btn btn-sm btn-primary me-1">Edit</a>
                    <a href="<c:url value='/deleteExpense?id=${exp.id}'/>"
                       class="btn btn-sm btn-danger">Delete</a>
                  </td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="7" class="text-center py-4">No expenses found.</td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>