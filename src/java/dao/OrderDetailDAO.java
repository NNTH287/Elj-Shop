/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderDetail;

/**
 *
 * @author LENOVO
 */
public class OrderDetailDAO extends jdbc.DBConnect {

    public OrderDetail getById(int did) {
        String sql = "SELECT [id]\n"
                + "      ,[productId]\n"
                + "      ,[orderId]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "  FROM [OnlineShop].[dbo].[OrderDetail]"
                + " where orderId = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, did);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int productID = rs.getInt(2);
                int orderID = rs.getInt(3);
                double price = rs.getDouble(4);
                int quantity = rs.getInt(5);
                return new OrderDetail(id, productID, orderID, price, quantity);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        OrderDetailDAO dao = new OrderDetailDAO();
        OrderDetail o = dao.getById(1);
        System.out.println(o);
    }
}