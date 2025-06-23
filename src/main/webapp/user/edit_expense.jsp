<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Expense</title>
  <%@ include file="/component/all_css.jsp" %>
  <style>.card-sh { box-shadow: 0 .75rem 1.5rem rgba(0,0,0,.1); }</style>
</head>
<body class="bg-light">
  <%@ include file="/component/navbar.jsp" %>

  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card card-sh">

          <div class="card-header text-center">
            <h3>Edit Expense</h3>
            <c:if test="${not empty msg}">
              <div class="alert alert-${msgType}
                          alert-dismissible fade show"
                   role="alert">
                ${msg}
                <button type="button"
                        class="btn-close"
                        data-bs-dismiss="alert"></button>
              </div>
            </c:if>
          </div>

          <div class="card-body">
            <form action="<c:url value='/editExpense'/>" method="post">
              <input type="hidden" name="id"
                     value="${expense.id}"/>

              <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input id="title" name="title"
                       type="text"
                       class="form-control"
                       value="${expense.title}"
                       required>
              </div>

              <div class="mb-3">
                <label for="date" class="form-label">Date</label>
                <input id="date" name="date"
                       type="date"
                       class="form-control"
                       value="${expense.date}"
                       required>
              </div>

              <div class="mb-3">
                <label for="time" class="form-label">Time</label>
                <input id="time" name="time"
                       type="time"
                       class="form-control"
                       value="${expense.time}"
                       required>
              </div>

              <div class="mb-3">
                <label for="description"
                       class="form-label">Description</label>
                <input id="description"
                       name="description"
                       type="text"
                       class="form-control"
                       value="${expense.description}">
              </div>

              <div class="mb-3">
                <label for="price" class="form-label">Price</label>
                <input id="price" name="price"
                       type="number" step="0.01"
                       class="form-control"
                       value="${expense.price}"
                       required>
              </div>

              <div class="d-flex gap-2">
                <button type="submit"
                        class="btn btn-primary w-100">
                  Save Changes
                </button>
                <a href="<c:url value='/viewExpense'/>"
                   class="btn btn-outline-secondary w-100">
                  Cancel
                </a>
              </div>
            </form>
          </div>

        </div>
      </div>
    </div>
  </div>

  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js">
  </script>
</body>
</html>