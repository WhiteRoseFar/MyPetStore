<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%--<%@ taglib prefix="stripes"--%>
<%--           uri="http://stripes.sourceforge.net/stripes.tld"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../common/top.jsp"%>

<style type="text/css">
    *{
        padding:0;
        margin:0;/*去内外边界*/
    }
    /*.content {*/
    /*    margin: 40px auto;!*居中显示*!*/
    /*    width: 450px;*/
    /*    height: 301px;*/
    /*    border:10px solid #808080;!*给外部加一个边框*!*/
    /*}*/
    /*.content img {*/
    /*    width: 450px;*/
    /*    height: 301px;*/
    /*}*/
    .content img:hover{
                     -webkit-transform: scale(1.2); /*Safari 和 Chrome*/
                     -moz-transform: scale(1.2); /*Firefox*/
                     -ms-transform: scale(1.2); /*IE9*/
                     -o-transform: scale(1.2); /*Opera*/
                 }
    .content img {
                      /*width: 450px;*/
                      /*height: 301px;*/
                      transition-duration:0.5s; /*过度的时间为0，5秒*/
                  }
</style>

<div id="Welcome">
    <div id="WelcomeContent">
        <!-- 显示登录用户的名 -->
    </div>
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent" class = "content">
            <a href="categoryForm?categoryId=FISH"><img src="images/fish_icon.gif"></a><br />
            Saltwater, Freshwater <br />

            <a href="categoryForm?categoryId=DOGS"><img src="images/dogs_icon.gif"/></a><br />
            Various Breeds <br />

            <a href="categoryForm?categoryId=CATS"><img src="images/cats_icon.gif"></a><br />
            Various Breeds, Exotic Varieties <br />

            <a href="categoryForm?categoryId=REPTILES"><img src="images/reptiles_icon.gif"></a><br />
            Lizards, Turtles, Snakes <br />

            <a href="categoryForm?categoryId=BIRDS"><img src="images/birds_icon.gif"></a><br />
            Exotic Varieties <br />
        </div>
    </div>

    <div id="MainImage" style="z-index: 3">
        <div id="MainImageContent">
            <map name="estoremap">
                <area alt="Birds" coords="72,2,280,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT" />
                <area alt="Fish" coords="2,180,72,250"
                      href="categoryForm?categoryId=FISH" shape="RECT" />
                <area alt="Dogs" coords="60,250,130,320"
                      href="categoryForm?categoryId=DOGS" shape="RECT" />
                <area alt="Reptiles" coords="140,270,210,340"
                      href="categoryForm?categoryId=REPTILES" shape="RECT" />
                <area alt="Cats" coords="225,240,295,310"
                      href="categoryForm?categoryId=CATS" shape="RECT" />
                <area alt="Birds" coords="280,180,350,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT" />
            </map>
            <img height="355" src="images/splash.gif"
                 usemap="#estoremap" width="350" style="z-index: 7"/></div>
    </div>

    <div id="Separator">&nbsp;</div>
</div>


<style type="text/css">
    /*悬浮框  */

    .float {
        position: absolute;
        z-index: 1;
    }
    /*图片大小*/

    .floatsize {
        width: 200px;
        height: 200px;
    }
</style>


<body>

<div style = "display: flex; z-index: 1" class = "content">
    <div id="float0" class="float floatsize" style="display: none; z-index: 0"><a href = "categoryForm?categoryId=BIRDS"><img id="floatImg0" src = "images/bird1.gif" class = "floatsize" onmouseover="MouseOver(0)" onmouseout="MouseOut(0)"></a>></div>
    <div id="float1" class="float floatsize" style="display: none; z-index: 1"><a href = "categoryForm?categoryId=CATS"><img id="floatImg1" src = "images/cat1.gif" class = "floatsize" onmouseover="MouseOver(1)" onmouseout="MouseOut(1)"></a></div>
    <div id="float2" class="float floatsize" style="display: none; z-index: 2"><a href = "categoryForm?categoryId=FISH"><img id="floatImg2" src = "images/fish1.gif" class = "floatsize" onmouseover="MouseOver(2)" onmouseout="MouseOut(2)"></a></div>
    <div id="float3" class="float floatsize" style="display: none; z-index: 3"><a href = "categoryForm?categoryId=DOGS"><img id="floatImg3" src = "images/dog1.gif" class = "floatsize" onmouseover="MouseOver(3)" onmouseout="MouseOut(3)"></a></div>
    <div id="float4" class="float floatsize" style="display: none; z-index: 4"><a href = "categoryForm?categoryId=REPTILES"><img id="floatImg4" src = "images/lizard1.gif" class = "floatsize" onmouseover="MouseOver(4)" onmouseout="MouseOut(4)"></a></div>
