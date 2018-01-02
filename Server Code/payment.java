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
public class payment extends HttpServlet {

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
            out.println("<title>Servlet payment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet payment at " + request.getContextPath() + "</h1>");
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
        int nos = Integer.parseInt((String) request.getParameter("nos"));
        String accno=(String)request.getParameter("accno");
        String pwd = (String) request.getParameter("pwd");
        String doj=(String)request.getParameter("doj");
        String bid=(String)request.getParameter("bid");
        int fare=Integer.parseInt((String)request.getParameter("fare"));
        String cname=(String)request.getParameter("cname");

try{
PrintWriter out=response.getWriter();
Class.forName("com.mysql.jdbc.Driver");
Connection  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybus","root","root");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select amount from account where accno='"+accno+"' AND pwd='"+pwd+"'");
Statement st2=con.createStatement();
ResultSet rs2=st2.executeQuery("select sum(seats) from reservation where bid='"+bid+"' AND doj='"+doj+"'");
rs2.next();
Statement st3=con.createStatement();
ResultSet rs3=st3.executeQuery("select nos from bus where bid='"+bid+"'");
rs3.next();
int seats=0;
seats=rs3.getInt(1)-rs2.getInt(1);
if(rs.next())
{
    if((fare*nos)<=rs.getInt(1)&&nos<=seats)
    {
        st.executeUpdate("insert into reservation values('"+bid+"','"+cname+"','"+nos+"','"+doj+"')");
        st.executeUpdate("update account set amount=amount-"+(fare*nos)+" where accno='"+accno+"'");
        out.println("1");
    }
    else
    {
        out.println("2");
    }
    
}
else
{
    out.println("0");
}
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
