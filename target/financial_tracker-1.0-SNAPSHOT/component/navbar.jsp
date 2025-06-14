<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
  <div class="container-fluid">
    <a class="navbar-brand" href="<c:url value='/index.jsp'/>">
      <i class="fa-solid fa-money-check"></i> Financial Tracker
    </a>
    <button class="navbar-toggler" type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse"
         id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active"
             href="<c:url value='/index.jsp'/>">
            <i class="fa-solid fa-house"></i> Home
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <c:choose>
          <c:when test="${not empty sessionScope.loginUser}">
            <li class="nav-item">
              <a class="nav-link" 
                 href="<c:url value='/addIncome'/>">
                <i class="fa-solid fa-wallet me-1"></i> Add Income
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" 
                 href="<c:url value='/viewIncome'/>">
                <i class="fa-solid fa-chart-line me-1"></i> View Income
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link"
                 href="<c:url value='/addExpense'/>">
                <i class="fa-solid fa-plus"></i> Add Expense
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link"
                 href="<c:url value='/viewExpense'/>">
                <i class="fa-solid fa-eye"></i> View Expenses
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link"
                 href="<c:url value='/user/home.jsp'/>">
                <i class="fa-solid fa-user"></i>
                ${sessionScope.loginUser.fullname}
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link"
                 href="<c:url value='/logout'/>">
                <i class="fa-solid fa-right-from-bracket"></i> Logout
              </a>
            </li>
          </c:when>
          <c:otherwise>
            <li class="nav-item">
              <a class="nav-link active"
                 href="<c:url value='/login.jsp'/>">
                <i class="fa-solid fa-right-to-bracket"></i> Login
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link active"
                 href="<c:url value='/register.jsp'/>">
                <i class="fa-solid fa-user-plus"></i> Register
              </a>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>