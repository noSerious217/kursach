import Controller.AuthorController;
import Controller.Book_AuthorController;
import Controller.EditionController;
import Controller.PublisherController;
import Core.City;
import Core.ConnectionManager;
import Core.Result;
import DAO.AuthorDAO;
import DAO.Book_AuthorDAO;
import DAO.EditionDAO;
import DAO.PublisherDAO;
import Entity.Author;
import Entity.Book_Author;
import Entity.Edition;
import Entity.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args) throws SQLException {
        Core.ConnectionManager.setAttributes("web","postgres","123456");

        ConnectionManager.Close();
    }
}
