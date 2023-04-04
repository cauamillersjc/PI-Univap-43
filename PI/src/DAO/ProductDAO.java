package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.Product;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class ProductDAO {
    private Connection conn;
    private PreparedStatement pstm;

    public ProductDAO(){
        this.conn = new ConnectionDAO().connectBD();
    }
    
    public void cadastrarProduto(Product objproduct){
    
        String sql = "INSERT INTO products(description, ean, price, sku, status, stock_quantity) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objproduct.getDescription());
            pstm.setString(2, objproduct.getEan());
            pstm.setDouble(3, objproduct.getPrice());
            pstm.setString(4, objproduct.getSku());
            pstm.setBoolean(5, objproduct.isStatus());
            pstm.setInt(6, objproduct.getQuantity());
            
            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "CADASTRADO COM SUCESSO");
        } catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, "Cadastro ProdutoDAO" + error);
            
        }
    }
    
    public void editarProduto(Product objproduct){
        
        String sql = "UPDATE products SET description = ?, ean = ?, price = ?, sku = ?, stock_quantity = ?, status = ? WHERE id = ?";
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objproduct.getDescription());
            pstm.setString(2, objproduct.getEan());
            pstm.setDouble(3, objproduct.getPrice());
            pstm.setString(4, objproduct.getSku());
            pstm.setInt(5, objproduct.getQuantity());
            pstm.setBoolean(6, objproduct.isStatus());
            pstm.setInt(7, objproduct.getId());
        
            pstm.execute();
            pstm.close();
            
            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
            
        } catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, "editarProdutoDAO" + error);
        } 
    }
    
}
