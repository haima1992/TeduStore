<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>测试下拉选项</title>
	</head>
	<body style="font-size:30px;">
		<pre>
			第1步：
				请求路径：/test/address.do
				控制器接收到请求后，获取所有省的信息，
				然后将这些信息转发到/web/test.jsp
			第2步：
				在test.jsp中，通过jstl遍历产生所有省的选项
			第3步：
				在test.jsp中引用jquery
			第4步：
				使得“省”列表改变时有反应，
				则先为“省”列表分配id
				然后创建js函数，为“省”列表绑定change()函数
			第5步：
				在“省”列表函数响应时，
				则应该获取当前选中的省的代号，
				并发送ajax请求到服务器，
				获取该省的所有市的列表
			第6步：
				为“市”列表分配id
			第7步：
				当“省”改变时，获取到了该省的所有市的列表，
				先将“市”列表清空：$("#cities").empty()
				则遍历市的列表数据，生成 option 标签的内容，
				并且，每生成一个，就添加到“市”列表中
		</pre>
		省 份：
		<select id="provinces" style="width:130px;">
			<option value="0">----- 请选择 -----</option>
			<c:forEach items="${provinces}" var="p">
				<option value="${p.code}">${p.name}</option>
			</c:forEach>
		</select>
		城 市：
		<select id="cities" style="width:130px;">
			<option value="0">----- 请选择 -----</option>
		</select>
		区/县：
		<select id="areas" style="width:130px;">
			<option value="0">----- 请选择 -----</option>
		</select>
		<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript">
			$("#provinces").change(function(){
				var provinceCode = $("#provinces").val();
				if(provinceCode == 0){
					return;
				}
				$.ajax({
					url:"../city/list.do",
					data:"provinceCode="+provinceCode,
					type:"get",
					dataType:"json",
					success:function(obj){
						var defaultOption = "<option value=\"0\">----- 请选择 -----</option>";
						$("#cities").html(defaultOption);
						$("#areas").html(defaultOption);
						var d = obj.data;
						for(var i=0;i<d.length;i++){
							var opt = "<option value='"+d[i].code+"'>"+d[i].name+"</option>";
							$("#cities").append(opt);
						}
					}
				});
			});
			$("#cities").change(function(){
				var cityCode = $("#cities").val();
				if(cityCode == 0){
					return;
				}
				$.ajax({
					url:"../area/list.do",
					data:"cityCode="+cityCode,
					type:"get",
					dataType:"json",
					success:function(obj){
						var defaultOption = "<option value=\"0\">----- 请选择 -----</option>";
						$("#areas").html(defaultOption);
						var d = obj.data;
						for(var i=0;i<d.length;i++){
							var opt = "<option value='"+d[i].code+"'>"+d[i].name+"</option>";
							$("#areas").append(opt);
						}
					}
				});
			});
		</script>
	</body>
</html>