<%@ page import="java.util.List" %>
<%@ page import="Entity.Author" %>
<%@ page import="Controller.AuthorController" %>
<%@ page import="Core.ConnectionManager" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.Collections" %>
<%@ page import="Entity.Book_Author" %>
<%--
  Created by IntelliJ IDEA.
  User: gidro
  Date: 12.04.18
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="Authors.css" type="text/css">
    <title>Авторы</title>
</head>
<body>
<% try
{%>
<div class="head">
    <table align="center">
        <tr align="center">
            <td width="18%"><a class="authors" href="Authors.jsp">Авторы</a></td>
            <td width="18%"><a class="book" href="Book.jsp">Книги</a></td>
            <td><a class="ba" href="Book_Author.jsp">Книги и авторы</a></td>
            <td width="18%"><a class="publisher" href="Publisher.jsp">Издатели</a></td>
            <td width="18%"><a class="edition" href="Edition.jsp">Издание</a></td>
        </tr>
    </table>
</div>
<h1>Авторы</h1>
<table>
    <tr>
        <td>
<form action="Authors.jsp" type="get">
    <h2>Поиск</h2>
    <input type="text" placeholder="Поиск..." name="mask"><br>
    <div class="checkbox">
    <input type="checkbox" name="text"> Поиск по ID
    </div>
    <input type="hidden" name="search" value="true">
    <input type="submit" value="Искать">
</form>
        </td>
        <td>
            <form action="Authors.jsp">
                <h2>Добавление</h2>
                <input type="number" placeholder="ID" name="id"><br>
                <input type="text" required placeholder="Имя" name="name"><br>
                <input type="hidden" name="add" value="true">
                <input type="submit" value="Добавить">
            </form>
        </td>
        <td>
            <form action="Authors.jsp">
                <h2>Обновление</h2>
                <input type="number" required min="1" max = "<%= AuthorController.getMaxID()%>" placeholder="ID" name="id"><br>
                <input type="text" required placeholder="Новое имя" name="name"><br>
                <input type="hidden" name="update" value="true">
                <input type="submit" value="Обновить">
            </form>
        </td>
        <td>
            <form action="Authors.jsp">
                <h2>Удаление</h2>
                <input type="number" required placeholder="ID" name="id"><br><br>
                <input type="hidden" name="delete" value="true">
                <input type="submit" value="Удалить">
            </form>
        </td>
    </tr>
</table>
<%
    Class.forName("org.postgresql.Driver");
    ConnectionManager.init();%>
<table align="center" class="table">
<tr class="tr"><th class="th">Id</th><th class="th">Имя</th></tr>
    <% List<Author> list;
    if (request.getParameter("add")!=null)
    {
        Author author = new Author();
        author.setName(request.getParameter("name"));
        if (request.getParameter("id")!=null && request.getParameter("id").length()>0) author.setId(Integer.parseInt(request.getParameter("id")));
        AuthorController.add(author);
    }
    if (request.getParameter("update")!=null)
    {
        Author author = new Author();
        author.setName(request.getParameter("name"));
        author.setId(Integer.parseInt(request.getParameter("id")));
        AuthorController.update(author);
    }
    if (request.getParameter("delete")!=null)
    {
        AuthorController.delete(Integer.parseInt(request.getParameter("id")));
    }
    if (request.getParameter("search")!=null)
    {
        if (request.getParameter("text")!=null && request.getParameter("text").equals("on"))
        {
            list = new LinkedList<Author>();
            list.add(AuthorController.select(Integer.parseInt(request.getParameter("mask"))));
        }
        else
        {
            list = AuthorController.select(request.getParameter("mask"));
        }
    }
    else list = AuthorController.select("");
    Collections.sort(list);
    for (int i=0; i<list.size();i++)
    {
        {
            out.print("<tr class='tr'><td class='td'>");
            out.print(list.get(i).getId());
            out.print("</td><td class='td'>");
            out.print(list.get(i).getName());
            out.print("</td></tr>");
        }
    }%>
</table>
<% ConnectionManager.Close();%>
<% }
catch (Exception e)
{
    out.println(e.toString());
}%>
</body>
</html>
