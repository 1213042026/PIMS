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
public class UpdateFinanceMessageServlet extends HttpServlet {
     public void wrong1(){
        String msg="不允许有空，修改失败！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void right(){
        String msg="填写信息合格，修改成功！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String income=new String(request.getParameter("income").getBytes("ISO-8859-1"),"UTF-8");
        String consumptionItem=new String(request.getParameter("consumptionItem").getBytes("ISO-8859-1"),"UTF-8");
        String consumptionSum=new String(request.getParameter("consumptionSum").getBytes("ISO-8859-1"),"UTF-8");
        String remainingSum=new String(request.getParameter("remainingSum").getBytes("ISO-8859-1"),"UTF-8");
        if(income.length()==0||consumptionItem.length()==0||consumptionSum.length()==0||remainingSum.length()==0){
            wrong1();
            response.sendRedirect("http://localhost:8080/PIMS/financeManager/updateFinanceMessage.jsp");
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
                String consumptionTime=null;
                ArrayList financeslist3=(ArrayList)session.getAttribute("financeslist3");
                if(financeslist3==null||financeslist3.size()==0){
                    response.sendRedirect("http://localhost:8080/PIMS/main/bottom.jsp");
                }else{
                    for(int i=financeslist3.size()-1;i>=0;i--){
                        UpdateFinanceBean ff=(UpdateFinanceBean)financeslist3.get(i);
                        consumptionTime=ff.getConsumptionTime();
                    }
                }
                String sql1="update finance set income='"+income+"',consumptionItem='"+consumptionItem+"',consumptionSum='"+consumptionSum+"',remainingSum='"+remainingSum+"' where consumptionTime='"+consumptionTime+"'and userName='"+userName+"'";
                stmt.executeUpdate(sql1);
                String sql2="select * from finance where userName='"+userName+"'";
                rs=stmt.executeQuery(sql2);
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
                rs.close();
                stmt.close();
                con.close();
                right();
                response.sendRedirect("http://localhost:8080/PIMS/LookFinanceServlet");
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