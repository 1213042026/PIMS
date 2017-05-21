/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package financeManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import loginRegister.LoginBean;

/**
 *
 * @author Administrator
 */
public class DeleteFinanceServlet extends HttpServlet {
    public void wrong1(){
        String msg="请输入要删除财务记录的消费时间！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong2(){
        String msg="此财务记录不存在！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void right(){
        String msg="此财务记录已成功删除！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String consumptionTime=new String(request.getParameter("consumptionTime").getBytes("ISO-8859-1"),"UTF-8");
        if(consumptionTime.length()==0){
            wrong1();
            response.sendRedirect("http://localhost:8080/PIMS/financeManager/deleteFinance.jsp");
        }else{
            try{
                Connection con=null;
                Statement stmt=null;
                ResultSet rs=null;
                Class.forName("com.mysql.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/person?useUnicode=true&characterEncoding=gbk";
                con=DriverManager.getConnection(url,"root","root");
                stmt=con.createStatement();
                String userName="";
                HttpSession session=request.getSession();
                ArrayList login=(ArrayList)session.getAttribute("login");
                if(login==null||login.size()==0){
                       response.sendRedirect("http://localhost:8080/PIMS/login.jsp");
                }else{
                    for(int i=login.size()-1;i>=0;i--){
                           LoginBean nn=(LoginBean)login.get(i);
                        userName=nn.getUserName();
                    }
                }
                String sql1="select * from finance where consumptionTime='"+consumptionTime+"'and userName='"+userName+"'";
                rs=stmt.executeQuery(sql1);
                rs.last();
                int k=rs.getRow();
                if(k<1){
                    wrong2();
                    response.sendRedirect("http://localhost:8080/PIMS/financeManager/deleteFinance.jsp");
                }else{
                    String sql2="delete from finance where consumptionTime='"+consumptionTime+"'and userName='"+userName+"'";
                    stmt.executeUpdate(sql2);
                    String sql3="select * from finance where userName='"+userName+"'";
                    rs=stmt.executeQuery(sql3);
                    rs.last();
                    int list=rs.getRow();
                    rs.beforeFirst();
                    if(list<1){
                        ArrayList financeslist=null;
                        session.setAttribute("financeslist", financeslist);
                    }else{
                    ArrayList financeslist=null;
                    financeslist=new ArrayList();
                    while(rs.next()){
                        LookFinanceBean ff=new LookFinanceBean();
                        ff.setIncome(rs.getString("income"));
                        ff.setConsumptionItem(rs.getString("consumptionItem"));
                        ff.setConsumptionSum(rs.getString("consumptionSum"));
                        ff.setConsumptionTime(rs.getString("consumptionTime"));
                        ff.setRemainingSum(rs.getString("remainingSum"));
                        financeslist.add(ff);
                        session.setAttribute("financeslist", financeslist);
                      }
                   }
                }
                rs.close();
                stmt.close();
                con.close();
                response.sendRedirect("http://localhost:8080/PIMS/financeManager/lookFinance.jsp");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

}