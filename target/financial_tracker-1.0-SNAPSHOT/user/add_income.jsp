<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>
<%
    if (session.getAttribute("loginUser") == null) {
        response.sendRedirect(request.getContextPath()
                              + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Income</title>
    <%@ include file="/component/all_css.jsp" %>
    <style>
      .card-sh { box-shadow: 0 .75rem 1.5rem rgba(0,0,0,.1); }
    </style>
</head>
<body class="bg-light">
  <%@ include file="/component/navbar.jsp" %>

  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card card-sh">

          <!-- Header + Flash -->
          <div class="card-header text-center">
            <h3 class="mb-3">Add Income</h3>
            <c:if test="${not empty msg}">
              <div class="alert alert-${msgType}
                          alert-dismissible fade show"
                   role="alert">
                ${msg}
                <button type="button" class="btn-close"
                        data-bs-dismiss="alert"></button>
              </div>
            </c:if>
          </div>

          <!-- Form -->
          <div class="card-body">
            <form action="<c:url value='/saveIncome'/>"
                  method="post">
              <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input id="title" name="title" type="text"
                       class="form-control" required>
              </div>
              <div class="mb-3">
                <label for="date" class="form-label">Date</label>
                <input id="date" name="date" type="date"
                       class="form-control" required>
              </div>
              <div class="mb-3">
                <label for="time" class="form-label">Time</label>
                <input id="time" name="time" type="time"
                       class="form-control" required>
              </div>
              <div class="mb-3">
                <label for="description"
                       class="form-label">Description</label>
                <input id="description" name="description"
                       type="text" class="form-control">
              </div>
              <div class="mb-3">
                <label for="amount" class="form-label">Amount</label>
                <input id="amount" name="amount"
                       type="number" step="0.01"
                       class="form-control" required>
              </div>
              <button type="submit"
                      class="btn btn-success w-100">
                Add Income
              </button>
            </form>
          </div>

        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js">
  </script>
</body>
</html>