<%-- 
    Document   : addFinacne
    Created on : 2012-3-21, 0:27:47
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>个人信息管理系统->增加财务记录</title>
    </head>
    <body bgcolor="CCCFFF">
        <hr noshade>
        <div align="center">
            <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
                <tr>
                    <td width="20%">增加财务记录</td>
                    <td width="20%">
                        <a href="http://localhost:8080/PIMS/LookFinanceServlet">查看财务记录</a>
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
        <form action="http://localhost:8080/PIMS/AddFinanceServlet" method="post">
            <table border="2" cellspacing="0" cellpadding="0" bgcolor="CCCFFF" width="60%" align="center">
                <tr>
                    <td>总收入</td>
                    <td><input type="text" name="income"/></td>
                </tr>
                <tr>
                    <td>消费项目</td>
                    <td><input type="text" name="consumptionItem"/></td>
                </tr>
                <tr>
                    <td>消费金额</td>
                    <td><input type="text" name="consumptionSum"/></td>
                </tr>
                <tr>
                    <td>消费时间</td>
                    <td><input type="text" name="consumptionTime"/></td>
                </tr>
                <tr>
                    <td>余额</td>
                    <td><input type="text" name="remainingSum"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="确 定" size="12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="reset" value="清 除" size="12">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
