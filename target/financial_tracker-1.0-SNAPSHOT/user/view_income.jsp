<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="com.db.HibernateUtil, com.dao.IncomeDao, com.entity.Income, java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>
<%
  IncomeDao dao = new IncomeDao(HibernateUtil.getSessionFactory());
  List<Income> incomes = dao.getAllIncomes();
  request.setAttribute("incomes", incomes);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Income List</title>
    <%@ include file="/component/all_css.jsp" %>
    <style>.card-sh { box-shadow: 0 .75rem 1.5rem rgba(0,0,0,.1); }</style>
</head>
<body class="bg-light">
  <%@ include file="/component/navbar.jsp" %>
  <div class="container py-5">
    <h4 class="mb-4">Income List</h4>
    <div class="table-responsive">
      <table class="table table-bordered table-striped table-hover">
        <thead class="table-light">
          <tr>
            <th>#</th><th>Title</th><th>Description</th>
            <th>Date</th><th>Time</th><th>Amount</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty incomes}">
              <c:forEach var="inc" items="${incomes}" varStatus="st">
                <tr>
                  <td>${st.index + 1}</td>
                  <td>${inc.title}</td>
                  <td>${inc.description}</td>
                  <td>${inc.date}</td>
                  <td>${inc.time}</td>
                  <td>${inc.amount}</td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="6" class="text-center">No income found.</td>
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