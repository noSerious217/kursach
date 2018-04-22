<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="Core.ConnectionManager" %>
<%@ page import="Entity.*" %>
<%@ page import="Controller.*" %><%--
  Created by IntelliJ IDEA.
  User: gidro
  Date: 19.04.18
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="Edition.css" type="text/css">
    <title>Издание</title>
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
<h1>Издания</h1>
<table>
    <tr>
        <td>
            <form action="Edition.jsp" type="get">
                <h2>Поиск</h2>
                <input type="number" required placeholder="Поиск..." min="1" name="id"><br>
                <select required name="book">
                    <%
                        LinkedList<Book> books = BookController.select("");
                        Collections.sort(books);
                        for (Book book : books) {
                            out.println("<option value='"+book.getId()+"'>"+book.getName()+"</option>");
                        }%>
                </select><br>
                <input type="hidden" name="search" value="true">
                <input type="submit" value="Искать">
            </form>
            <form action="Edition.jsp" type="get">
                <input type="submit" value="Сброс фильтра">
            </form>
        </td>
        <td>
            <form action="Edition.jsp" type="get">
                <h2>Поиск по книге</h2>
                <select required name="book">
                    <%
                        for (Book book : books) {
                            out.println("<option value='"+book.getId()+"'>"+book.getName()+"</option>");
                        }%>
                </select><br>
                <input type="hidden" name="searchbybook" value="true">
                <input type="submit" value="Искать">
            </form>
        </td>
        <td>
            <form action="Edition.jsp">
                <h2>Добавление</h2>
                <input type="number" placeholder="ID" min="1" name="id"><br>
                <select required name="book">
                    <%
                        for (Book book : books) {
                            out.println("<option value='"+book.getId()+"'>"+book.getName()+"</option>");
                        }%>
                </select><br>
                <select required name="publisher">
                    <%
                        LinkedList<Publisher> publishers = PublisherController.select("");
                        Collections.sort(publishers);
                        for (Publisher publisher : publishers) {
                            out.println("<option value='"+publisher.getId()+"'>"+publisher.getName()+", "+publisher.getCity()+"</option>");
                        }%>
                </select><br>
                <input type="number" required placeholder="Количество страниц" min="1" name="pages"><br>
                <input type="number" required placeholder="Количество копий" min="0" name="copies"><br>
                <input type="text" required placeholder="ISBN" maxlength="13" minlength="13" name="ISBN"><br>
                <input type="number" required placeholder="Год выпуска" min="1900" max="2018" name="year"><br>
                <input type="hidden" name="add" value="true">
                <input type="submit" value="Добавить">
            </form>
        </td>
        <td>
            <form action="Edition.jsp">
                <h2>Обновление</h2>
                <input type="number" required placeholder="ID" min="1" max = "<%= EditionController.getMaxID()%>" name="id"><br>
                <select required placeholder="ID книги" name="oldbook">
                    <%
                        for (Book book : books) {
                            out.println("<option value='"+book.getId()+"'>"+book.getName()+"</option>");
                        }%>
                </select><br>
                <select required name="book">
                    <%
                        for (Book book : books) {
                            out.println("<option value='"+book.getId()+"'>"+book.getName()+"</option>");
                        }%>
                </select><br>
                <div class="checkbox">
                    <input type="checkbox" name="bookupd"> Изменить книгу
                </div>
                <select name="publisher">
                    <%
                        for (Publisher publisher : publishers) {
                            out.println("<option value='"+publisher.getId()+"'>"+publisher.getName()+", "+publisher.getCity()+"</option>");
                        }%>
                </select><br>
                <div class="checkbox">
                    <input type="checkbox" name="pubupd"> Изменить издателя
                </div>
                <input type="number" placeholder="Количество страниц" min="1" name="pages"><br>
                <input type="number" placeholder="Количество копий" min="0" name="copies"><br>
                <input type="text" placeholder="ISBN" maxlength="13" minlength="13" name="ISBN"><br>
                <input type="number" placeholder="Год выпуска" min="1900" max="2018" name="year"><br>
                <input type="hidden" name="update" value="true">
                <input type="submit" value="Обновить">
            </form>
        </td>
    </tr>
</table>
<%
    LinkedList<Edition> list;
    if (request.getParameter("add")!=null)
    {
        Edition edition = new Edition();
        if (request.getParameter("id")!=null&&request.getParameter("id").length()>0) edition.setId(Integer.parseInt(request.getParameter("id")));
        edition.setB_id(Integer.parseInt(request.getParameter("book")));
        edition.setP_id(Integer.parseInt(request.getParameter("publisher")));
        edition.setPages(Integer.parseInt(request.getParameter("pages")));
        edition.setCopies(Integer.parseInt(request.getParameter("copies")));
        edition.setISBN(request.getParameter("ISBN"));
        edition.setYear(Integer.parseInt(request.getParameter("year")));
        EditionController.add(edition);
    }
    if (request.getParameter("update")!=null)
    {
        Edition edition = EditionController.select(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("oldbook")));
        if (request.getParameter("pubupd")!=null && request.getParameter("pubupd").equals("on"))
            edition.setP_id(Integer.parseInt(request.getParameter("publisher")));
        if (request.getParameter("pages")!=null && request.getParameter("pages").length()>0) edition.setPages(Integer.parseInt(request.getParameter("pages")));
        if (request.getParameter("copies")!=null && request.getParameter("copies").length()>0) edition.setCopies(Integer.parseInt(request.getParameter("copies")));
        if (request.getParameter("ISBN")!=null && request.getParameter("ISBN").length()>0) edition.setISBN(request.getParameter("ISBN"));
        if (request.getParameter("year")!=null && request.getParameter("year").length()>0) edition.setPages(Integer.parseInt(request.getParameter("year")));
        if (request.getParameter("bookupd")!=null && request.getParameter("bookupd").equals("on"))
            EditionController.update(edition,Integer.parseInt(request.getParameter("book")));
        else EditionController.update(edition,edition.getB_id());
    }
    if (request.getParameter("search")!=null)
    {
        list = new LinkedList<Edition>();
        list.add(EditionController.select(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("book"))));
    }
    else if (request.getParameter("searchbybook")!=null)
    {
        list = EditionController.selectByBook(Integer.parseInt(request.getParameter("book")));
    }
    else list = EditionController.select();
%>
<table align="center" class="table">
    <tr class="tr"><th class="th">ID</th><th class="th">Название книги</th><th class="th">Название издательства</th><th class="th">Количество страниц</th><th class="th">Количество копий</th><th class="th">ISBN</th><th class="th">Год выхода</th></tr>
        <%
        Collections.sort(list);
        for (int i=0; i<list.size();i++)
        {
            {
                out.print("<tr class='tr'><td class='td'>");
                out.print(list.get(i).getId());
                out.print("</td><td class='td'>");
                Book book = BookController.select(list.get(i).getB_id());
                out.print(book.getName());
                out.print("</td><td class='td'>");
                Publisher publisher  = PublisherController.select(list.get(i).getP_id());
                out.print(publisher.getName());
                out.print("</td><td class='td'>");
                out.print(list.get(i).getPages());
                out.print("</td><td class='td'>");
                out.print(list.get(i).getCopies());
                out.print("</td><td class='td'>");
                out.print(list.get(i).getISBN());
                out.print("</td><td class='td'>");
                out.print(list.get(i).getYear());
                out.print("</td><td class='td'>");
                out.print("</td></tr>");
            }
        }
    %>
</table>

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