</div>

<div style="height: 300px"></div>

</body>



<script>
    //定义全局变量
    var moveX = [350, 500, 800, 350, 500]; //X轴方向上移动的距离
    var moveY = [100, 100, 100, 500, 500]; //Y轴方向上移动的距离
    var stepX = 5; //图片X轴移动的速度
    var stepY = 6; //图片Y轴移动的速度
    var directionX = [0, 0, 0, 0, 0]; //设置图片在X轴方向上的移动方向   0:向右  1:向左
    var directionY = [0, 0, 0, 0, 0]; //设置图片在Y轴方向上的移动方向   0:向下  1:向上
    var interval = [60, 70, 80, 90, 100];
    var interId = [0, 0, 0, 0, 0]

    function changePos(divVal, picVal, picId) {
        var img = document.getElementById(divVal); //获得图片所在层的ID
        var height = document.documentElement.clientHeight; //浏览器的高度
        var width = document.documentElement.clientWidth; //浏览器的宽度
        var imgHeight = document.getElementById(picVal).height; //飘浮图片的高度
        var imgWidth = document.getElementById(picVal).width; //瓢浮图片的宽度
        //设置飘浮图片距离浏览器左侧位置
        img.style.left = parseInt(moveX[picId] + document.documentElement.scrollLeft) + "px";
        //设置飘浮图片距离浏览器右侧位置
        img.style.top = parseInt(moveY[picId] + document.documentElement.scrollTop) + "px";

        //设置图片在Y轴上的移动规律
        if(directionY[picId] == 0) {
            //飘浮图片在Y轴方向上向下移动
            moveY[picId] += stepY;
        } else {
            //飘浮图片在Y轴方向上向上移动
            moveY[picId] -= stepY;
        }
        if(moveY[picId] < 100) {
            //如果飘浮图片飘浮到顶端的时候，设置图片在Y轴方向上向下移动
            directionY[picId] = 0;
            moveY[picId] = 100;
        }
        if(moveY[picId] > (height - imgHeight - 200)) {
            //如果飘浮图片飘浮到浏览器底端的时候，设置图片在Y轴方向上向上移动
            directionY[picId] = 1;
            moveY[picId] = (height - imgHeight - 200);
        }

        //设置图片在X轴上的移动规律
        if(directionX[picId] == 0) {
            moveX[picId] += stepX;
        } else {
            moveX[picId] -= stepX;
        }
        if(moveX[picId] < 350) {
            //如果飘浮图片飘浮到浏览器左侧的时候，设置图片在X轴方向上向右移动
            directionX[picId] = 0;
            moveX[picId] = 350;
        }
        if(moveX[picId] > (width - imgWidth - 150)) {
            //如果飘浮图片飘浮到浏览器右侧的时候，设置图片在X轴方向上向左移动
            directionX[picId] = 1;
            moveX[picId] = (width - imgWidth - 150);
        }
        if(document.getElementById(divVal).style.display == "none")
            document.getElementById(divVal).style.display = "block";
    }

    function MouseOver(num) {
        document.getElementById("float" + num).style.zIndex = 5;
        clearInterval(interId[num]);
    }


    function MouseOut(num){
        var temp = num.toString();
        var word = "changePos('float" + temp + "','floatImg" +  temp + "'," + temp +")";
        document.getElementById("float" + num).style.zIndex = num;
        interId[num] = setInterval(word, interval[num]);
    }


    interId[0] = setInterval("changePos('float0', 'floatImg0', 0)", 60);
    interId[1] = setInterval("changePos('float1', 'floatImg1', 1)", 70);
    interId[2] = setInterval("changePos('float2', 'floatImg2', 2)", 80);
    interId[3] = setInterval("changePos('float3', 'floatImg3', 3)", 90);
    interId[4] = setInterval("changePos('float4', 'floatImg4', 4)", 100);
</script>

<%@ include file="../common/bottom.jsp"%>