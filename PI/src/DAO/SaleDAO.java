package DAO;

import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Model.Sale;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public SaleDAO() {
        this.conn = new ConnectionDAO().connectBD();
    }

    public Sale finishSale(ArrayList<Product> products, Sale sale) {
        String sqlsale = "INSERT INTO sales(product_ean, price, sale_datetime, total_quantity, payment_method) VALUES(?,?,?,?,?)";
        String sqlsale_product = "INSERT INTO sale_product(product_id, sale_id, product_quantity, product_price) VALUES (?, ?, ?, ?)";
        String sqlstock = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE ean = ?";

        try {
            // Inserção na tabela sales
            pstm = conn.prepareStatement(sqlsale, Statement.RETURN_GENERATED_KEYS);

            for (Product product : products) {
                pstm.setString(1, product.getEan());
                pstm.setDouble(2, product.getPrice());
                pstm.setString(3, sale.getSale_datetime());
                pstm.setInt(4, product.getQuantity());
                pstm.setString(5, sale.getPayment_method());

                int rowsAffected = pstm.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("A inserção na tabela sales falhou, nenhuma linha afetada.");
                }

                try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        sale.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("A inserção na tabela sales falhou, nenhum ID retornado.");
                    }
                }
            }

            pstm = conn.prepareStatement(sqlsale_product);

            for (Product product : products) {
                pstm.setInt(1, product.getId());
                pstm.setInt(2, sale.getId());
                pstm.setInt(3, product.getQuantity());
                pstm.setDouble(4, product.getPrice());

                int rowsAffected = pstm.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("A inserção na tabela sale_product falhou");
                }
            }

            // Atualização da tabela products
            pstm = conn.prepareStatement(sqlstock);

            for (Product product : products) {
                pstm.setInt(1, product.getQuantity());
                pstm.setString(2, product.getEan());

                int rowsAffected = pstm.executeUpdate();

                if (rowsAffected == 0) {
                    throw new SQLException("A atualização da tabela products falhou");
                }
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar venda: " + error.getMessage());
        }

        return sale;
    }

    public int getLastSaleID() {
        String sql = "SELECT MAX(id) FROM sale_product";

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs.next()) {
                lastId = rs.getInt(1);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "getLastSaleID saleDAO: " + erro);
        }

        return lastId + 1;
    }
}
