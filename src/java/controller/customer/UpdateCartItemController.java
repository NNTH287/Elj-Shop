/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.CartDAO;
import dao.CartItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.CartItem;
import util.Helper;

/**
 *
 * @author Admin
 */
public class UpdateCartItemController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Vector<CartItem> cartItem = (Vector<CartItem>) session.getAttribute("cartItem");
        int productId = 0;
        int cartIdOfItem = 0;
        int quantity = 0;
        CartDAO cdao = new CartDAO();
        CartItemDAO cidao = new CartItemDAO();
        for (int i = 0; i < cartItem.size(); i++) {
            productId = cartItem.get(i).getProductId();
            cartIdOfItem = cartItem.get(i).getCartId();
            try {
                quantity = Integer.parseInt(request.getParameter("quantity-" + i));
            } catch (Exception e) {
                Helper.setNotification(request, "Please enter valid quantity!", "RED");
                response.sendRedirect("jsp/cartPage.jsp");
                return;
            }
            if (quantity < 1) {
                Helper.setNotification(request, "Please enter valid quantity!", "RED");
                response.sendRedirect("jsp/cartPage.jsp");
                return;
            }
            cidao.updateQuantity(productId, cartIdOfItem, quantity);
        }
        int userId = (int) session.getAttribute("userId");
        int cartId = cdao.getCartIdByCustomerId(userId);
        cartItem = cidao.getCartItemByCartId(cartId);
        session.setAttribute("cartItem", cartItem);
        response.sendRedirect("jsp/cartPage.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
