<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Funcionários</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="<c:url value='../../static/css/tableClientes.css' />">
</head>
<body>
<div class="dashboard-container">
    <div class="main-content">
        <h1>Funcionários</h1>
        <div style="display: flex; justify-content: space-between; flex-direction: row; align-items: center">
            <form action="/Funcionario/Cadastro" method="post">
                <button class="btn btn-primary" type="submit">Novo Funcionário</button>
            </form>
            <a href="/dashboard" class="btn-primary">Voltar</a>
        </div>

        <div class="table-container">
            <table id="funcionariosTable" class="display">
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Cargo</th>
                    <th>Salário</th>
                    <th>Ativo</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${funcionarios}" var="f">
                    <tr>
                        <td>${f.nome}</td>
                        <td>${f.cpf}</td>
                        <td>${f.cargo}</td>
                        <td>R$ ${f.salario}</td>
                        <td><c:choose>
                            <c:when test="${f.ativo}">Sim</c:when>
                            <c:otherwise>Não</c:otherwise>
                        </c:choose></td>
                        <td>
                            <form action="/Funcionario/Edicao" method="post" style="display: inline;">
                                <input type="hidden" name="opcao" value="${f.id}">
                                <button type="submit" class="btn-danger btn-sm">Editar</button>
                            </form>
                            <form action="/Funcionario/Excluir" method="post" style="display: inline;">
                                <input type="hidden" name="opcao" value="${f.id}">
                                <button type="submit" class="btn-danger btn-sm" onclick="return confirm('Excluir funcionário?')">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#funcionariosTable').DataTable({
            language: {
                url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/pt-BR.json'
            }
        });
    });
</script>
</body>
</html>
