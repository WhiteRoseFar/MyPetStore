<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%--<%@ taglib prefix="stripes"--%>
<%--           uri="http://stripes.sourceforge.net/stripes.tld"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../common/top.jsp"%>
<div id="Welcome">
    <div id="WelcomeContent">
        <!-- 显示登录用户的名 -->
    </div>
</div>

<div id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
            <a href=""><img src="images/fish_icon.gif"></a><br />
            Saltwater, Freshwater <br />

            <a href=""><img src="images/dogs_icon.gif"></a><br />
            Various Breeds <br />

            <a href=""><img src="images/cats_icon.gif"></a><br />
            Various Breeds, Exotic Varieties <br />

            <a href=""><img src="images/reptiles_icon.gif"></a><br />
            Lizards, Turtles, Snakes <br />

            <a href=""><img src="images/birds_icon.gif"></a><br />
            Exotic Varieties <br />
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <area alt="Birds" coords="72,2,280,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT" />
                <area alt="Fish" coords="2,180,72,250"
                      href="" shape="RECT" />
                <area alt="Dogs" coords="60,250,130,320"
                      href="" shape="RECT" />
                <area alt="Reptiles" coords="140,270,210,340"
                      href="" shape="RECT" />
                <area alt="Cats" coords="225,240,295,310"
                      href="" shape="RECT" />
                <area alt="Birds" coords="280,180,350,250"
                      href="" shape="RECT" />
            </map>
            <img height="355" src="images/splash.gif" align="middle"
                 usemap="#estoremap" width="350" /></div>
    </div>

    <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/bottom.jsp"%>