<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Funcionário</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='../../static/css/geral.css'/>" />
</head>
<body>
<div class="cadastro-container">
    <div class="login-header">
        <h1>Editar Funcionário</h1>
        <p>Atualize as informações do funcionário abaixo</p>
    </div>

    <form action="/Funcionario/Editar" method="post">

        <!-- Campo oculto com ID do funcionário -->
        <input type="hidden" name="opcao" value="${funcionario.id}" />

        <div class="form-group">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="${funcionario.nome}" required />
        </div>

        <div class="form-group">
            <label for="cpf">CPF:</label>
            <input type="text" id="cpf" name="cpf" value="${funcionario.cpf}" required />
        </div>

        <div class="form-group">
            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" name="telefone" value="${funcionario.telefone}" />
        </div>

        <div class="form-group">
            <label for="cargo">Cargo:</label>
            <input type="text" id="cargo" name="cargo" value="${funcionario.cargo}" required />
        </div>

        <div class="form-group">
            <label for="salario">Salário:</label>
            <input type="number" step="0.01" id="salario" name="salario" value="${funcionario.salario}" required />
        </div>

        <div class="form-group">
            <label for="ativo">Ativo:</label>
            <select id="ativo" name="ativo" required>
                <option value="true" <c:if test="${funcionario.ativo}">selected</c:if>>Sim</option>
                <option value="false" <c:if test="${!funcionario.ativo}">selected</c:if>>Não</option>
            </select>
        </div>

        <button type="submit" class="cadastro-button">Salvar Alterações</button>
        <div class="register-link">
            <a href="/Funcionario">Cancelar</a>
        </div>
    </form>
</div>
</body>
</html>
