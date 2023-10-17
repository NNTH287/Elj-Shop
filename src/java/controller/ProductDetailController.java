/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.FeedbackDAO;
import dao.ProductDAO;
import dao.ProviderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.StringTokenizer;
import java.util.Vector;
import model.Category;
import model.Feedback;
import model.Product;
import model.Provider;

/**
 *
 * @author Admin
 */
public class ProductDetailController extends HttpServlet {

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
        int proId = Integer.parseInt(request.getParameter("proId"));
        String from = request.getParameter("from");
        ProductDAO pdao = new ProductDAO();
        CategoryDAO cdao = new CategoryDAO();
        ProviderDAO providerDAO = new ProviderDAO();
        Product product = pdao.getProductById(proId);
        Category category = cdao.getCategoryById(product.getCategoryId());
        Provider provider = providerDAO.getProviderById(product.getProviderId());
        request.setAttribute("from", from);
        request.setAttribute("product", product);
        request.setAttribute("categoryName", category.getName());
        request.setAttribute("brandName", provider.getCompanyName());
        String sort = request.getParameter("sort") != null ? request.getParameter("sort") : "";
        String categoryIdParam = request.getParameter("categoryId");
        int categoryId;
        if (categoryIdParam != null && !"".equals(categoryIdParam)) {
            categoryId = Integer.parseInt(categoryIdParam);
        } else {
            categoryId = -1; // Giá trị mặc định khi categoryId là null hoặc rỗng
        }
        String providerIdParam = request.getParameter("providerId");
        int providerId;

        if (providerIdParam != null && !"".equals(providerIdParam)) {
            providerId = Integer.parseInt(providerIdParam);
        } else {
            providerId = -1; // Giá trị mặc định khi categoryId là null hoặc rỗng
        }
        String price = request.getParameter("price");
        double minPrice = 0.0;
        double maxPrice = 10000000000000.0;
        String searchName = request.getParameter("searchName") != null ? request.getParameter("searchName") : "";

        if (price != null && !price.equals("")) {
            StringTokenizer tokenizer = new StringTokenizer(request.getParameter("price"), "-");
            minPrice = Double.parseDouble(tokenizer.nextToken());
            maxPrice = Double.parseDouble(tokenizer.nextToken());
        }
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        Vector<Product> products = productDAO.getProductByFilter(sort, categoryId, providerId, minPrice, maxPrice, searchName);
        Vector<Category> categories = categoryDAO.getAllCategory();
        Vector<Provider> providers = providerDAO.getAllProvider();
        request.setAttribute("sort", sort);
        request.setAttribute("searchName", searchName);
        request.setAttribute("products", products);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("providerId", providerId);
        request.setAttribute("price", price);
        request.setAttribute("categories", categories);
        request.setAttribute("providers", providers);

        FeedbackDAO fdao = new FeedbackDAO();
        Vector<Feedback> feedbacks = fdao.getFeedbackByProductId(proId);
        request.setAttribute("feedbacks", feedbacks);

        request.getRequestDispatcher("/jsp/productDetailPage.jsp").forward(request, response);
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