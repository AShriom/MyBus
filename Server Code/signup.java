/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author Shriom
 */
public class signup extends HttpServlet {

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
            out.println("<title>Servlet signup</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet signup at " + request.getContextPath() + "</h1>");
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
        String uname = (String) request.getParameter("uname");
String name = (String) request.getParameter("fname");
String pwd = (String) request.getParameter("pwd");
String dob = (String) request.getParameter("dob");
String email = (String) request.getParameter("email");
String cno = (String) request.getParameter("cno");

try{
PrintWriter out=response.getWriter();
    Class.forName("com.mysql.jdbc.Driver");
Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybus","root","root");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select * from users where uname='"+uname+"'");
if(rs.next())
{
	   con.close();
	   out.println("0");
}
else
{
String q="insert into users values(?,?,?,?,?,?)";
PreparedStatement p=con.prepareStatement(q);
p.setString(1,uname);
p.setString(2,name);
p.setString(3,pwd);
p.setString(4,dob);
p.setString(5,email);
p.setString(6,cno);
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
