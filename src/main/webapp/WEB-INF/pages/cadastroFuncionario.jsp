<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Funcionário</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='../../static/css/geral.css'/>" />
</head>
<body>
<div class="login-container">
    <div class="login-header">
        <h1>Cadastro de Funcionário</h1>
        <p>Adicione um novo funcionário ao sistema</p>
    </div>

    <form action="/Funcionario/Cadastrar" method="POST">
        <input type="hidden" name="idUsuario" value="${idUsuario}" />

        <div class="form-group">
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" placeholder="Ex: João da Silva" required>
        </div>

        <div class="form-group">
            <label for="cpf">CPF</label>
            <input type="text" id="cpf" name="cpf" placeholder="Ex: 000.000.000-00" required>
        </div>

        <div class="form-group">
            <label for="telefone">Telefone</label>
            <input type="text" id="telefone" name="telefone" placeholder="Ex: (55) 99999-9999">
        </div>

        <div class="form-group">
            <label for="cargo">Cargo</label>
            <input type="text" id="cargo" name="cargo" placeholder="Ex: Tosador, Atendente..." required>
        </div>

        <div class="form-group">
            <label for="salario">Salário (R$)</label>
            <input type="number" step="0.01" id="salario" name="salario" placeholder="Ex: 2000.00" required>
        </div>

        <div class="form-group">
            <label for="ativo">Está ativo?</label>
            <select name="ativo" id="ativo" required>
                <option value="true" selected>Sim</option>
                <option value="false">Não</option>
            </select>
        </div>

        <button type="submit" class="cadastro-button">Cadastrar Funcionário</button>
    </form>
</div>
</body>
</html>
