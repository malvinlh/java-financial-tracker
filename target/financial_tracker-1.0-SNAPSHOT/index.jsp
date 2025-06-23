<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Financial Tracker Home</title>
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
  </style>
</head>
<body class="bg-light">
  <%@ include file="/component/navbar.jsp" %>

  <section class="hero-section">
    <div class="hero-bg"></div>

    <div class="hero-card text-center">
      <h1 class="display-4 mb-3">Welcome to Financial Tracker!</h1>
      <p class="lead mb-4">Strategy Design Pattern (Add Income)</p>

      <div class="row row-cols-2 g-3 mb-4 justify-content-center">
        <!-- Add Income -->
        <div class="col">
          <a href="<c:url value='/addIncome'/>"
             class="btn btn-success btn-lg w-100">
            <i class="fa-solid fa-wallet me-2"></i>Add Income
          </a>
        </div>
        <!-- View Income -->
        <div class="col">
          <a href="<c:url value='/viewIncome'/>"
             class="btn btn-outline-success btn-lg w-100">
            <i class="fa-solid fa-chart-line me-2"></i>View Income
          </a>
        </div>
        <!-- Add Expense (disabled, red) -->
        <div class="col">
          <a class="btn btn-danger btn-lg w-100 disabled"
             href="#"
             tabindex="-1"
             aria-disabled="true">
            <i class="fa-solid fa-plus me-2"></i>Add Expense
          </a>
        </div>
        <!-- View Expenses (disabled, red) -->
        <div class="col">
          <a class="btn btn-outline-danger btn-lg w-100 disabled"
             href="#"
             tabindex="-1"
             aria-disabled="true">
            <i class="fa-solid fa-eye me-2"></i>View Expenses
          </a>
        </div>
      </div>
    </div>
  </section>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>