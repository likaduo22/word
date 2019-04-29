<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<script type="text/javascript" src="./jquery.1.9.1.js"></script>
<title>读取word展现</title>

<script type="text/javascript">
//var a = new Array();
$.ajax({
    url:"word.do",    //请求的url地址
    dataType:"json",   //返回格式为json
    async:true,//请求是否异步，默认为异步，这也是ajax重要特性
    type:"POST",   //请求方式
    success:function(data){
        console.log(data)
        //  var json = eval(data); //数组       
         var dataa = data.dataInfo;
    	// console.log(dataa[0].connect)
    	 $("#list").html(dataa[0].connect);
    	var image = dataa[0].imageUrl;
    	
    	
    	for(var i =0; i<image.length; i++){
    		//console.log(image[i])
    		//a += image[i];
    		//a.push(image[i])
    		//$("#images").attr("src","/upload/"+image[i]);
    		$("#ll").append('<img src="/upload/'+image[i]+'">')
    		
    	}
    	//$("#ll").html('<img src="/upload/'+a+'">');
    },



})












</script>

</head>

<body>
	
  <div id="list">
  
  </div>
  <div id='ll'>
		<!-- <img src="" id="images"> -->
		
		  <%--  <c:forEach var=item   items= a >

   				<c:out value="${item}"></c:out>
   			
   			</c:forEach> --%>
	</div>
	
</body>
</html>