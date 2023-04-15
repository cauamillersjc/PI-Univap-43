package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.Product;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class ProductDAO {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    ArrayList<Product> list = new ArrayList<>();
    Product product;

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
    
    public void editProduct(Product objproduct){
        
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
    
    public ArrayList<Product> searchProduct(){
        String sql = "SELECT * FROM products";
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                Product objproduct = new Product();
                objproduct.setDescription(rs.getString("description"));
                objproduct.setEan(rs.getString("ean"));
                objproduct.setId(rs.getInt("id"));
                objproduct.setPrice(rs.getFloat("price"));
                objproduct.setQuantity(rs.getInt("stock_quantity"));
                objproduct.setSku(rs.getString("sku"));
                objproduct.setStatus(rs.getBoolean("status"));
                
                list.add(objproduct);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "searchProduct productDAO: " + erro);
        }
        return list;
    }
    
    public Product searchProductByEan(String ean){
        String sql = "SELECT * FROM products WHERE ean = '" + ean + "' LIMIT 1";
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                Product objproduct = new Product();
                objproduct.setDescription(rs.getString("description"));
                objproduct.setEan(rs.getString("ean"));
                objproduct.setId(rs.getInt("id"));
                objproduct.setPrice(rs.getFloat("price"));
                objproduct.setQuantity(rs.getInt("stock_quantity"));
                objproduct.setSku(rs.getString("sku"));
                objproduct.setStatus(rs.getBoolean("status"));
                
                product = objproduct;
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "searchProduct productDAO: " + erro);
        }
        return product;
    }
    
    
}
