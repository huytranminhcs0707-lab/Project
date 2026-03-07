
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
  <title>Danh sách khách hàng</title>
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
      </ul>
    </div>

    <div class="page-content">

      <div class="page-header">
        <h1>
          Danh sách khách hàng
          <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            overview &amp; stats
          </small>
        </h1>
      </div>

      <div class="row">
        <div class="col-xs-12">
          <div class="widget-box ui-sortable-handle">
            <div class="widget-header">
              <h5 class="widget-title">Tìm kiếm</h5>
              <div class="widget-toolbar">
                <a href="#" data-action="collapse">
                  <i class="ace-icon fa fa-chevron-up"></i>
                </a>
              </div>
            </div>

            <div class="widget-body" style="font-family:'Times New Roman', Times, serif;">
              <div class="widget-main" >
                  <form:form modelAttribute="modelSearch" action="/admin/customer-list" id="listForm" method="GET">
                <div class="row" style="margin-bottom: 10px;">
                  <div class="col-xs-4">
                    <label>Tên khách hàng</label>
<%--                    <input type="text" class="form-control" name="numberOfBasement" value="">--%>
                     <form:input class="form-control" path="name"/>
                  </div>
                  <div class="col-xs-4">
                    <label>Di động</label>
<%--                    <input type="text" class="form-control" name="direction" value="">--%>
                         <form:input class="form-control" path="customerPhone"/>
                  </div>
                  <div class="col-xs-4">
                    <label>Email</label>
<%--                    <input type="number" class="form-control" name="level" value="">--%>
                     <form:input class="form-control" path="email"/>
                  </div>
                </div>


                <!-- Row 5: Tên quản lý | SĐT quản lý | Nhân viên -->
                <div class="row" style="margin-bottom: 10px;">
                  <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
                    <div class="col-xs-4">
                    <label>Nhân viên</label>
                    <form:select class="form-control" path = "staffId">
                      <form:option value="">-----Chọn nhân viên-----</form:option>
                     <form:options items="${listStaffs}"/>
                    </form:select>
                  </div>
                  </security:authorize>
                </div>

                <!-- Row 7: Search button -->
                <div class="row">
                  <div class="col-xs-12">
                    <button type="button" class="btn btn-danger" id="btnSearchCustomer">
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                      </svg>
                      Tìm kiếm
                    </button>
                  </div>
                </div>
                  </form:form>


              </div>
            </div>

            <!-- Add / Delete buttons -->
            <div class="pull-right" style="margin: 10px 10px 10px 0;">
              <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
                                <a href="/admin/customer-edit">
             <button type="button" class="btn btn-primary" title="Thêm khách hàng" id = "">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-add" viewBox="0 0 16 16">
  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"></path>
  <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"></path>
</svg>
              </button>
               </a>
               <button type="button" class="btn btn-outline-danger" title="Xoá khách hàng" id = "btnDeleteCustomers">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-dash" viewBox="0 0 16 16">
  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"></path>
  <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"></path>
</svg>

              </button>
              </security:authorize>

            </div>

          </div>
        </div>
      </div>

      <!-- Bảng danh sách -->
      <div class="row">
        <div class="col-xs-12">
          <table id="tableList" style="margin: 3em 0 1.5em;" class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
              <th class="center">
                <label class="pos-rel">
                  <input type="checkbox" class="ace"  name = "checkList">
                  <span class="lbl"></span>
                </label>
              </th>
              <th>Tên khách hàng</th>
              <th>Di động</th>
              <th>Email</th>
              <th>Nhu cầu</th>
              <th>Ngày thêm</th>
              <th>Người thêm</th>
              <th>Tình trạng</th>
              <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var = "item" items="${customerList}">
                    <tr>
              <td class="center">
                <label class="pos-rel">
                  <input type="checkbox" class="ace" name = "checkList" value="${item.id}">
                  <span class="lbl"></span>
                </label>
              </td>
              <td> ${item.name}</td>
              <td>${item.customerPhone}</td>
              <td>${item.email}</td>
              <td>${item.demand}</td>
               <td>${item.createdDate}</td>
              <td>${item.createdBy}</td>
              <td>${item.status}</td>

              <td>
                  <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
                      <button class="btn btn-xs btn-success" title="Giao khách hàng" onclick="assignmentCustomer(${item.id})">
                    <i class="ace-icon fa fa-check bigger-120"></i>
                  </button>
                  </security:authorize>
                  <a href="/admin/customer-edit-${item.id}">
                    <button class="btn btn-xs btn-info" title="Sửa khách hàng">
                      <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>
                  </a>
                  <button class="btn btn-xs btn-danger" title="Xoá khách hàng" onclick="deleteCustomer(${item.id})">
                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                  </button>
              </td>
            </tr>
            </c:forEach>

            </tbody>
          </table>
        </div>
      </div>
    </div><!-- /.page-content -->
  </div>
