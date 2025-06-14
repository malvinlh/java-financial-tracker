<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <%@ include file="/component/all_css.jsp" %>
</head>
<body class="d-flex align-items-center justify-content-center"
      style="height:100vh">
  <div class="w-100" style="max-width:400px;">

    <!-- Flash message -->
    <c:if test="${not empty sessionScope.msg}">
      <div class="alert alert-${sessionScope.msgType}
                  alert-dismissible fade show"
           role="alert">
        ${sessionScope.msg}
        <button type="button" class="btn-close"
                data-bs-dismiss="alert"></button>
      </div>
      <c:remove var="msg" scope="session"/>
      <c:remove var="msgType" scope="session"/>
    </c:if>

    <div class="card">
      <div class="card-header text-center fs-4">
        Login
      </div>
      <div class="card-body">
        <form action="<c:url value='/userLogin'/>"
              method="post">
          <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" name="email"
                   class="form-control" required>
          </div>
          <div class="mb-3">
            <label class="form-label">Password</label>
            <input type="password" name="password"
                   class="form-control" required>
          </div>
          <button type="submit"
                  class="btn btn-success w-100">
            Login
          </button>
        </form>
        <p class="mt-3 text-center">
          Don't have an account?
          <a href="<c:url value='/register.jsp'/>">
            Create one
          </a>
        </p>
      </div>
    </div>

  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js">
  </script>
</body>
</html>