<%@ include file="../common/top.jsp"%>

<div id="Catalog">
  <form action="saveAccount" method="post">
    <h3>User Information</h3>

    <table>
      <tr>
        <td>User Name:</td>
        <td><c:out value="${sessionScope.loginAccount.username}" /></td>
      </tr>
      <tr>
        <td>New password:</td>
        <td><input type="text" name="password" autofocus="autofocus"/></td>
      </tr>
      <tr>
        <td>Repeat password:</td>
        <td><input type="text" name="repeatedPassword" /></td>
      </tr>
    </table>
    <%@ include file="accountList.jsp"%>
    <input type="submit" name="editAccount" value="Save Account Information" />

  </form>
  <!--event="listOrders-->
  <a href="listOrder?username=${sessionScope.loginAccount.username}">My Orders</a>
</div>

<%@ include file="../common/bottom.jsp"%>
