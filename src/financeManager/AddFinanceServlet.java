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
public class AddFinanceServlet extends HttpServlet {
    public void wrong1(){
        String msg="不允许有空，添加失败！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong2(){
        String msg="当天财务记录已存在，添加失败！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void right(){
        String msg="填写记录合格，添加成功！";
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
        String consumptionTime=new String(request.getParameter("consumptionTime").getBytes("ISO-8859-1"),"UTF-8");
        String remainingSum=new String(request.getParameter("remainingSum").getBytes("ISO-8859-1"),"UTF-8");
        if(income.length()==0||consumptionItem.length()==0||consumptionSum.length()==0||consumptionTime.length()==0||remainingSum.length()==0){
            wrong1();
            response.sendRedirect("http://localhost:8080/PIMS/financeManager/addFinance.jsp");
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
                int k;
                k=rs.getRow();
                if(k>0){
                    wrong2();
                    response.sendRedirect("http://localhost:8080/PIMS/financeManager/addFinance.jsp");
                }else{
                    String sql2="insert into finance"+"(userName,income,consumptionItem,consumptionSum,consumptionTime,remainingSum)"+"values("+"'"+userName+"'"+","+"'"+income+"'"+","+"'"+consumptionItem+"'"+","+"'"+consumptionSum+"'"+","+"'"+consumptionTime+"'"+","+"'"+remainingSum+"'"+")";
                    stmt.executeUpdate(sql2);
                }
                String sql3="select * from finance where userName='"+userName+"'";
                rs=stmt.executeQuery(sql3);
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