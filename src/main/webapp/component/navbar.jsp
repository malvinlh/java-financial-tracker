<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-success">
  <div class="container-fluid ps-4">
    <a class="navbar-brand me-3" href="<c:url value='/index.jsp'/>">
      <i class="fa-solid fa-money-check"></i>
      Financial Tracker
    </a>
    <button class="navbar-toggler" type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarContent">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" 
             href="<c:url value='/index.jsp'/>">
            <i class="fa-solid fa-house"></i> Home
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>