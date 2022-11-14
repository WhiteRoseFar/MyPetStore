<%@ include file="../common/top.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>
<div id="Catalog">

  <form action="signOn" method="post">
    <p>Please enter your username and password.</p>
    <c:if test="${requestScope.signOnMsg != null}">
      <p>
        <font color="red">${requestScope.signOnMsg}</font>
      </p>
    </c:if>
    <p>
      Username:<input type="text" name="username"/> <br />
      Password:<input type="password" name="password"/> <br />
      VeriCode:<input type="text"  name = "verifyCode"/><br />
       <img class="img" src="<%=request.getContextPath()%>/verifyCode"   onClick="change()" id="picture"/>
       看不清？<input type="button" value="换一张" onClick="change()" /><br/>
    </p>
    <input type="submit" value="Login">
  </form>
  Need a user name and password?
  <a href="registerForm">Register Now!</a>

</div>

<%@ include file="../common/bottom.jsp"%>