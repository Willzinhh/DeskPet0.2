<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Serviço</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="../../static/css/geral.css"/>" />
</head>
<body>
<div class="login-container">
    <div class="login-header">
        <h1>Cadastro de Serviço</h1>
        <p>Adicione um novo serviço ao sistema</p>
    </div>

    <form action="/Servico/Cadastrar" method="POST">
        <div class="form-group">
            <label for="nomeServico">Nome do Serviço</label>
            <input type="text" id="nomeServico" name="nomeServico" placeholder="Ex: Banho e Tosa" required>
        </div>

        <div class="form-group">
            <label for="descricao">Descrição</label>
            <input type="text" id="descricao" name="descricao" placeholder="Descrição detalhada do serviço" required>
        </div>

        <div class="form-group">
            <label for="preco">Preço (R$)</label>
            <input type="number" step="0.01" id="preco" name="preco" placeholder="Ex: 49.90" required>
        </div>

        <div class="form-group">
            <label for="tempo">Duração:</label>
            <select name="tempo">
                <option value="15min">15 minutos</option>
                <option value="30min">30 minutos</option>
                <option value="45min">45 minutos</option>
                <option value="1h">1 hora</option>
                <option value="1h 30min">1 hora e 30 minutos</option>
                <option value="2h">2 horas</option>
                <option value="2h 30min">2 horas e 30 minutos</option>
                <option value="3h">3 horas</option>
                <option value="4h">4 horas</option>
            </select>
        </div>

        <button type="submit" class="cadastro-button">Cadastrar Serviço</button>
    </form>
</div>
</body>
</html>
