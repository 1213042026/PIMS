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

/**
 *
 * @author Administrator
 */
public class UpdateFinanceServlet extends HttpServlet {
    public void wrong1(){
        String msg="请输入要修改财务记录的消费时间！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong2(){
        String msg="此消费时间不存在,无法修改！";
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
            response.sendRedirect("http://localhost:8080/PIMS/financeManager/updateFinance.jsp");
        }else{
            try{
                Connection con=null;
                Statement stmt=null;
                ResultSet rs=null;
                Class.forName("com.mysql.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/person?useUnicode=true&characterEncoding=gbk";
                con=DriverManager.getConnection(url,"root","root");
                stmt=con.createStatement();
                String sql1="select * from finance where consumptionTime='"+consumptionTime+"'";
                rs=stmt.executeQuery(sql1);
                rs.last();
                int k=rs.getRow();
                rs.beforeFirst();
                if(k<1){
                    wrong2();
                    response.sendRedirect("http://localhost:8080/PIMS/financeManager/updateFinance.jsp");
                }else{
                    HttpSession session=request.getSession();
                    ArrayList financeslist2=null;
                    financeslist2=new ArrayList();
                    while(rs.next()){
                        LookFinanceBean ff=new LookFinanceBean();
                        ff.setIncome(rs.getString("income"));
                        ff.setConsumptionItem(rs.getString("consumptionItem"));
                        ff.setConsumptionSum(rs.getString("consumptionSum"));
                        ff.setConsumptionTime(rs.getString("consumptionTime"));
                        ff.setRemainingSum(rs.getString("remainingSum"));
                        financeslist2.add(ff);
                        session.setAttribute("financeslist2", financeslist2);
                      }
                      ArrayList financeslist3=null;
                      UpdateFinanceBean nn=new UpdateFinanceBean();
                      financeslist3=new ArrayList();
                      nn.setConsumptionTime(consumptionTime);
                      financeslist3.add(nn);
                      session.setAttribute("financeslist3", financeslist3);
                }
                rs.close();
                stmt.close();
                con.close();
                response.sendRedirect("http://localhost:8080/PIMS/financeManager/updateFinanceMessage.jsp");
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