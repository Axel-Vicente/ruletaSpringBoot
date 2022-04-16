<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Index</title>
</head>
<body>
    <div class="container">
    <br><br>
        <form:form action="/play" method="get" modelAttribute="ruleta">
            <form:select class="form-control required" path="betTypes" onchange="setItem(value)">
                <option value="">Seleccione un tipo de apuesta</option>
                <c:forEach var="i" begin="${0}" end="${longType - 1}" step="${1}">
                    <option value="${i}">${betType[i]}</option>
                </c:forEach>
            </form:select>
            <div id="optionSelected"></div>
            <br>
            <c:if test="${parar}">
                <input class="btn btn-dark disabled" type="submit" value="Tirar" />
                <a class="btn btn-danger" href="/reset" >Restart</a>
            </c:if>
            <c:if test="${!parar}">
                <input class="btn btn-success" type="submit" value="Tirar" />
                <a class="btn btn-danger" href="/reset" >Restart</a>
            </c:if>
            <div>Dinero restante: ${dinero}</div>
        </form:form>

        <c:if test="${isWinner}">
            <div class="alert alert-success" role="alert">
                <h1>Ganaste enhorabuena!!! has ganado ${betMoney} € el numero ganador era ${winnerNumber}</h1>
            </div>
        </c:if>
        <c:if test="${isLoser}">
            <div class="alert alert-danger" role="alert">
                <h1>Perdiste!!! has perdido ${betMoney} € el numero ganador era ${winnerNumber}</h1>
            </div>
        </c:if>
    </div>
    </body>
    <script>
        function setItem(value) {
            if (value == 0) {
                document.getElementById("optionSelected").innerHTML = "<br/> <select class='form-control required' name='betOption' id='betOption'><option value='1-18'>1-18</option><option value='19-36'>19-36</option></select><input class='form-control' type='number' name='money' placeholder='Elija la cantidad a apostar' min='1' max='${dinero}' />";
            }else if (value == 1) {
                document.getElementById("optionSelected").innerHTML = "<br/><input class='form-control' type='number' name='betOption' placeholder='Elija el numero para apostar' min='0' max='36' /><input class='form-control' type='number' name='money' placeholder='Elija la cantidad a apostar' min='1' max='${dinero}' />";
            }else if (value == 2) {
                document.getElementById("optionSelected").innerHTML = "<br/> <select class='form-control required' name='betOption' id='betOption'><option value='red'>Rojo</option><option value='black'>Negro</option><option value='green'>Verde</option></select><input class='form-control' type='number' name='money' placeholder='Elija la cantidad a apostar' min='1' max='${dinero}' />";
            }else if (value == 3) {
                document.getElementById("optionSelected").innerHTML = "<br/> <select class='form-control required' name='betOption' id='betOption'><option value='even'>Par</option><option value='odd'>Impar</option></select><input class='form-control' type='number' name='money' placeholder='Elija la cantidad a apostar' min='1' max='${dinero}' />";
            }
        }
    </script>
</html>