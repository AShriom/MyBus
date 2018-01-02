/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class reservation extends HttpServlet {

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
            out.println("<title>Servlet reservation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet reservation at " + request.getContextPath() + "</h1>");
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
        String src = (String) request.getParameter("src");
String dest = (String) request.getParameter("dest");
String date=(String)request.getParameter("date");
try{
PrintWriter out=response.getWriter();
Class.forName("com.mysql.jdbc.Driver");
Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybus","root","root");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select bid,bname,time,fare,nos from bus where src='"+src+"' AND dest='"+dest+"'");
Statement st2=con.createStatement();
ResultSet rs2;
String bid="";
int seats=0;
String output="";
while(rs.next())
{
    bid=rs.getString(1);
    rs2=st2.executeQuery("select sum(seats) from reservation where bid='"+bid+"' AND doj='"+date+"'");
    rs2.next();
    seats=rs.getInt(5)-rs2.getInt(1);
    output=output + rs.getString(1)+" || "+rs.getString(2)+" || "+rs.getString(3)+" || "+rs.getString(4)+" || "+seats+" :: ";
    rs2.close();
}
out.print(output);
rs.close();
con.close();
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
