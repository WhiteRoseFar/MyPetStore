<%@ include file="../common/top.jsp"%>

<style type="text/css">
    /*body{*/
    /*    display: flex;*/
    /*    flex-wrap: wrap;*/
    /*    background-color: rgb(233, 233, 233);*/
    /*    flex-direction: column;*/
    /*    align-items: center;*/
    /*}*/
    .itemBox{
        width: 280px;
        height: 280px;
        overflow: hidden;
        background-color: white;
        display: flex;
        justify-content: center;
        align-items: center;
        margin-bottom: 20px;
        border-radius: 30px;
        transition: all 0.5s ease-in-out;
        box-shadow: 0px 0px 5px 4px #cccccc;
    }
    .itemBox img{
        width: 100%;
    }
    .title{
        font-size: 20px;
        padding: 0 20px;
    }
    .zk{
        width: 100%;
        height: 100vh;
        border-radius: 0px;
    }
    .zk p{
        padding: 10px;
    }

</style>

<div ></div>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<%--<div id="Catalog">--%>

<%--    <h2>${sessionScope.category.name}</h2>--%>

<%--    <table>--%>
<%--        <tr>--%>
<%--            <th>Product ID</th>--%>
<%--            <th>Name</th>--%>
<%--        </tr>--%>
<%--        <c:forEach var="product" items="${sessionScope.productList}">--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <a href="productForm?productId=${product.productId}"> ${product.productId}</a>--%>
<%--                </td>--%>
<%--                <td>${product.name}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>

<%--</div>--%>

<div id="Catalog">

    <h2>${sessionScope.category.name}</h2>
            <div style="margin-left: 100px; display: flex; flex-wrap: wrap;">
        <c:forEach var="product" items="${sessionScope.productList}" varStatus="status">
            <div class="itemBox" style="margin-left: 100px;" onclick="window.location.href='productForm?productId=${product.productId}'">
                ${product.description}
            </div>
                <c:if test="${status.count % 3 == 0}">
            </div>
            <div style="margin-left: 300px; display: flex">
                </c:if>
        </c:forEach>
            </div>
    </div>

</div>

<%@ include file="../common/bottom.jsp"%>


