package DAO;

import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.Sale;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class SaleDAO {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    Sale sale;
    int lastId;

    public SaleDAO(){
        this.conn = new ConnectionDAO().connectBD();
    }
    
    public Sale finishSale(ArrayList<Product> products){
        
    }
    
    public int getLastSaleID(){
        String sql = "SELECT MAX(id) FROM sales";
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            if(rs.next()){
                while(rs.next()){
                    lastId = Integer.parseInt(rs.getString("id"));
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "getLastSaleID saleDAO: " + erro);
        }
        
        if(lastId == 0)
            lastId = 1;
        
        return lastId;
    }
}
