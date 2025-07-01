<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Novo Agendamento</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='../../static/css/geral.css' />" />
</head>
<body>

<div class="cadastro-container">
    <div class="login-header">
        <h1>Novo Agendamento</h1>
        <p>Preencha os dados para agendar um serviço</p>
    </div>

    <form action="/Agendamento/Adicionar" method="post">
        <<div class="form-group">
        <label for="data">Data:</label>
        <input type="date" id="data" name="data" required />
    </div>

        <div class="form-group">
            <label for="horario">Horário:</label>
            <input type="time" id="horario" name="horario" required />
        </div>

        <div class="form-group">
            <label for="pet_id">Pet:</label>
            <select id="pet_id" name="pet_id" required>
                <c:forEach var="pet" items="${pets}">
                    <option value="${pet.id}">${pet.nomepet} - ${pet.especie}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="servico_id">Serviço:</label>
            <select id="servico_id" name="servico_id" required>
                <c:forEach var="servico" items="${servicos}">
                    <option value="${servico.id}">${servico.nome} - R$ ${servico.valor}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="funcionario_id">Funcionário:</label>
            <select id="funcionario_id" name="funcionario_id" required>
                <c:forEach var="funcionario" items="${funcionarios}">
                    <option value="${funcionario.id}">${funcionario.nome} - ${funcionario.cargo}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="observacao">Observação:</label>
            <textarea id="observacao" name="observacao" placeholder="Observações adicionais..."></textarea>
        </div>

        <button type="submit" class="cadastro-button">Agendar</button>
        <div class="register-link">
            <a href="/Agendamento">Cancelar</a>
        </div>
    </form>
</div>

</body>
</html>
