<%--
  Created by IntelliJ IDEA.
  User: tranminhhuy
  Date: 23/2/26
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Thêm khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
      <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
          try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
        </script>

        <ul class="breadcrumb">
          <li>
            <i class="ace-icon fa fa-home home-icon"></i>
            <a href="#">Home</a>
          </li>
          <li class="active">Dashboard</li>
        </ul><!-- /.breadcrumb -->

      </div>

      <div class="page-content">

        <div class="page-header">
          <h1 style="font-family: 'Times New Roman', Times, serif;">
            Thêm hoặc sửa khách hàng
          </h1>
        </div><!-- /.page-header -->
        <div class = "row" style="font-family: 'Times New Roman', Times, serif;">
          <form:form modelAttribute="customerEdit" id = "listForm" method="GET">
              <div class="col-xs-12 ">
            <form class = "form-horizontal" role="form" >
              <div class="form-group">
                <label class = "col-xs-3">Tên khách hàng</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="name"/>
                </div>
              </div>


              <div class="form-group">
                <label class = "col-xs-3">Số điện thoại</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="customerPhone" />
                </div>
              </div>

              <div class="form-group">
                <label class = "col-xs-3">Email</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="email"/>
                </div>
              </div>

              <div class="form-group">
                <label class = "col-xs-3">Tên công ty</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="companyName"/>
                </div>
              </div>

              <div class="form-group">
                <label class = "col-xs-3">Nhu cầu</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="demand"/>
                </div>
              </div>
              <div class="form-group">
                <label class = "col-xs-3">Tình trạng</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="status"/>
                </div>
              </div>

              <div class="form-group">
                <label class = "col-xs-3"></label>
                <div class="col-xs-9">
                  <div>
                    <c:if test="${not empty customerEdit.id}">
                    <button type="button" class="btn btn-primary" id = "btnAddUser">
                      Sửa khách hàng
                    </button>
                    <button type="button" class="btn btn-primary" id = "btnCancel">
                      Huỷ thao tác
                    </button>
                    </c:if>
                    <c:if test="${empty customerEdit.id}">
                    <button type="button" class="btn btn-primary" id = "btnAddUser">
                      Thêm khách hàng
                    </button>
                    <button type="button" class="btn btn-primary" id = "btnCancel">
                      Huỷ thao tác
                    </button>
                    </c:if>

                  </div>

                </div>
              </div>
              <form:hidden path="id" id = "customerId"/>
            </form>
          </div>
          </form:form>
        </div>

      </div><!-- /.page-content -->
    </div>
  <c:forEach var = "item" items="${transactionType}">
<div class="col-xs-12 ">
 <div class="col-xs-12 ">
  <h3 class="header smaller lighter blue">${item.value}</h3>
  <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}',${customerEdit.id})">
  <i class="orange ace-icon fa fa-location-arrow bigger-130"></i> Add
</button>
</div>
<c:if test="${item.key == 'CSKH'}">
<div class = "col-xs-12 ">
<table id = "simple-table" class = "table table-bordered table-striped table-hover">
  <thead>
  <tr>
  <th>Ngày tạo</th>
   <th>Người tạo</th>
    <th>Chi tiết giao dịch</th>
     <th>Thao tác</th>
</tr>
</thead>
<tbody>

    <c:forEach items="${list1}" var="itm">
    <tr>
        <td>${itm.createdDate}</td>
        <td>${itm.createdBy}</td>
        <td>${itm.transactionDetail}</td>
          <td><button type="button" class="btn btn-success" title="Sủa thông tin giao dịch" onclick="updateTransaction(${itm.id}, ${customerEdit.id})">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-card-text" viewBox="0 0 16 16">
  <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2z"></path>
  <path d="M3 5.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5M3 8a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 8m0 2.5a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5"></path>
</svg>
              </button></td>
    </tr>
</c:forEach>

</tbody>
</table>
</div>
</c:if>
<c:if test="${item.key == 'DDX'}">
<div class = "col-xs-12 ">
<table id = "simple-table" class = "table table-bordered table-striped table-hover">
  <thead>
  <tr>
  <th>Ngày tạo</th>
   <th>Người tạo</th>
    <th>Chi tiết giao dịch</th>
     <th>Thao tác</th>
</tr>
</thead>
<tbody>

   <c:forEach items="${list2}" var="itm">
    <tr>
      <td>${itm.createdDate}</td>
       <td>${itm.createdBy}</td>
        <td>${itm.transactionDetail}</td>
        <td><button type="button" class="btn btn-success" title="Sủa thông tin giao dịch" onclick="updateTransaction(${itm.id}, ${customerEdit.id})">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-card-text" viewBox="0 0 16 16">
  <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2z"></path>
  <path d="M3 5.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5M3 8a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 8m0 2.5a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5"></path>
</svg>
              </button></td>
    </tr>
</c:forEach>


</tbody>
</table>
</div>
</c:if>
</div>
</c:forEach>
</div><!-- /.main-content -->
<div class="modal fade" id="transactionTypeModal" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
        <h4 class="modal-title">Nhập giao dịch</h4>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label for="transactionDetail" class="col-xs-12 col-sm-3 control-label no-padding-right">Chi tiết giao dịch</label>
          <div class="col-xs-12 col-sm-9">
                        <span class="block input-icon input-icon-right">
                            <input type="text" id="transactionDetail" class="width-100">
                        </span>
          </div>
        </div>
        <input type="hidden" name="customerId" id="modalcustomerId" value="">
        <input type="hidden" name="code" id="modalcode" value="">
        <input type="hidden" name="id" id="modalid" value="">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" id="btnAddOrUpdateTransaction">Thêm giao dịch</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
      </div>
    </div>
  </div>
</div>
<script>
  function transactionType(code, customerId){
    $('#transactionTypeModal').modal();
    $('#modalcustomerId').val(customerId);
    $('#modalcode').val(code);

  }
  function updateTransaction(id,customerId){
    $('#transactionTypeModal').modal();
    $('#modalid').val(id);
    $('#modalcustomerId').val(customerId);
  }
  $('#btnAddOrUpdateTransaction').click(function (e){
      e.preventDefault();
      var data = {};
      data['id'] = $('#modalid').val();
    data['customerId'] = $('#modalcustomerId').val();
    data['code'] = $('#modalcode').val();
    data['transactionDetail'] = $('#transactionDetail').val();
    addTransaction(data);
  });

  function addTransaction (data) {
    $.ajax ({
    type:"POST",
      url: '/api/customer/transaction',
      data: JSON.stringify(data),
// dataType: "json",
    contentType: "application/json",
      success: function (respond) {
        console.log("success");
        alert("Add transaction success");
          location.reload();
      },
      error: function(respond){
        console.log("fail");

      }
    });
  }
  $('#btnAddUser').click(function(){
    var data = {};
    var formData = $('#listForm').serializeArray();
    $.each(formData, function(i,v){
      data["" + v.name + ""] = v.value
    });

    //call api
    $.ajax ({
      type:"POST",
      url: '/api/customer',
      data: JSON.stringify(data),
// dataType: "json",
      contentType: "application/json",
      success: function (respond) {
        console.log("success");
          alert("Add customer success");
          location.reload();
      },
      error: function(respond){
        console.log("fail");
          alert("Fail to add customer");
          location.reload();
      }
    });

  });
  $('#btnCancel').click(function (){
    window.location.href= "/admin/customer-list";
  });
</script>

</body>
</html>
