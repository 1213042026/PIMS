<%-- 
    Document   : updateFinanceMessage
    Created on : 2012-3-21, 1:33:34
    Author     : Administrator
--%>

<%@page import="financeManager.LookFinanceBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>个人信息管理系统->修改财务记录</title>
    </head>
    <body bgcolor="CCCFFF">
        <hr noshade>
        <div align="center">
            <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
                <tr>
                    <td width="20%">
                        <a href="http://localhost:8080/PIMS/financeManager/addFinance.jsp">增加财务记录</a>
                    </td>
                    <td width="20%">
                        <a href="http://localhost:8080/PIMS/LookFinanceServlet">查看财务记录</a>
                    </td>
                    <td width="20%">
                        修改财务记录
                    </td>
                    <td width="20%">
                        <a href="http://localhost:8080/PIMS/financeManager/deleteFinance.jsp">删除财务记录</a>
                    </td>
                </tr>
            </table>
        </div>
        <hr noshade>
        <br><br>
        <form action="http://localhost:8080/PIMS/UpdateFinanceMessageServlet" method="post">
            <table border="2" cellspacing="0" cellpadding="0" bgcolor="CCCFFF" width="60%" align="center">
            <%
                ArrayList financeslist2=(ArrayList)session.getAttribute("financeslist2");
                if(financeslist2==null||financeslist2.size()==0){
                    response.sendRedirect("http://localhost:8080/PIMS/financeManager/lookFinance.jsp");
                }else{
                    for(int i=financeslist2.size()-1;i>=0;i--){
                        LookFinanceBean ff=(LookFinanceBean)financeslist2.get(i);
            %>
                <tr>
                    <td height="30">消费时间</td>
                    <td><%=ff.getConsumptionTime()%></td>
                </tr>
                <tr>
                    <td height="30">总收入</td>
                    <td><input type="text" name="income" value="<%=ff.getIncome()%>"></td>
                </tr>
                <tr>
                    <td height="30">消费项目</td>
                    <td><input type="text" name="consumptionItem" value="<%=ff.getConsumptionItem()%>"></td>
                </tr>
                <tr>
                    <td height="30">消费金额</td>
                    <td><input type="text" name="consumptionSum" value="<%=ff.getConsumptionSum()%>"></td>
                </tr>
                <tr>
                    <td height="30">余额</td>
                    <td><input type="text" name="remainingSum" value="<%=ff.getRemainingSum()%>"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="确 定" size="12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" value="清 除" size="12">
                    </td>
                 </tr>
            <%
                }
               }
            %>
            </table>
        </form>
    </body>
</html>
