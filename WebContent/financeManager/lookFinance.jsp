<%-- 
    Document   : lookFinance
    Created on : 2012-3-21, 0:49:41
    Author     : Administrator
--%>

<%@page import="financeManager.LookFinanceBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>个人信息管理系统--管理财务</title>
    </head>
    <body bgcolor="CCCFFF">
        <hr noshade>
        <div align="center">
            <table border="0" cellspacing="0"cellpadding="0" width="100%"align="center">
                <tr>
                    <td width="20%">
                        <a href="http://localhost:8080/PIMS/financeManager/addFinance.jsp">增加财务记录</a>
                    </td>
                    <td width="20%">
                        查看财务记录
                    </td>
                    <td width="20%">
                        <a href="http://localhost:8080/PIMS/financeManager/updateFinance.jsp">修改财务记录</a>
                    </td>
                    <td width="20%">
                        <a href="http://localhost:8080/PIMS/financeManager/deleteFinance.jsp">删除财务记录</a>
                    </td>
                </tr>
            </table>
        </div>
        <hr noshade>
        <br><br>
        <table border="2" cellspacing="0"cellpadding="0" width="60%"align="center">
            <tr>
                <th height="30">总收入</th>
                <th height="30">消费项目</th>
                <th height="30">消费金额</th>
                <th height="30">消费时间</th>
                <th height="30">余额</th>

            </tr>
            <%
                ArrayList financeslist=(ArrayList)session.getAttribute("financeslist");
                if(financeslist==null||financeslist.size()==0){
            %>
            <div align="center">
               <h1>您还没有财务记录！</h1>
            </div>
            <%
                }else{
                    for(int i=financeslist.size()-1;i>=0;i--){
                        LookFinanceBean ff=(LookFinanceBean)financeslist.get(i);
            %>
            <tr>
                <td><%=ff.getIncome()%></td>
                <td><%=ff.getConsumptionItem()%></td>
                <td><%=ff.getConsumptionSum()%></td>
                <td><%=ff.getConsumptionTime()%></td>
                <td><%=ff.getRemainingSum()%></td>
            </tr>
            <%
                  }
                }
            %>
        </table>
    </body>
</html>