</div><!-- /.main-content -->

<!-- Modal giao toà nhà -->
<div class="modal fade" id="assignmentCustomerModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Danh sách nhân viên</h4>
      </div>
      <div class="modal-body">
        <table id="staffList" style="margin: 3em 0 1.5em;" class="table table-striped table-bordered table-hover">
          <thead>
          <tr>
            <th class="text-center">Chọn</th>
            <th class="text-center">Tên nhân viên</th>
          </tr>
          </thead>
          <tbody>


          </tbody>
        </table>
        <input type="hidden" id="customerId" name="customerId" value="3487">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnassignmentCustomer">Giao khách hàng</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
      </div>
    </div>
  </div>
</div>
<script>
  function goToPage(page) {
    $('#page').val(page);
    $('#listForm').submit();
  }
  function assignmentCustomer(customerId){
    $('#assignmentCustomerModal').modal();
    loadStaffs(customerId);
    $('#customerId').val(customerId);
  }
  function loadStaffs(customerId){
    $.ajax({
      type: "GET",
      url: "/api/customer/" + customerId + "/staffs",
      // data: JSON.stringify(buildingId),
      // contentType : "application/json",
      dataType: "JSON",
      success: function (response) {
        var row = '';
        $.each(response.data, function (index,item){
          row += '<tr>';
          row += '<td class="text-center"><input type="checkbox" value="' + item.staffId + '" id="checkbox_' + item.staffId +'" ' + item.checked + '/></td>';
          row += '<td class="text-center">' + item.fullName + '</td>';
          row += '</tr>';

        });
        $('#staffList tbody').html(row);
        console.log("success");
      },
      error: function(response){
        console.log("fail");
        console.log(response);
      }
    });
  }
  $('#btnassignmentCustomer').click(function(e){
    e.preventDefault();
    var data = {};
    data['customerId'] = $('#customerId').val();
    var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
      return $(this).val();
    }).get();
    data['staffs'] = staffs;
    console.log("oke");
    if (data['staffs'] != ''){
      assignment(data);
    }
  });
  function assignment(data){
    $.ajax({
      type: "Post",
      url: "/api/customer/assignment",
      data: JSON.stringify(data),
      contentType: "application/json",
      // dataType: "JSON",
      success: function (response) {
        console.log("success");
        alert("Assignment success");
        location.reload();
      },
      error: function (response) {
        console.log("fail");
        alert("Assignment error");
        location.reload();
      }
    });
  }
  $('#btnSearchCustomer').click(function (e){
    e.preventDefault();
    $('#listForm').submit();
  });

  function deleteCustomer(data){
  var customerId = [data];
  deleteCustomers(customerId);
  }
  $('#btnDeleteCustomers').click(function(e){
    e.preventDefault();
    var customerIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
      return $(this).val();
    }).get();
    deleteCustomers(customerIds);
  });
  function deleteCustomers(data){
    $.ajax({
      type: "Delete",
      url: "/api/customer/" + data,
      data: JSON.stringify(data),
      contentType : "application/json",
      // dataType: "JSON",
      success: function (response) {
        alert("Delete success");
        location.reload();
      },
      error: function(response){
        console.log("fail");
        alert("Delete error");
        location.reload();
      }
    });
  }
</script>
</body>
</html>
