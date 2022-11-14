<%@ include file="../common/top.jsp"%>

<style type="text/css">
    .itemBox{
        width: 280px;
        height: 200px;
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

<div id="BackLink">
    <a href="categoryForm?categoryId=${sessionScope.category.categoryId}">
        Return to ${sessionScope.category.name}
    </a>
</div>

<div id="Catalog">

    <h2>${sessionScope.product.name}</h2>

    <table>
        <tr>
            <th>Item ID</th>
            <th>Product ID</th>
            <th>Description</th>
            <th>List Price</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach var="item" items="${sessionScope.itemList}">
            <tr>
                <td>
                    <a href="itemForm?itemId=${item.itemId}">${item.itemId}</a>
                </td>

                <td>${item.product.productId}</td>
                <td>${item.attribute1} ${item.attribute2} ${item.attribute3}
                        ${item.attribute4} ${item.attribute5} ${sessionScope.product.name}</td>
                <td><fmt:formatNumber value="${item.listPrice}"
                                      pattern="$#,##0.00" /></td>
                <td>
                    <a href="addItemToCart?workingItemId=${item.itemId}" class="Button">
                        Add to Cart
                    </a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
            </td>
        </tr>
    </table>

</div>

<div style="margin-left: 400px">

<c:forEach items="${sessionScope.itemList}" var="item" varStatus="status">

    <div class="itemBox" style="margin-left: 250px">
<%--        <p>&nbsp;&nbsp;</p>>--%>
<%--        <p>&nbsp;&nbsp;</p>>--%>
<%--        <p>&nbsp;&nbsp;</p>>--%>
        <div style="width: 100px; height: 100px">
            ${product.description}
        </div>
<%--        <p></p>--%>
<%--        <p>--%>
                <div id="Catalog">

                    <table>
<%--                        <tr>--%>
<%--                            <td>--%>
<%--                                    ${sessionScope.product.description}--%>
<%--                            </td>--%>
<%--                        </tr>--%>

                        <tr>
                            <td>
                                <b>
                                    <a href="itemForm?itemId=${item.itemId}">
                                            ${item.itemId}
                                    </a>
                                </b>
                            </td>
                        </tr>

                        <tr>
                            <td><b><font size="4">
                                    ${item.attribute1}
                                    ${item.attribute2} ${item.attribute3}
                                    ${item.attribute4} ${item.attribute5}
                                    ${product.name}
                            </font></b></td>
                        </tr>
                        <tr>
                            <td>${product.name}</td>
                        </tr>
                        <tr>
                            <td><c:if test="${item.quantity <= 0}">
                                Back ordered.
                            </c:if> <c:if test="${item.quantity > 0}">
                                $item.quantity} in stock.
                            </c:if></td>
                        </tr>
                        <tr>
                            <td><fmt:formatNumber value="${item.listPrice}"
                                                  pattern="$#,##0.00" /></td>
                        </tr>

                        <tr>
                            <td>
                                <a href="addItemToCart?workingItemId=${item.itemId}" class="Button">
                                    Add to Cart
                                </a>
                            </td>
                        </tr>
                    </table>

                </div>

    </div>
    <c:if test="${status.count % 3 == 0}">
        </div>
        <div style="margin-left: 400px">
    </c:if>
</c:forEach>
        </div>

<script>
    //获取每一个菜单
    const itemBox = Array.from(document.getElementsByClassName('itemBox'));
    const body = document.querySelector('body')
    //给每一个菜单加上点击事件
    itemBox.forEach(r=>{
        r.addEventListener('click',function(e){
            //获取body滚动高度
            const height = body.scrollHeight
            //切换菜单展开和收起状态
            r.classList.toggle('zk')
            //判断菜单是否展开
            if(r.classList.contains('zk')){
                //给body加上一个屏幕的高度
                body.style.height = height + window.screen.height+'px'
                //固定滚动条
                body.style.overflow = 'hidden'
            }else{
                //移除增加的高度
                body.style.height = 'auto'
                body.style.overflow = 'auto'
            }
            window.scrollTo({ top: this.offsetTop, behavior: "smooth" })

        },true)
    })
</script>



<%@ include file="../common/bottom.jsp"%>





