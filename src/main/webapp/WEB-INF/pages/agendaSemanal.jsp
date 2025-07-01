<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Agenda Diária</title>
    <link rel="stylesheet" href="<c:url value='../../static/css/geral.css'/>">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1000px;
            margin: 40px auto;
            padding: 30px;
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #343a40;
            margin-bottom: 30px;
        }

        form {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 25px;
            gap: 10px;
        }

        label {
            font-weight: bold;
            color: #495057;
        }

        input[type="date"] {
            padding: 8px;
            border: 1px solid #ced4da;
            border-radius: 6px;
            background-color: #fff;
        }

        button, .btn {
            background-color: #198754;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.2s ease;
            text-decoration: none;
            font-size: 14px;
        }

        button:hover, .btn:hover {
            background-color: #157347;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        thead {
            background-color: #343a40;
            color: white;
        }

        th, td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #dee2e6;
        }

        tbody tr:hover {
            background-color: #f1f1f1;
        }

        .status-verde { color: green; }
        .status-vermelho { color: red; }
        .status-laranja { color: orange; }

        .acoes form {
            display: inline;
        }

        a.btn-primary {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 18px;
            background-color: #0d6efd;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            transition: background-color 0.2s ease;
        }

        a.btn-primary:hover {
            background-color: #0b5ed7;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Agenda do Dia</h1>

    <form action="/Agendamento" method="get">
        <label for="data">Data:</label>
        <input type="date" id="data" name="data" value="${dataSelecionada}" required>
        <button type="submit">Ver Agendamentos</button>
        <a class="btn" href="/Agendamento/AdicionarAgendamento">Agendar</a>
    </form>

    <c:if test="${not empty agendamentos}">
        <table>
            <thead>
            <tr>
                <th>Horário</th>
                <th>Pet</th>
                <th>Serviço</th>
                <th>Funcionário</th>
                <th>Status</th>
                <th>Status Pagamento</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ag" items="${agendamentos}">
                <tr>
                    <td><fmt:formatDate value="${ag.horario}" pattern="HH:mm"/></td>
                    <td>${ag.petNome}</td>
                    <td>${ag.servicoNome}</td>
                    <td>${ag.funcionarioNome}</td>
                    <td>${ag.status}</td>

                    <td>
                        <c:choose>
                            <c:when test="${ag.status_pagamento == 'PAGO'}">
                                <span class="status-verde">PAGO</span>
                            </c:when>
                            <c:when test="${ag.status_pagamento == 'CANCELADO'}">
                                <span class="status-vermelho">CANCELADO</span>
                            </c:when>
                            <c:otherwise>
                                <span class="status-laranja">PENDENTE</span>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <!-- AÇÕES -->
                    <td class="acoes">
                        <form action="/Agendamento/AtualizarStatus" method="post">
                            <input type="hidden" name="opcao" value="${ag.id}">
                            <input type="hidden" name="acao" value="pagar">
                            <button type="submit">Pagar</button>
                        </form>

                        <form action="/Agendamento/AtualizarStatus" method="post">
                            <input type="hidden" name="opcao" value="${ag.id}">
                            <input type="hidden" name="acao" value="cancelar">
                            <button type="submit">Cancelar</button>
                        </form>

                        <form action="/Agendamento/EdicaoAgendamento" method="post">
                            <input type="hidden" name="opcao" value="${ag.id}">
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty agendamentos}">
        <p>Nenhum agendamento encontrado para esta data.</p>
    </c:if>

    <a href="/dashboard" class="btn">Voltar</a>
</div>
</body>
</html>
