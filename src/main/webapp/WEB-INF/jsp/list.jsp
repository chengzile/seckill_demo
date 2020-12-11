<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <title>秒杀列表页</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   </head>
   <body>
   		<!-- 页面显示 -->
   		<div class="container">
   			<div class="panel panel-default">
   				<div class="panel-heading text-center">
   					<h2>秒杀列表</h2>
   				</div>
   				<div class="panel-body">
   					<table class="table table-hover">
   						<thead>
   							<tr>
   								<th>名称</th>
   								<th>库存</th>
   								<th>开始时间</th>
   								<th>结束时间</th>
   								<th>创建时间</th>
   								<th>详情页</th>
   							</tr>
   						</thead>
   						<tbody>
   							<c:forEach items="${list }" var="sk">
	   							<tr>
	   								<td>${sk.name }</td>
	   								<td>${sk.number }</td>
	   								<td><fmt:formatDate value="${sk.strartTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
	   								<td><fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
	   								<td><fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
	   								<td>
										<a class="btn btn-info" href="/seckill/seckill/${sk.seckillId }/detail" target="_black">Link</a>
									</td>
	   							</tr>
   							</c:forEach>
   						</tbody>
   					</table>
   				</div>
   			</div>
   		</div>
   </body>
</html>