<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Serviço</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="../../static/css/geral.css"/>" />
</head>
<body>
<div class="cadastro-container">
    <div class="login-header">
        <h1>Editar Serviço</h1>
        <p>Atualize as informações do serviço abaixo</p>
    </div>

    <form action="/Servico/Editar" method="post">

        <!-- Campo oculto com ID do serviço -->
        <input type="hidden" name="action" value="EDITAR_SERVICO" />
        <input type="hidden" name="id" value="${servico.id}" />

        <div class="form-group">
            <label for="nome">Nome do Serviço:</label>
            <input type="text" id="nome" name="nome" value="${servico.nome}" required />
        </div>

        <div class="form-group">
            <label for="descricao">Descrição:</label>
            <input type="text" id="descricao" name="descricao" value="${servico.descricao}" />
        </div>

        <div class="form-group">
            <label for="valor">Valor (R$):</label>
            <input type="number" id="valor" name="valor" step="0.01" value="${servico.valor}" required />
        </div>

        <div class="form-group">
            <label for="tempo">Duração:</label>
            <select id="tempo" name="tempo" required>
                <option value="00:15:00" <c:if test="${servico.tempo == '00:15:00'}">selected</c:if>>15 minutos</option>
                <option value="00:30:00" <c:if test="${servico.tempo == '00:30:00'}">selected</c:if>>30 minutos</option>
                <option value="00:45:00" <c:if test="${servico.tempo == '00:45:00'}">selected</c:if>>45 minutos</option>
                <option value="01:00:00" <c:if test="${servico.tempo == '01:00:00'}">selected</c:if>>1 hora</option>
                <option value="01:30:00" <c:if test="${servico.tempo == '01:30:00'}">selected</c:if>>1 hora e 30 minutos</option>
                <option value="02:00:00" <c:if test="${servico.tempo == '02:00:00'}">selected</c:if>>2 horas</option>
                <option value="02:30:00" <c:if test="${servico.tempo == '02:30:00'}">selected</c:if>>2 horas e 30 minutos</option>
                <option value="03:00:00" <c:if test="${servico.tempo == '03:00:00'}">selected</c:if>>3 horas</option>
                <option value="04:00:00" <c:if test="${servico.tempo == '04:00:00'}">selected</c:if>>4 horas</option>
            </select>
        </div>


        <input type="hidden" name="cliente_usuario_id" value="${servico.cliente_usuario_id}" />

        <button type="submit" class="cadastro-button">Salvar Alterações</button>
        <div class="register-link">
            <a href="/Servico">Cancelar</a>
        </div>
    </form>
</div>
</body>
</html>
