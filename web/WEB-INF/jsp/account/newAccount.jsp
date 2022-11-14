<%@ include file="../common/top.jsp"%>

${sessionScope.messageAccount}
<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>
<div id="Catalog">
  <form action="newAccount" method="post">
    <h3>User Information</h3>

    <table>
      <tr>
        <td>User ID:</td>
        <td><input type="text" name="username"/></td>
      </tr>
      <tr>
        <td>New password:</td>
        <td><input type="text" name="password" /></td>
      </tr>
      <tr>
        <td>Repeat password:</td>
        <td><input type="text" name="repeatedPassword" /></td>
      </tr>
    </table>

    <%@ include file="accountList.jsp"%>
    <input type="submit" name="newAccount" value="Save Account Information" />
  </form>
</div>

<%@ include file="../common/bottom.jsp"%>