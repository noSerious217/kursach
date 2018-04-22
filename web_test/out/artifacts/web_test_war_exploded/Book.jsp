<%@ page import="Core.ConnectionManager" %>
<%@ page import="Entity.Book" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="Controller.BookController" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: gidro
  Date: 19.04.18
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="Book.css" type="text/css">
    <title>Книги</title>
</head>
<body>
    <% try
{
    Class.forName("org.postgresql.Driver");
    ConnectionManager.init();%>
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
    <h1>Книги</h1>
    <table>
        <tr>
            <td>
                <form action="Book.jsp" type="get">
                    <h2>Поиск</h2>
                    <input type="text" maxlength="80" placeholder="Поиск..." name="mask"><br>
                    <div class="checkbox">
                        <input type="checkbox" name="text"> Поиск по ID
                    </div>
                    <input type="hidden" name="search" value="true">
                    <input type="submit" value="Искать">
                </form>
            </td>
            <td>
                <form action="Book.jsp">
                    <h2>Добавление</h2>
                    <input type="number" placeholder="ID" name="id"><br>
                    <input type="text" maxlength="80" required placeholder="Имя" name="name"><br>
                    <input type="text" maxlength="20" required placeholder="Жанр" name="genre"><br>
                    <input type="number" required placeholder="Год выхода" name="year" min="1900" max="2018"><br>
                    <input type="hidden" name="add" value="true">
                    <input type="submit" value="Добавить">
                </form>
            </td>
            <td>
                <form action="Book.jsp">
                    <h2>Обновление</h2>
                    <input type="number" required min="1" max = "<%= BookController.getMaxID()%>" placeholder="ID" name="id"><br>
                    <input type="text" maxlength="80" placeholder="Новое имя" name="name"><br>
                    <input type="text" maxlength="20" placeholder="Новый жанр" name="genre"><br>
                    <input type="number" placeholder="Год выхода" name="year" min="1900" max="2018"><br>
                    <input type="hidden" name="update" value="true">
                    <input type="submit" value="Обновить">
                </form>
            </td>
            <td>
                <form action="Book.jsp">
                    <h2>Удаление</h2>
                    <input type="number" required placeholder="ID" name="id"><br><br>
                    <input type="hidden" name="delete" value="true">
                    <input type="submit" value="Удалить">
                </form>
            </td>
        </tr>
    </table>
    <table align="center" class="table">
        <tr class="tr"><th class="th">Id</th><th class="th">Название</th><th class="th">Жанр</th><th class="th">Год выхода</th></tr>
        <%
        LinkedList<Book> list;
        if (request.getParameter("add")!=null)
    {
        Book book = new Book();
        book.setName(request.getParameter("name"));
        book.setGenre(request.getParameter("genre"));
        book.setYear(Integer.parseInt(request.getParameter("year")));
        if (request.getParameter("id")!=null && request.getParameter("id").length()>0) book.setId(Integer.parseInt(request.getParameter("id")));
        BookController.add(book);
    }

    if (request.getParameter("update")!=null)
        {
            Book book = BookController.select(Integer.parseInt(request.getParameter("id")));
            if (request.getParameter("name")!=null && request.getParameter("name").length()>0) book.setName(request.getParameter("name"));
            if (request.getParameter("genre")!=null && request.getParameter("genre").length()>0) book.setGenre(request.getParameter("genre"));
            if (request.getParameter("year")!=null && request.getParameter("year").length()>0) book.setYear(Integer.parseInt(request.getParameter("year")));
            BookController.update(book);
        }

        if (request.getParameter("delete")!=null)
            {
                BookController.delete(Integer.parseInt(request.getParameter("id")));
            }
        if (request.getParameter("search")!=null)
    {
        if (request.getParameter("text")!=null && request.getParameter("text").equals("on"))
        {
            list = new LinkedList<Book>();
            list.add(BookController.select(Integer.parseInt(request.getParameter("mask"))));
        }
        else
        {
            list = BookController.select(request.getParameter("mask"));
        }
    }
    else list = BookController.select("");
            Collections.sort(list);
        for (int i=0; i<list.size();i++)
        {
            {
                out.print("<tr class='tr'><td class='td'>");
                out.print(list.get(i).getId());
                out.print("</td><td class='td'>");
                out.print(list.get(i).getName());
                out.print("</td><td class='td'>");
                out.print(list.get(i).getGenre());
                out.print("</td><td class='td'>");
                out.print(list.get(i).getYear());
                out.print("</td></tr>");
            }
        }%>
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