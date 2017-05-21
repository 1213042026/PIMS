<%-- 
    Document   : updateFinance
    Created on : 2012-3-21, 1:09:12
    Author     : Administrator
--%>

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
        <form action="http://localhost:8080/PIMS/UpdateFinanceServlet" method="post">
            <table border="2" cellspacing="0" cellpadding="0" bgcolor="CCCFFF" width="60%" align="center">
                <tr align="center">
                    <td align="center" height="130">
                        <p>请输入要修改财务记录的消费时间</p>
                        消费时间<input type="text" name="consumptionTime"/><br>
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <input type="submit" value="确 定" size="12"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" value="清 除" size="12"/>
                     </td>
                 </tr>
            </table>
        </form>
    </body>
</html>
