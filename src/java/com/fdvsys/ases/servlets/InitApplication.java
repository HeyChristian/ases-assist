/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdvsys.ases.servlets;

import com.fdvsys.ases.utils.AppProperties;
import com.fdvsys.ases.utils.DbProperties;
import com.fdvsys.ases.utils.JFilesManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian
 */
public class InitApplication extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void init(ServletConfig config) throws ServletException{
        
        
        
        Properties prop = new Properties();
         try {
            
             
      
               InputStream configStream  = config.getServletContext().getResourceAsStream("/WEB-INF/appconfig.properties");
               prop.load(configStream);
             
               
               //Load Database Properties
               DbProperties.setDatabase(prop.getProperty("dbname"));
               DbProperties.setHostname(prop.getProperty("hostname"));
               DbProperties.setDbuser(prop.getProperty("dbuser"));
               DbProperties.setDbpassword(prop.getProperty("password"));
               DbProperties.setPort(prop.getProperty("port"));
               
               //Load Application Properties
               AppProperties.setPreprocessDirectory(prop.getProperty("preprocess"));
               AppProperties.setScriptsDirectory(prop.getProperty("scripts"));
             //  JFilesManager f = new JFilesManager();
              // f.getLoadDailyFiles();
               
             
         } catch (IOException ex) {
             Logger.getLogger("InitApplication").log(Level.SEVERE, null, ex);
             
             System.out.println("DONT FOUND appconfig.properties");
         }
        
        
        System.out.println("Startup class initialized successfully");
    }
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InitApplication</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InitApplication at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
