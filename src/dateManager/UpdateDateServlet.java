/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dateManager;

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
public class UpdateDateServlet extends HttpServlet {
    public void wrong1(){
        String msg="请把日期填写完整，修改失败！";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong2(){
        String msg="请确认日期填写正确，修改失败�?";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong3(){
        String msg="请填写日程内容，修改失败�?";
        int type=JOptionPane.YES_NO_CANCEL_OPTION;
        String title="信息提示";
        JOptionPane.showMessageDialog(null, msg, title, type);
    }
    public void wrong4(){
        String msg="该日程不存在，修改失败！";
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
        String year=new String(request.getParameter("year").getBytes("ISO-8859-1"),"UTF-8");
        String month=new String(request.getParameter("month").getBytes("ISO-8859-1"),"UTF-8");
        String day=new String(request.getParameter("day").getBytes("ISO-8859-1"),"UTF-8");
        String thing=new String(request.getParameter("thing").getBytes("ISO-8859-1"),"UTF-8");
        String date="20"+year+"-"+month+"-"+year;
        if(year.length()==0||month.length()==0||day.length()==0){
            wrong1();
            response.sendRedirect("http://localhost:8080/dateManager/updateDate.jsp");
        }else if(year.length()!=2||Integer.parseInt(year)<11||Integer.parseInt(month)<1||Integer.parseInt(month)>12||Integer.parseInt(day)<1||Integer.parseInt(day)>31){
            wrong2();
            response.sendRedirect("http://localhost:8080/dateManager/updateDate.jsp");
        }else if(thing.length()==0){
            wrong3();
            response.sendRedirect("http://localhost:8080/dateManager/updateDate.jsp");
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
                String sql1="select * from date where date='"+date+"'and userName='"+userName+"'";
                rs=stmt.executeQuery(sql1);
                rs.last();
                int k;
                k=rs.getRow();
                rs.beforeFirst();
                if(k<1){
                    wrong4();
                    response.sendRedirect("http://localhost:8080/dateManager/updateDate.jsp");
                }else{
                    String sql2="update date set thing='"+thing+"' where date='"+date+"'and userName='"+userName+"'";
                    stmt.executeUpdate(sql2);
                    String sql3="select * from date where userName='"+userName+"'";
                    rs=stmt.executeQuery(sql3);
                    ArrayList datelist=new ArrayList();
                    while(rs.next()){
                        LookDateBean dd=new LookDateBean();
                        dd.setDate(rs.getString("date"));
                        dd.setThing(rs.getString("thing"));
                        datelist.add(dd);
                        session.setAttribute("datelist", datelist);
                    }
                    rs.close();
                    stmt.close();
                    con.close();
                    right();
                    response.sendRedirect("http://localhost:8080/PIMS/dateManager/lookDate.jsp");
                }
                rs.close();
                stmt.close();
                con.close();
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