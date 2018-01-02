/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shriom
 */
public class addbus extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addbus</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addbus at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String bid = (String) request.getParameter("bid");
String bname = (String) request.getParameter("bname");
String src = (String) request.getParameter("src");
String dest = (String) request.getParameter("dest");
String time = (String) request.getParameter("time");
String fare = (String) request.getParameter("fare");
String cat = (String) request.getParameter("cat");
String nos = (String) request.getParameter("nos");

try{
PrintWriter out=response.getWriter();
    Class.forName("com.mysql.jdbc.Driver");
Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybus","root","root");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select * from bus where bid='"+bid+"' OR bname='"+bname+"'");
if(rs.next())
{
	   con.close();
	   out.println("0");
}
else
{
String q="insert into bus values(?,?,?,?,?,?,?,?)";
PreparedStatement p=con.prepareStatement(q);
p.setString(1,bid);
p.setString(2,bname);
p.setString(3,src);
p.setString(4,dest);
p.setString(5,time);
p.setString(6,fare);
p.setString(7,cat);
p.setString(8,nos);
p.executeUpdate();

con.close();
out.println("1");
}
}
catch(Exception e)
{
    System.out.println(e.getMessage());
}
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
