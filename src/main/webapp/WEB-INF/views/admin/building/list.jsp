<%--
  Created by IntelliJ IDEA.
  User: tranminhhuy
  Date: 23/2/26
  Time: 12:23
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
  <title>Danh sách toà nhà</title>
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
          Danh sách toà nhà
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
                  <form:form modelAttribute="modelSearch" action="/admin/building-list" id="listForm" method="GET">
                    <!-- Row 1: Tên toà nhà | Diện tích sàn -->
                <div class="row" style="margin-bottom: 10px;">
                  <div class="col-xs-6">
                    <label>Tên toà nhà</label>
<%--                    <input type="text" class="form-control" name="name" value="">--%>
                    <form:input class="form-control" path="name"/>
                  </div>
                  <div class="col-xs-6">
                    <label>Diện tích sàn</label>
<%--                    <input type="number" class="form-control" name="floorArea" value="">--%>
                     <form:input class="form-control" path="floorArea"/>
                  </div>
                </div>

                <!-- Row 2: Quận | Phường | Đường -->
                <div class="row" style="margin-bottom: 10px;">
                  <div class="col-xs-3">
                    <label>Quận</label>
                    <form:select class="form-control" path="district">
                      <form:option value="">-----Chọn quận-----</form:option>
                      <form:options items="${districts}"/>

                    </form:select>
                  </div>
                  <div class="col-xs-5">
                    <label>Phường</label>
<%--                    <input type="text" class="form-control" name="ward" value="">--%>
                      <form:input class="form-control" path="ward"/>
                  </div>
                  <div class="col-xs-4">
                    <label>Đường</label>
<%--                    <input type="text" class="form-control" name="street" value="">--%>
                      <form:input class="form-control" path="street"/>
                  </div>
                </div>

                <!-- Row 3: Số tầng hầm | Hướng | Hạng -->
                <div class="row" style="margin-bottom: 10px;">
                  <div class="col-xs-4">
                    <label>Số tầng hầm</label>
<%--                    <input type="text" class="form-control" name="numberOfBasement" value="">--%>
                     <form:input class="form-control" path="numberOfBasement"/>
                  </div>
                  <div class="col-xs-4">
                    <label>Hướng</label>
<%--                    <input type="text" class="form-control" name="direction" value="">--%>
                         <form:input class="form-control" path="direction"/>
                  </div>
                  <div class="col-xs-4">
                    <label>Hạng</label>
<%--                    <input type="number" class="form-control" name="level" value="">--%>
                     <form:input class="form-control" path="level"/>
                  </div>
                </div>

                <!-- Row 4: Diện tích từ | Diện tích đến | Giá thuê từ | Giá thuê đến -->
                <div class="row" style="margin-bottom: 10px;">
                  <div class="col-xs-3">
                    <label>Diện tích từ</label>
<%--                    <input type="number" class="form-control" name="areaFrom" value="">--%>
                     <form:input class="form-control" path="areaFrom"/>
                  </div>
                  <div class="col-xs-3">
                    <label>Diện tích đến</label>
<%--                    <input type="number" class="form-control" name="areaTo" value="">--%>
                     <form:input class="form-control" path="areaTo"/>
                  </div>
                  <div class="col-xs-3">
                    <label>Giá thuê từ</label>
<%--                    <input type="number" class="form-control" name="rentPriceFrom" value="">--%>
                     <form:input class="form-control" path="rentPriceFrom"/>
                  </div>
                  <div class="col-xs-3">
                    <label>Giá thuê đến</label>
<%--                    <input type="number" class="form-control" name="rentPriceTo" value="">--%>
                     <form:input class="form-control" path="rentPriceTo"/>
                  </div>
                </div>

                <!-- Row 5: Tên quản lý | SĐT quản lý | Nhân viên -->
                <div class="row" style="margin-bottom: 10px;">
                  <div class="col-xs-5">
                    <label>Tên quản lý</label>
<%--                    <input type="text" class="form-control" name="managerName" value="">--%>
                     <form:input class="form-control" path="managerName"/>
                  </div>
                  <div class="col-xs-5">
                    <label>Số điện thoại quản lý</label>
