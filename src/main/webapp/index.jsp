<%@ page import="com.db.HibernateUtil" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Financial Tracker</title>
    <%@ include file="component/all_css.jsp" %>

    <style>
      html, body {
        margin: 0; padding: 0;
        height: 100%; overflow: hidden;
      }
      .hero-container {
        height: calc(100vh - 56px);
      }
      .hero-container img {
        width: 100%; height: 100%;
        object-fit: cover; display: block;
      }
    </style>
</head>
<body>
  <%@ include file="component/navbar.jsp" %>

  <%-- Initialize Hibernate to warm up SessionFactory --%>
  <%
      SessionFactory factory = HibernateUtil.getSessionFactory();
  %>

  <div class="hero-container">
    <img src="img/wallpaper.png" alt="Financial Dashboard">
  </div>
</body>
</html>