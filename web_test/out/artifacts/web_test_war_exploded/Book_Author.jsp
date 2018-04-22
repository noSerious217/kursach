<%@ page import="java.util.LinkedList" %>
<%@ page import="Entity.Book_Author" %>
<%@ page import="Controller.Book_AuthorController" %>
<%@ page import="java.util.Collections" %>
<%@ page import="Entity.Author" %>
<%@ page import="Controller.AuthorController" %>
<%@ page import="Entity.Book" %>
<%@ page import="Controller.BookController" %>
<%@ page import="Core.ConnectionManager" %><%--
  Created by IntelliJ IDEA.
  User: gidro
  Date: 19.04.18
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="Book_Author.css" type="text/css">
    <title>Книги и авторы</title>
</head>
<body>
<% try
{
    Class.forName("org.postgresql.Driver");
    ConnectionManager.init();
%>
<div class="head">
    <table>
        <tr align="center">
            <td width="18%"><a class="authors" href="Authors.jsp">Авторы</a></td>
            <td width="18%"><a class="book" href="Book.jsp">Книги</a></td>
            <td><a class="ba" href="Book_Author.jsp">Книги и авторы</a></td>
            <td width="18%"><a class="publisher" href="Publisher.jsp">Издатели</a></td>
            <td width="18%"><a class="edition" href="Edition.jsp">Издание</a></td>
        </tr>
    </table>
</div>
<h1>Книги и авторы</h1>
<table>
    <tr>
        <td>
            <form action="Book_Author.jsp" type="get">
                <h2>Поиск</h2>
                <input type="number" min="1" placeholder="Поиск..." name="id"><br>
                <div class="checkbox">
                    <input type="checkbox" name="author"> Поиск по автору
                </div>
                <input type="hidden" name="search" value="true">
                <input type="submit" value="Искать">
            </form>
        </td>
        <td>
            <form action="Book_Author.jsp">
                <h2>Добавление</h2>
                <select name="author">
                    <%
                        LinkedList<Author> authors = AuthorController.select("");
                        for (Author author : authors) {
                        out.println("<option value='"+author.getId()+"'>"+author.getName()+"</option>");
                    }%>
                </select><br>
                <select name="book">
                    <%
                        LinkedList<Book> books = BookController.select("");
                        for (Book book : books) {
                            out.println("<option value='"+book.getId()+"'>"+book.getName()+"</option>");
                        }%>
                </select><br>
                <input type="hidden" name="add" value="true">
                <input type="submit" value="Добавить">
            </form>
        </td>
        <td>
            <form action="Book_Author.jsp">
                <h2>Удаление</h2>
                <select name="author">
                    <%
                        for (Author author : authors) {
                            out.println("<option value='"+author.getId()+"'>"+author.getName()+"</option>");
                        }%>
                </select><br>
                <select name="book">
                    <%
                        for (Book book : books) {
                            out.println("<option value='"+book.getId()+"'>"+book.getName()+"</option>");
                        }%>
                </select><br>
                <input type="hidden" name="delete" value="true">
                <input type="submit" value="Удалить">
            </form>
        </td>
    </tr>
</table>
<%
        LinkedList<Book_Author> list;
        if (request.getParameter("add")!=null)
        {
            Book_AuthorController.add(Integer.parseInt(request.getParameter("author")),Integer.parseInt(request.getParameter("book")));
        }
        if (request.getParameter("delete")!=null)
        {
            Book_AuthorController.delete(Integer.parseInt(request.getParameter("author")),Integer.parseInt(request.getParameter("book")));
        }
        if (request.getParameter("search")!=null && request.getParameter("id")!=null && request.getParameter("id").length()>0)
        {
            if (request.getParameter("author")!=null && request.getParameter("author").equals("on"))
                list = Book_AuthorController.selectByAuthor(Integer.parseInt(request.getParameter("id")));
            else  list = Book_AuthorController.selectByBook(Integer.parseInt(request.getParameter("id")));
        }
        else
        list = Book_AuthorController.select();
        %>
<table align="center" class="table">
    <tr class="tr"><th class="th">ID автора</th><th class="th">Имя автора</th><th class="th">ID книги</th><th class="th">Название книги</th></tr>
    <%
        Collections.sort(list);
        for (int i=0; i<list.size();i++)
        {
            {
                out.print("<tr class='tr'><td class='td'>");
                out.print(list.get(i).getA_id());
                out.print("</td><td class='td'>");
                Author author = AuthorController.select(list.get(i).getA_id());
                out.print(author.getName());
                out.print("</td><td class='td'>");
                out.print(list.get(i).getB_id());
                out.print("</td><td class='td'>");
                Book book = BookController.select(list.get(i).getB_id());
                out.print(book.getName());
                out.print("</td><td class='td'>");
                out.print("</td></tr>");
            }
        }
    %>
<%
ConnectionManager.Close();
    }
    catch (Exception e)
    {
        out.println(e.getMessage());
    }
%>
</body>
</html>
