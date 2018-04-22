<%@ page import="java.util.List" %>
<%@ page import="Entity.Author" %>
<%@ page import="Controller.AuthorController" %>
<%@ page import="Core.ConnectionManager" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.Collections" %>
<%@ page import="Entity.Book_Author" %>
<%@ page import="Core.City" %>
<%@ page import="Entity.Publisher" %>
<%@ page import="Controller.PublisherController" %>
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
    <link rel="stylesheet" href="Publisher.css" type="text/css">
    <title>Издательства</title>
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
<h1>Издательства</h1>
<table>
    <tr>
        <td>
            <form action="Publisher.jsp" type="get">
                <h2>Поиск</h2>
                <input type="text" placeholder="Поиск..." name="mask"><br>
                <div class="checkbox">
                    <input type="checkbox" name="text"> Поиск по ID
                </div>
                <select name="city">
                    <% for (City.Code code : City.Code.values()) {
                       out.println("<option value='"+code.toString()+"'>"+City.GetName(code)+"</option>");
                    }%>
                </select>
                <div class="checkbox">
                    <input type="checkbox" name="cityon"> Ограничение по городу
                </div>
                <input type="hidden" name="search" value="true">
                <input type="submit" value="Искать">
            </form>
        </td>
        <td>
            <form action="Publisher.jsp">
                <h2>Добавление</h2>
                <input type="number" placeholder="ID" name="id"><br>
                <input type="text" required placeholder="Имя" name="name"><br>
                <select required name="city">
                    <% for (City.Code code : City.Code.values()) {
                        out.println("<option value='"+code.toString()+"'>"+City.GetName(code)+"</option>");
                    }%>
                </select><br>
                <input type="hidden" name="add" value="true">
                <input type="submit" value="Добавить">
            </form>
        </td>
        <td>
            <form action="Publisher.jsp">
                <h2>Обновление</h2>
                <input type="number" required min="1" max = "<%= PublisherController.getMaxID()%>" placeholder="ID" name="id"><br>
                <input type="text" placeholder="Новое имя" name="name"><br>
                <select name="city">
                    <% for (City.Code code : City.Code.values()) {
                        out.println("<option value='"+code.toString()+"'>"+City.GetName(code)+"</option>");
                    }%>
                </select>
                <div class="checkbox">
                    <input type="checkbox" name="cityupd"> Изменить город
                </div>
                <input type="hidden" name="update" value="true">
                <input type="submit" value="Обновить">
            </form>
        </td>
        <td>
            <form action="Publisher.jsp">
                <h2>Удаление</h2>
                <input type="number" required placeholder="ID" name="id"><br><br>
                <input type="hidden" name="delete" value="true">
                <input type="submit" value="Удалить">
            </form>
        </td>
    </tr>
</table>
<table align="center" class="table">
    <tr class="tr"><th class="th">Id</th><th class="th">Название</th><th class="th">Город</th></tr>
    <% List<Publisher> list;
        if (request.getParameter("add")!=null)
        {
            Publisher publisher = new Publisher();
            publisher.setName(request.getParameter("name"));
            if (request.getParameter("id")!=null && request.getParameter("id").length()>0) publisher.setId(Integer.parseInt(request.getParameter("id")));
            publisher.setCity(City.GetName(request.getParameter("city")));
            PublisherController.add(publisher);
        }
        if (request.getParameter("update")!=null)
        {
            Publisher publisher = PublisherController.select(Integer.parseInt(request.getParameter("id")));
            if (request.getParameter("name")!=null && request.getParameter("name").length()>0) publisher.setName(request.getParameter("name"));
            if (request.getParameter("cityupd")!=null && request.getParameter("cityupd").equals("on")) publisher.setCity(City.GetName(request.getParameter("city")));
            PublisherController.update(publisher);
        }
        if (request.getParameter("delete")!=null)
        {
            PublisherController.delete(Integer.parseInt(request.getParameter("id")));
        }
        if (request.getParameter("search")!=null)
        {
            if (request.getParameter("text")!=null && request.getParameter("text").equals("on"))
            {
                list = new LinkedList<Publisher>();
                list.add(PublisherController.select(Integer.parseInt(request.getParameter("mask"))));
            }
            else
            {
                if (request.getParameter("cityon")!=null && request.getParameter("cityon").equals("on"))
                    list = PublisherController.select(request.getParameter("mask"),City.GetCode(request.getParameter("city")));
                else list = PublisherController.select(request.getParameter("mask"));
            }
        }
        else list = PublisherController.select("");
        Collections.sort(list);
        for (int i=0; i<list.size();i++)
        {
            {
                out.print("<tr class='tr'><td class='td'>");
                out.print(list.get(i).getId());
                out.print("</td><td class='td'>");
                out.print(list.get(i).getName());
                out.print("</td><td class='td'>");
                out.print(list.get(i).getCity());
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
