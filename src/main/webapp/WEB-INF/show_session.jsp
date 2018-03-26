<%--
  Created by IntelliJ IDEA.
  User: 28029
  Date: 2018/3/19
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.EnumSet" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<html>
<head>
    <title>session_test</title>
</head>
<body>
<br>
<%String name = "";
    HttpSession se = request.getSession();
    Enumeration<String> en = (Enumeration<String>)se.getAttributeNames();
    out.println("session:");
    while(en.hasMoreElements()){
        name = en.nextElement();
        out.println("Key:"+name);
        out.println("     " );
        out.println("value:"+se.getAttribute(name));
    }
    Map<String, String[]> map =  request.getParameterMap();
    out.println("paramValue:");
    for(String key: map.keySet()){
        out.println("Key:"+key);
        out.println("     " );
        String[] strs = map.get(key);
        name = key;
%>
<%
    for(String str: strs){
        out.println("--values:"+str);
%>
<br>
<%}%>
<%}%>

<br>
<br>
session:
<br>
name:test  ---value:${test}
</body>
</html>