<%--                    <input type="text" class="form-control" name="managerPhoneNumber" value="">--%>
                     <form:input class="form-control" path="managerPhoneNumber"/>
                  </div>
                  <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
                    <div class="col-xs-2">
                    <label>Nhân viên</label>
                    <form:select class="form-control" path = "staffId">
                      <form:option value="">-----Chọn nhân viên-----</form:option>
                     <form:options items="${listStaffs}"/>
                    </form:select>
                  </div>
                  </security:authorize>
                </div>

                <!-- Row 6: Checkboxes -->
                <div class="row" style="margin-bottom: 10px;">
                  <div class="col-xs-12">
                     <form:checkboxes items="${typeCodes}" path="typeCode"/>
                  </div>
                </div>

                <!-- Row 7: Search button -->
                <div class="row">
                  <div class="col-xs-12">
                    <button type="button" class="btn btn-danger" id="btnSearchBuilding">
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                      </svg>
                      Tìm kiếm
                    </button>
                  </div>
                </div>
                <input type="hidden" id="page" name="page" value="${currentPage}"/>
                <input type="hidden" id="size" name="size" value="${size}"/>
                  </form:form>


              </div>
            </div>

            <!-- Add / Delete buttons -->
            <div class="pull-right" style="margin: 10px 10px 10px 0;">
              <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
                                <a href="/admin/building-edit">
                 <button type="button" class="btn btn-primary" title="Thêm toà nhà">
                   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-add" viewBox="0 0 16 16">
                     <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                     <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                     <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                   </svg>
                 </button>
               </a>
              <button type="button" class="btn btn-danger" title="Xoá toà nhà" id = "btnDeleteBuildings">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-building-dash" viewBox="0 0 16 16">
                  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                  <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                  <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
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
              <th>Tên toà nhà</th>
              <th>Địa chỉ</th>
              <th>Số tầng hầm</th>
              <th>Tên quản lý</th>
              <th>SĐT quản lý</th>
              <th>Diện tích sàn</th>
              <th>Diện tích trống</th>
              <th>Diện tích thuê</th>
              <th>Phí thuê</th>
              <th>Phí dịch vụ</th>
              <th>Phí môi giới</th>
              <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var = "item" items="${buildingList}">
                    <tr>
              <td class="center">
                <label class="pos-rel">
                  <input type="checkbox" class="ace" name = "checkList" value="${item.id}">
                  <span class="lbl"></span>
                </label>
              </td>
              <td> ${item.name}</td>
              <td>${item.address}</td>
              <td>${item.numberOfBasement}</td>
              <td>${item.managerName}</td>
              <td>${item.managerPhoneNumber}</td>
              <td>${item.floorArea}</td>
              <td>${item.emptyArea}</td>
              <td>${item.rentArea}</td>
              <td>${item.rentPrice}</td>
              <td>${item.brokerageFee}</td>
              <td></td>
              <td style="white-space: nowrap;">
                  <security:authorize access="hasAnyRole('ADMIN','MANAGER')">
                      <button class="btn btn-xs btn-success" title="Giao toà nhà" onclick="assignmentBuilding(${item.id})">
                    <i class="ace-icon fa fa-check bigger-120"></i>
                  </button>
                  </security:authorize>
                  <a href="/admin/building-edit-${item.id}">
                    <button class="btn btn-xs btn-info" title="Sửa toà nhà">
                      <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>
                  </a>
                  <button class="btn btn-xs btn-danger" title="Xoá toà nhà" onclick="deleteBuilding(${item.id})">
                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                  </button>
              </td>
            </tr>
            </c:forEach>

            </tbody>
          </table>
        </div>
      </div>
    <!-- Phân trang -->
<!-- Phân trang -->
<div class="row">
  <div class="col-xs-12 text-center">
    <ul class="pagination">
      <li class="${currentPage == 0 ? 'disabled' : ''}">
        <a href="#" onclick="goToPage(${currentPage - 1})">«</a>
      </li>
   <c:if test="${totalPages > 0}">
    <c:forEach begin="0" end="${totalPages - 1}" var="i">
        <li class="${i == currentPage ? 'active' : ''}">
          <a href="#" onclick="goToPage(${i})">${i + 1}</a>
        </li>
      </c:forEach>
     </c:if>
      <li class="${currentPage + 1 >= totalPages ? 'disabled' : ''}">
        <a href="#" onclick="goToPage(${currentPage + 1})">»</a>
      </li>
    </ul>
    <p>Tổng: <b>${totalElements}</b> toà nhà | Trang <b>${currentPage + 1}/${totalPages}</b></p>
  </div>
</div>
    </div><!-- /.page-content -->
  </div>
</div><!-- /.main-content -->

<!-- Modal giao toà nhà -->
<div class="modal fade" id="assignmentBuildingModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
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
        <input type="hidden" id="buildingId" name="buildingId" value="3487">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnassignmentBuilding">Giao toà nhà</button>
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
  function assignmentBuilding(buildingId){
    $('#assignmentBuildingModal').modal();
    loadStaffs(buildingId);
    $('#buildingId').val(buildingId);
  }
  function loadStaffs(buildingId){
    $.ajax({
      type: "GET",
      url: "/api/building/" + buildingId + "/staffs",
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
  $('#btnassignmentBuilding').click(function(e){
    e.preventDefault();
    var data = {};
    data['buildingId'] = $('#buildingId').val();
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
      url: "/api/building/assignment",
      data: JSON.stringify(data),
      contentType: "application/json",
      // dataType: "JSON",
      success: function (response) {
        alert("Assignment success");
        location.reload();
      },
      error: function (response) {
        console.log("fail");
        console.log(response);
      }
    });
  }
  $('#btnSearchBuilding').click(function (e){
    e.preventDefault();
    $('#listForm').submit();
  });

  function deleteBuilding(data){
    var buildingId = [data];
    deleteBuildings(buildingId);
  }
  $('#btnDeleteBuildings').click(function(e){
    e.preventDefault();
    var buildingIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
      return $(this).val();
    }).get();
    deleteBuildings(buildingIds);
  });
  function deleteBuildings(data){
    $.ajax({
      type: "Delete",
      url: "/api/building/" + data,
      data: JSON.stringify(data),
      contentType : "application/json",
      dataType: "JSON",
      success: function (response) {
        console.log("success");
        alert("Delete success");
        location.reload();
      },
      error: function(response){
        console.log("fail");
        console.log(response);
      }
  });
  }
</script>
</body>
</html>
