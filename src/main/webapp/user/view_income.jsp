<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ page import="com.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>
<%
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
        response.sendRedirect(request.getContextPath()
                              + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Incomes</title>
    <%@ include file="/component/all_css.jsp" %>
    <style>
      .card-sh { box-shadow: 0 .75rem 1.5rem rgba(0,0,0,.1); }
    </style>
</head>
<body class="bg-light">
  <%@ include file="/component/navbar.jsp" %>

  <div class="container py-5">

    <!-- Flash message -->
    <c:if test="${not empty msg}">
      <div class="alert alert-${msgType}
                  alert-dismissible fade show"
           role="alert">
        ${msg}
        <button type="button" class="btn-close"
                data-bs-dismiss="alert"></button>
      </div>
    </c:if>

    <div class="card card-sh">
      <div class="card-header text-center">
        <h4 class="mb-0">Your Incomes</h4>
      </div>
      <div class="card-body">
        <div class="table-responsive">
          <table class="table table-bordered
                        table-striped table-hover mb-0">
            <thead class="table-light">
              <tr>
                <th>#</th><th>Title</th>
                <th>Description</th><th>Date</th>
                <th>Time</th><th>Amount</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <c:choose>
                <c:when test="${not empty requestScope.incomes}">
                  <c:forEach var="inc" items="${requestScope.incomes}"
                             varStatus="st">
                    <tr>
                      <th scope="row">${st.index + 1}</th>
                      <td><c:out value="${inc.title}"/></td>
                      <td><c:out value="${inc.description}"/></td>
                      <td><c:out value="${inc.date}"/></td>
                      <td><c:out value="${inc.time}"/></td>
                      <td><c:out value="${inc.amount}"/></td>
                      <td>
                        <a href="<c:url value='/editIncome?id=${inc.id}'/>"
                           class="btn btn-sm btn-primary me-1">
                          Edit
                        </a>
                        <a href="<c:url value='/deleteIncome?id=${inc.id}'/>"
                           class="btn btn-sm btn-danger">
                          Delete
                        </a>
                      </td>
                    </tr>
                  </c:forEach>
                </c:when>
                <c:otherwise>
                  <tr>
                    <td colspan="7"
                        class="text-center py-4">
                      No incomes found.
                    </td>
                  </tr>
                </c:otherwise>
              </c:choose>
            </tbody>
          </table>
        </div>
      </div>
    </div>

  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js">
  </script>
</body>
</html>