<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
    <script src="https://kit.fontawesome.com/b685510abd.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="background">
    <div class="account-content">
        <div class="account-title">Please enter your information of the new account.</div>
        <form action="register" class="account-form" method="post">

            <c:if test="${requestScope.registerMsg!=null}">
                <div class="account-form-error">
                    <p class="error-msg">${requestScope.registerMsg}</p>
                </div>
            </c:if>


            <div class="account-form-item">
                <label for="username" class="account-form-label">
                    <!--用户名图标-->
                    <i class="fa-solid fa-user"></i>
                </label>
                <input type="text" class="account-form-input" name="username" id="username" placeholder="Please input username" autocomplete="off">
            </div>


            <div class="account-form-item">
                <label for="password" class="account-form-label">
                    <!--密码图标-->
                    <i class="fa-solid fa-lock"></i>
                </label>
                <input type="password" class="account-form-input" name="password" id="password" placeholder="Please input password" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="email" class="account-form-label">
                    <!--电子邮箱图标-->
                    <i class="fa-solid fa-envelope"></i>
                </label>
                <input type="text" class="account-form-input" name="email" id="email" placeholder="Please input email" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="firstname" class="account-form-label">
                    <!--名字图标-->
                    <i class="fa-solid fa-signature"></i>
                </label>
                <input type="text" class="account-form-input" name="firstname" id="firstname" placeholder="Please input firstname" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="lastname" class="account-form-label">
                    <!--姓图标-->
                    <i class="fa-thin fa-signature"></i>
                </label>
                <input type="text" class="account-form-input" name="lastname" id="lastname" placeholder="Please input lastname" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="status" class="account-form-label">
                    <!--状态图标-->
                    <i class="fa-solid fa-circle-check"></i>
                </label>
                <input type="text" class="account-form-input" name="status" id="status" placeholder="Please input status" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="address1" class="account-form-label">
                    <!--地址1图标-->
                    <i class="fa-sharp fa-solid fa-location-dot"></i>
                </label>
                <input type="text" class="account-form-input" name="address1" id="address1" placeholder="Please input address1" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="address2" class="account-form-label">
                    <!--地址2图标-->
                    <i class="fa-sharp fa-solid fa-location-dot"></i>
                </label>
                <input type="text" class="account-form-input" name="address2" id="address2" placeholder="Please input address2" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="country" class="account-form-label">
                    <!--国家图标-->
                    <i class="fa-solid fa-globe"></i>
                </label>
                <input type="text" class="account-form-input" name="country" id="country" placeholder="Please input country" autocapitalize="off">
            </div>



            <div class="account-form-item">
                <label for="zip" class="account-form-label">
                    <!--邮编图标-->
                    <i class="fa-solid fa-globe"></i>
                </label>
                <input type="text" class="account-form-input" name="zip" id="zip" placeholder="Please input zip" autocapitalize="off">
            </div>




            <div class="account-form-item">
                <label for="state" class="account-form-label">
                    <!--省份图标-->
                    <i class="fa-solid fa-space-station-moon"></i>
                </label>
                <input type="text" class="account-form-input" name="state" id="state" placeholder="Please input state" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="city" class="account-form-label">
                    <!--城市图标-->
                    <i class="fa-solid fa-city"></i>
                </label>
                <input type="text" class="account-form-input" name="city" id="city" placeholder="Please input city" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="phone" class="account-form-label">
                    <!--电话号图标-->
                    <i class="fa-solid fa-phone"></i>
                </label>
                <input type="text" class="account-form-input" name="phone" id="phone" placeholder="Please input phone" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="favouriteCategoryId" class="account-form-label">
                    <!--最喜欢动物图标-->
                    <i class="fa-solid fa-dove"></i>
                </label>
                <input type="text" class="account-form-input" name="favouritecategoryid" id="favouriteCategoryId" placeholder="Please input favouriteCategoryId" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="password" class="account-form-label">
                    <!--语言偏好图标-->
                    <i class="fa-solid fa-language"></i>
                </label>
                <input type="text" class="account-form-input" name="languagepreference" id="languagePreference" placeholder="Please input languagePreference" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="listOption" class="account-form-label">
                    <!--ListOption图标-->
                    <i class="fa-solid fa-star"></i>
                </label>
                <input type="text" class="account-form-input" name="listoption" id="listOption" placeholder="Please input listOption" autocapitalize="off">
            </div>


            <div class="account-form-item">
                <label for="bannerOption" class="account-form-label">
                    <!--BannerOption图标-->
                    <i class="fa-solid fa-shield-halved"></i>
                </label>
                <input type="text" class="account-form-input" name="banneroption" id="bannerOption" placeholder="Please input bannerOption" autocapitalize="off">
            </div>



            <div class="account-form-item">
                <input type="submit" value="register" class="account-form-submit">
            </div>
            <div class="account-form-link">
                <a href="signonForm" class="link">back to signOn</a>
            </div>

        </form>
    </div>
</div>
</body>
</html>sp"%>