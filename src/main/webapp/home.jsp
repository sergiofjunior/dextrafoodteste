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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>

    <title>Dextra Burger</title>
</head>

<body>

<form id="formulario">

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
            <p class="text-font">Cardápio</p>
            <br>


            <div class="table-title">
                <table class="table-fill" id="table1" width="480px">
                    <tr>
                        <th class="text-left"></th>
                        <th class="text-left">Lanche</th>
                        <th class="text-left">Ingredientes</th>
                        <th class="text-left">Preço</th>
                    </tr>


                    <c:forEach var="burger" items="${burgers}">
                        <tr>
                            <td class="text-left"><input type="radio" name="burger" id="burger" value="${burger.id}"/></td>
                            <td class="text-left">${burger.nome}</td>
                            <td class="text-left">${burger.mostraIngredientes()}</td>
                            <td class="text-right"><fmt:formatNumber value="${burger.preco}" minFractionDigits="2" type="currency"/></td>
                        </tr>

                    </c:forEach>

                </table>
            </div>
            <br>
            <p class="text-font-simple"> <b> Ingredientes Extras</b> </p>

            <div>
                <table id="table2" width="70%" align="center">
                    <tr>
                        <th class="text-left"></th>
                        <th class="text-left">Ingrediente</th>
                        <th class="text-right">Preço</th>
                        <th class="text-right">Qtd.</th>
                    </tr>
                    <c:forEach var="ingredientes" items="${ingredientes}" >
                        <tr>
                            <td class="text-left"><input type="checkbox" name="ingrediente" value="${ingredientes.id}"></td>
                            <td class="text-left">${ingredientes.nome}</td>
                            <td class="text-right"><fmt:formatNumber value="${ingredientes.preco}" minFractionDigits="2" type="currency"/></td>
                            <td class="text-right"><input type="text" name="qtd" id="qtd${ingredientes.id}" value="${ingredientes.qtd}" size="2px" maxlength="1"style="text-align: right"/></td>
                        </tr>
                    </c:forEach>

                </table>
            </div>

            <br>

            <div id="msgResponse" style="text-align: center">
                <p class="text-font" style="font-size: 10px; font-style: italic"><b></b></p>
            </div>

            <br>

            <div style="text-align: center">
                <input type="button" id="calcular" value="Calcular Pedido"></input>&nbsp;
            </div>

            <script>
                $(document).ready(function(){
                    $("#qtd1").keypress(verificaNumero);
                    $("#qtd2").keypress(verificaNumero);
                    $("#qtd3").keypress(verificaNumero);
                    $("#qtd4").keypress(verificaNumero);
                    $("#qtd5").keypress(verificaNumero);

                    $('#calcular').click(function(){

                        document.getElementById('msgResponse').innerHTML="";
                        var idBurger = $('input:radio[name=burger]:checked').val();
                        idBurger = parseInt(idBurger);
                        var idAndQtdIngredientes = new Array();;
                        $("input[name='ingrediente']:checked").each(function ()
                        {
                            if($("#qtd"+parseInt($(this).val())).val() != ''){
                                idAndQtdIngredientes.push(parseInt($(this).val()));
                                idAndQtdIngredientes.push(parseInt($("#qtd"+parseInt($(this).val())).val()));
                            }
                        });

                        var data;
                        var url;
                        if(idAndQtdIngredientes == ''){
                            data = {idBurger: idBurger};
                            url = "calculaSomenteBurger"
                        }else{
                            data = {idBurger: idBurger, ingredientes: idAndQtdIngredientes};
                            url = "calcular";
                        }

                        $.ajax(
                            {
                                type: "POST",
                                url: url,
                                data: data,
                                success: function( response )
                                {
                                    var div = document.getElementById('msgResponse');
                                    div.innerHTML = div.innerHTML + response;
                                }
                            });

                        return false;
                    });
                });

                function verificaNumero(e) {
                    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
                        alert("Digite apenas números de 0 a 9");
                        return false;
                    }
                }
            </script>

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


