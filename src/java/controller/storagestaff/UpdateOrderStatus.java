/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.storagestaff;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import model.Order;
import model.Product;
import model.OrderDetail;
import util.Helper;

/**
 *
 * @author Datalia
 */
public class UpdateOrderStatus extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            OrderDAO orderDAO = new OrderDAO();
            String service = request.getParameter("go");
            String searchName = request.getParameter("keyword") != null ? request.getParameter("keyword") : "";
            //String sortType = request.getParameter("sort");
            request.setAttribute("searchName", searchName);
            if (service.equals("search")) {
                Vector<Order> orders = orderDAO.getAcceptedOrdersByName(searchName);

                for (Order order : orders) {
                    System.out.println(order.toString());
                }
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("/jsp/storageManageOrderStatus.jsp").forward(request, response);
            }
        }
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
        String service = request.getParameter("go");
        OrderDAO orderDAO = new OrderDAO();
        if (service == null || service.equals("")) {
            service = "displayAll";
        }
        if (service.equals("displayAll")) {
//            OrderDAO orderDAO = new OrderDAO();
            Vector<Order> orders = orderDAO.GetStorageManageOrder();
            request.setAttribute("orders", orders);
            String[] arr = {"Processed", "Accepted", "Shipped", "Received", "Canceled"};
            List<String> status = Arrays.asList(arr);
            request.setAttribute("status", status);
            request.getRequestDispatcher("/jsp/storageManageOrderStatus.jsp").forward(request, response);
        } else if (service.equals("viewDetail")) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            ProductDAO productDAO = new ProductDAO();
            Vector<OrderDetail> orderdetails = orderDetailDAO.getById(orderId);
            Order getOrder = orderDAO.getById(orderId);
            Vector<Product> product = productDAO.getAllProduct();
            request.setAttribute("orderdetails", orderdetails);
            request.setAttribute("order", getOrder);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/jsp/storageOrderDetail.jsp").forward(request, response);
        } else if (service.equals("changeOrderStatus")) {
            int orderId = Integer.parseInt(request.getParameter("id"));
            Order order = orderDAO.getById(orderId);
            String newStatus = request.getParameter("newStatus");
            Order changeStatusOrder = orderDAO.getById(orderId);
            int checkStatusChange = 0;
            if (newStatus.equals("Packing")) {
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                orderDetailDAO.updateProductQuantity(orderId);
                checkStatusChange = orderDAO.changeOrderStatus(orderId, newStatus);
                if (checkStatusChange != 0) {
                    //Update success notification
                    Helper.setNotification(request, "Order status changed to " + newStatus + " successfully for " + changeStatusOrder.getReceiver() + "'s order!", "GREEN");
                } else {
                    //Update fail notification
                    Helper.setNotification(request, "Failed to change order status to " + newStatus + " for " + changeStatusOrder.getReceiver() + "'s order. Please try again.", "RED");
                }
            } else if (newStatus.equals("Cancelled") && order.getStatus().equals("Packing")) {
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                orderDetailDAO.increaseProductQuantity(orderId);
                response.sendRedirect("update-order-status");
                checkStatusChange = orderDAO.changeOrderStatus(orderId, newStatus);
                if (checkStatusChange != 0) {
                    //Update success notification
                    Helper.setNotification(request, "Order status changed to " + newStatus + " successfully for " + changeStatusOrder.getReceiver() + "'s order!", "GREEN");
                } else {
                    //Update fail notification
                    Helper.setNotification(request, "Failed to change order status to " + newStatus + " for " + changeStatusOrder.getReceiver() + "'s order. Please try again.", "RED");
                }
                return;
            } else if (newStatus.equals("Cancelled") || newStatus.equals("Shipping")) {
                response.sendRedirect("update-order-status");
                checkStatusChange = orderDAO.changeOrderStatus(orderId, newStatus);
                if (checkStatusChange != 0) {
                    //Update success notification
                    Helper.setNotification(request, "Order status changed to " + newStatus + " successfully for " + changeStatusOrder.getReceiver() + "'s order!", "GREEN");
                } else {
                    //Update fail notification
                    Helper.setNotification(request, "Failed to change order status to " + newStatus + " for " + changeStatusOrder.getReceiver() + "'s order. Please try again.", "RED");
                }
                return;
            }
            response.sendRedirect("update-order-status?go=viewDetail&id=" + orderId);
        }
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
