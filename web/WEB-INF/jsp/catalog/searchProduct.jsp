<%@ include file="../common/top.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<div id="BackLink">
  <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">
  <c:if test="${not empty sessionScope.productList }" >
    <table>
      <tr>
        <th>&nbsp;</th>
        <th>Product ID</th>
        <th>Name</th>
      </tr>
  </c:if>

    <c:if test="${not empty sessionScope.productList}">
      <c:forEach var="product" items="${sessionScope.productList}">
        <tr>
          <td>
            <a href="productForm?productId=${product.productId}">${product.description}</a>
          </td>
          <td><b>
            <a href="productForm?productId=${product.productId}">${product.productId}</a>
          </b></td>
          <td>${product.name}</td>
        </tr>
      </c:forEach>
    </c:if>
    <c:if test="${empty sessionScope.productList }">
        <h2 style="color: red"> 查询关键字出错，无此商品！！</h2>
    </c:if>

    <tr>
      <td></td>
    </tr>

  </table>

</div>

<%@ include file="../common/bottom.jsp"%>