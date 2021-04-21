<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/12
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>省市级联查询</title>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
    function loadDataAjax() {
        //做ajax请求，使用jquery的$.ajax()
        $.ajax({
            url:"queryProvince",
            dataType:"json",
            success:function( resp ){
                //删除旧的数据，把已经存在的数据清空
                $("#province").empty();
                //[{"id":1,"name":"河北","jiancheng":"冀","shenghui":"石家庄"},{}]
                $.each( resp, function (i,n) {
                    //获取select这个dom对象
                    $("#province").append("<option value='"+n.id+ "'>" +  n.name + "</option>");
                })
            }
        })
    }


    $(function(){
        //  $(function()）在页面的dom的对象加载成功后执行的函数， 在此发起ajax。
        loadDataAjax();

        //绑定事件
        $("#btnLoad").click(function(){
            loadDataAjax();
        })

        //给省份的select绑定一个change事件，当select内容发生变化时，触发事件
        $("#province").change(function () {
            //获取选中的列表框的值
            var obj = $("#province>option:selected");
            // alert(" select 的change 事件" + obj.val() + "===="+obj.text())
            var provinceId = obj.val(); // 1 ,2, 3

            //做一个ajax请求，获取省份的所有城市信息
            $.post("queryCity",{proid:provinceId,name:"Lisi",age:20},callback,"json");

        })

    })


    //定义一个处理返回数据的函数
    function callback(resp){
        //清空select列表
        $("#city").empty();
        $.each( resp, function(i,n){
            $("#city").append("<option value='"+n.id+"'>"+n.name+"</option>")
        })
    }

</script>
</head>
  <body>
     <table border="1">
       <tr>
         <td>省份：</td>
         <td><select id="province">
           <option value="0">请选择.....</option>
         </select></td>
         <td><input type="button" id="btn1" value="查询省份对应的城市"/></td>
       </tr>
       <tr>
         <td>城市：</td>
         <td><select id="city">
           <option value="0">请选择.....</option>
         </select></td>
       </tr>
     </table>
  </body>
</html>
