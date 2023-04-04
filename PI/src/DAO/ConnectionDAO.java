package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConnectionDAO {

    public Connection connectBD(){

        Connection conn = null;

        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/market_pdv", "root", "");

        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "ConexaoDAO" + erro);
        }

        return conn;
    }

    public static void main(String[] args) {

    }

}