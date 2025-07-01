
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Editar Agendamento</title>
    <link rel="stylesheet" href="<c:url value='../../static/css/geral.css'/>">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f8f9fa;
            padding: 0;
            margin: 0;
        }

        .container {
            max-width: 700px;
            margin: 40px auto;
            padding: 30px;
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #343a40;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: bold;
        }

        input, select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 6px;
            width: 100%;
        }

        button {
            background-color: #0d6efd;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0b5ed7;
        }

        a {
            display: block;
            margin-top: 20px;
            text-align: center;
            color: #0d6efd;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Editar Agendamento</h1>

    <form action="/Agendamento/Editar" method="post">
        <input type="hidden" name="opcao" value="${agendamento.id}"/>

        <!-- PET -->
        <label for="pet">Pet:</label>
        <select name="petId" id="pet" required>
            <c:forEach var="pet" items="${pets}">
                <option value="${pet.id}" ${pet.id == agendamento.pet_id ? 'selected' : ''}>${pet.nomepet}</option>
            </c:forEach>
        </select>

        <!-- SERVIÇO -->
        <label for="servico">Serviço:</label>
        <select name="servicoId" id="servico" required>
            <c:forEach var="servico" items="${servicos}">
                <option value="${servico.id}" ${servico.id == agendamento.servico_id ? 'selected' : ''}>${servico.nome}</option>
            </c:forEach>
        </select>

        <!-- FUNCIONÁRIO -->
        <label for="funcionario">Funcionário:</label>
        <select name="funcionarioId" id="funcionario" required>
            <c:forEach var="func" items="${funcionarios}">
                <option value="${func.id}" ${func.id == agendamento.funcionario_id ? 'selected' : ''}>${func.nome}</option>
            </c:forEach>
        </select>

        <!-- DATA -->
        <label for="data">Data:</label>
        <input type="date" id="data" name="data"
               value="<fmt:formatDate value='${agendamento.data}' pattern='yyyy-MM-dd' />" required/>

        <!-- HORA -->
        <label for="hora">Hora:</label>
        <input type="time" id="hora" name="hora"
               value="<fmt:formatDate value='${agendamento.horario}' pattern='HH:mm' />" required/>
        <!-- STATUS -->
        <label for="status">Status:</label>
        <select name="status" id="status" required>
            <option value="Agendado" ${agendamento.status == 'Agendado' ? 'selected' : ''}>Agendado</option>
            <option value="Concluído" ${agendamento.status == 'Concluído' ? 'selected' : ''}>Concluído</option>
            <option value="Cancelado" ${agendamento.status == 'Cancelado' ? 'selected' : ''}>Cancelado</option>
        </select>

        <!-- STATUS PAGAMENTO -->
        <label for="statusPagamento">Status Pagamento:</label>
        <select name="status_pagamento" id="statusPagamento" required>
            <option value="PENDENTE" ${agendamento.status_pagamento == 'PENDENTE' ? 'selected' : ''}>Pendente</option>
            <option value="PAGO" ${agendamento.status_pagamento == 'PAGO' ? 'selected' : ''}>Pago</option>
            <option value="CANCELADO" ${agendamento.status_pagamento == 'CANCELADO' ? 'selected' : ''}>Cancelado</option>
        </select>

        <button type="submit">Salvar</button>
    </form>
    <!-- BOTÃO EXCLUIR -->
    <form action="/Agendamento/Excluir" method="post" onsubmit="return confirm('Tem certeza que deseja excluir este agendamento?');">
        <input type="hidden" name="opcao" value="${agendamento.id}">
        <button type="submit" style="background-color: #dc3545;">Excluir</button>
    </form>

    <a href="/Agendamento">Cancelar</a>
</div>
</body>
</html>
