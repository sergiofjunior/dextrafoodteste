<%--
  Created by IntelliJ IDEA.
  User: Sérgio
  Date: 19/04/2017
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

    <LINK href="css/style.css" type=text/css rel=stylesheet>

    <script src="js/dextrafood.js"></script>

    <title>Dextra Burger</title>
</head>

<body>

<form id="formularioPedido">

    <div class="menu">

        <div id="div1" width="300px" style="float:left">
            <img src="img/logo.jpg" width="250" height="50" style="margin-left:20px" >
        </div>

        <div id="div2" style="margin-left:30px; text-align:right;" width="300px" style="float:right">
            <ul class="menu-items">
                <li class="item"><a href="#">Unidades Dextra Burger</a></li>
            </ul>
        </div>

    </div>


    <div id="pagina" class="pagina">
        <div class="corpo" id="div3" width="300px" style="margin-bottom: 50px">
            <br>
            <p class="text-font">Pedido Realizado com Sucesso</p>
            <br>



            <br>

            <br><br><br>
            <hr align="center" width="50%" size="1" color="#CDCDCD">
            <br><br>
            <p class="text-font-simple" style="color: gray">Dextra Burger - Todos os direitos reservados.</p>
            <br><br><br>
        </div >
    </div>
</form>
</body>
</html>


