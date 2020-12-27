<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>每特教育|蚂蚁课堂网盘搜索引擎</title>
   <!-- 新 Bootstrap 核心 CSS 文件 -->

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

<!-- 可选的Bootstrap主题文件（一般不使用） -->

<script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->

<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->

<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body  style="display: block; margin: 0 auto; width: 50%; " >
      <div style="width:100%;height:60px;" align="center">
           <h2 style="color:#985f0d;">每特教育|蚂蚁课堂网盘搜索引擎</h2>
      </div>
      <br/>
      <div align="center">
          <span style="font-size: 18px;" ></span>
          <span style="font-size: 18px;" ></span>
      </div>
      <br/>
      <br/>
      <div class="bs-example" data-example-id="striped-table">
      <table class="table table-bordered table-hover">
             <thead>
                 <tr>
                     <th style="text-align:center;" scope="row">链接名称</th>
                     <th style="text-align:center;">文件大小GB</th>
                     <th style="text-align:center;">分享人</th>
                      <th style="text-align:center;">云盘地址</th>
                 </tr>
             </thead>
             <tbody>
             	<#list page.content as p>
                 <tr >
                     <th style="text-align: left;" >
                     ${p.name?replace(keyword, '<span style="color: red">${keyword}</span>')}
                     
                     </th>
                     <th style="text-align: left;">${p.filesize}</th>
                     <th style="text-align: left;">${p.sharpeople}</th>
                     <th style="text-align: left;">云盘地址 </th>
                </tr>
               </#list>	
             </tbody>
      </table>
      </div>
</body>
</html>
