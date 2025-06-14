<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  if (session.getAttribute("loginUser") == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>User Home</title>
  <%@ include file="/component/all_css.jsp" %>
  <style>
    .hero-section {
      position: relative;
      height: calc(100vh - 56px);
      display: flex;
      align-items: center;
      justify-content: center;
      overflow: hidden;
    }
    .hero-bg {
      position: absolute;
      top: 0; left: 0;
      width: 100%; height: 100%;
      background: url('<c:url value="/img/wallpaper.png"/>') center/cover no-repeat;
      filter: blur(8px);
      transform: scale(1.05);
      z-index: 0;
    }
    .hero-card {
      position: relative;
      z-index: 1;
      max-width: 800px;
      width: 100%;
      background: #fff;
      border-radius: .75rem;
      box-shadow: 0 .75rem 1.5rem rgba(0,0,0,.1);
      padding: 2.5rem;
    }
    .btn-rows {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      gap: 1rem;
      margin-bottom: 1rem;
    }
    .btn-rows:last-child {
      margin-bottom: 0;
    }
  </style>
</head>
<body class="bg-light">
  <%@ include file="/component/navbar.jsp" %>

  <section class="hero-section">
    <div class="hero-bg"></div>

    <div class="hero-card text-center">
      <h1 class="display-4 mb-3">
        Welcome, <c:out value="${sessionScope.loginUser.fullname}"/>!
      </h1>
      <p class="lead mb-4">You have successfully logged in.</p>

      <div class="btn-rows">
        <a href="<c:url value='/addExpense'/>"
           class="btn btn-primary btn-lg px-4">
          <i class="fa-solid fa-plus me-2"></i>Add Expense
        </a>
        <a href="<c:url value='/viewExpense'/>"
           class="btn btn-outline-primary btn-lg px-4">
          <i class="fa-solid fa-eye me-2"></i>View Expenses
        </a>
      </div>

      <div class="btn-rows">
        <a href="<c:url value='/addIncome'/>"
           class="btn btn-success btn-lg px-4">
          <i class="fa-solid fa-wallet me-2"></i>Add Income
        </a>
        <a href="<c:url value='/viewIncome'/>"
           class="btn btn-outline-success btn-lg px-4">
          <i class="fa-solid fa-chart-line me-2"></i>View Income
        </a>
      </div>
    </div>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>