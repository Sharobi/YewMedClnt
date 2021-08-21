<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Confirm Modal Purchase Return Start -->

<div class="modal fade" id="confirmModalPos" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" style="width: 666px;">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.confrmation" text="Confirmation!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="footer.jsp.cnfrmquestion" text="Are you sure?" />
				<input type="hidden" id="confirmIdret">
				<input type="hidden" id="cnfrmType" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirmPos()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal end -->


<!-- Confirm Modal Start -->

<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.confrmation" text="Confirmation!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<spring:message code="footer.jsp.cnfrmquestion" text="Are you sure?" />
				<input type="hidden" id="confirmId" value="">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cnfrm_cancel_btn">
					<spring:message code="footer.jsp.btn.cancel" text="Cancel" />
				</button>
				<button type="button" onclick="DoConfirm()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Modal end -->

<!-- Confirm Message Modal Starts -->


<div class="modal fade" id="confirmMessageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!" />
				</h4>
			</div>
			<div class="modal-body">
				<div id="confirmmessagecont"></div>
				<input type="hidden" id="confirmval" value="">
				<input type="hidden" id="cnfrmCustName" />
			</div>
			<div class="modal-footer">
				<button type="button" onclick="targetURL()" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Confirm Message Modal ends -->

<!-- Please wait modal Start -->

<div class="modal fade" id="pleasewaitModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.wait" text="Please Wait!" />
				</h4>
			</div>
			<div class="modal-body">
				<div style="text-align: center;">
					<img src="${pageContext.request.contextPath}/assets/images/wait/wait.gif">
				</div>
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>
<!-- Please wait modal end -->

<!--Alert Modal Start  -->
<div class="modal fade" id="emptyAlertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
				<h4 class="modal-title" id="myModalLabel">
					<spring:message code="footer.jsp.alert" text="Alert!"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<span id="emptyalertmsg"></span>
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-theme">
					<spring:message code="footer.jsp.btn.ok" text="OK" />
				</button>
			</div>
		</div>
	</div>
</div>
<!--Alert Modal End  -->

<link href="${pageContext.request.contextPath }/assets/css/jquery-ui.css" rel="Stylesheet"></link>
 


<script src="${pageContext.request.contextPath }/assets/js/common/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/bootstrap/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/datatable/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/datatable/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/common/common.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/common/moment-with-locales.js"></script>
<script src="${pageContext.request.contextPath }/assets/ajax/ajax.js"></script>
<script type="text/javascript">
var BASE_URL = "${pageContext.request.contextPath}";
$("#searchitem_forhistory").autocomplete({
	source : function(	request,
						response) {
		if (request.term.length >= 2) {
			$.ajax({
				url : BASE_URL + "/item/autocompleteitem.htm",
				type : "GET",
				data : {
					tagName : request.term
				},
				dataType : "json",
				success : function(data) {
					response($.map(data, function(v) {
						return {
							label : v.itemName,
							itemCode : v.itemId,
							//tagCode : v.tagCode
							items : v,
						};

					}));
				},
				error : function(error) {
					alert('error: ' + error);
				}
			});
		}
	},
	select : function(	e,
						ui) {
		$("#itemid_forhistory").val(ui.item.itemCode);

	},
	change : function(	e,
						ui) {
		if (!(ui.item))
			e.target.value = "";
	},
});
$(document).on('keyup', function(e) {
	if (e.keyCode == 89 && (e.ctrlKey)) {
		e.preventDefault();
		$("#itemHistoryModalDiv").find('input:hidden').val('');
		var date = new Date();
		$("#strtdateitemhis").val(date);
		$("#enddateitemhis").val(date);
		$("#strtdateitemhis").datepicker("update", date);
		$("#enddateitemhis").datepicker("update", date);
		$("#searchitem_forhistory").val('');
		$('#itemhistorytbl tbody').empty();
		$('#itemhistoryModal').modal('show');

	}
});

$('#strtdateitemhis').datepicker({
	format : 'yyyy-mm-dd',
	autoclose : true,
});
$('#enddateitemhis').datepicker({
	format : 'yyyy-mm-dd',
	autoclose : true,
});
function stDtGrtEdDt() {
	$("#totrecepqty").text(0);
	$("#totissueqty").text(0);
	$("#totcalcqty").text(0);
	$("#itemhisbody").html("");
	var itemhistorytbl=$('#itemhistorytbl').DataTable();
	itemhistorytbl.destroy(); 
	$('#itemhistorytbl tbody').empty();
	if ((new Date($('#strtdateitemhis').val()).getTime() > new Date($('#enddateitemhis').val()).getTime())) {
		document.getElementById('alertMsgitmhis').innerHTML = getFieldText.frmdtGrtEnddt;
		//e.preventDefault();
	} else {
		//$("#searchForm").submit();
		var searchitemforhis = $("#itemid_forhistory").val();
		if (searchitemforhis == 0) {
			document.getElementById('alertMsgitmhis').innerHTML = "Please input item name for search.";
		} else {
			var CommonRelsetmapperObj = {};
			CommonRelsetmapperObj.itemId = searchitemforhis;
			CommonRelsetmapperObj.frmDate = $("#strtdateitemhis").val();
			CommonRelsetmapperObj.toDate = $("#enddateitemhis").val();
			$('#pleasewaitModal').modal('show');
			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/item/getitemhistory.htm", CommonRelsetmapperObj, function(response) {
				console.log("getitemhistory=" + response);
				$('#pleasewaitModal').modal('hide');
				var totrecepqty=0;
				var totissueqty=0;
				var totcalcqty=0;
				var itemhislist = JSON.parse(response);
				if (itemhislist.length == 0) {
					document.getElementById('alertMsgitmhis').innerHTML = "No history found.";
				} else {
					document.getElementById('alertMsgitmhis').innerHTML = "";
					for ( var i = 0; i < itemhislist.length; i++) {
						var itemhis = itemhislist[i];
						//		console.log(altmed.itemId);
						totrecepqty=Number(totrecepqty)+Number(itemhis.inCalculateLooseQty);
						totissueqty=Number(totissueqty)+Number(itemhis.outCalculateLooseQty);
						var starttrline = "<tr id=" + itemhis.itemId + "  >";
						var batch = "<td>" + itemhis.batchNo + "</td>";
						var expdt = "<td>" + itemhis.expiryDateFormat + "</td>";
						var inPackQty = "<td>" + itemhis.inPackQty + "</td>";
						var inLooseQty = "<td>" + itemhis.inLooseQty + "</td>";
						var outPackQty = "<td>" + itemhis.outPackQty + "</td>";
						var outLooseQty = "<td>" + itemhis.outLooseQty + "</td>";
						var tranType = "<td>" + itemhis.tranType + "</td>";
						var invNo = "<td>" + itemhis.invNo + "</td>";//
						var invDate = "";
						if(itemhis.invDate==undefined){
							invDate = "<td></td>";
						}else{
							invDate = "<td>" + moment(itemhis.invDate).format('YYYY-MM-DD')+ "</td>";
						}
						var partyName = "<td>" + itemhis.partyName + "</td>";
						var endtrline = "</tr>";
						createdrowline = starttrline + batch + expdt + inPackQty + inLooseQty + outPackQty + outLooseQty + tranType + invNo + invDate + partyName + endtrline;
						$("#itemhisbody").append(createdrowline);
					}
					totcalcqty=Number(totrecepqty)-Number(totissueqty);
					$("#totrecepqty").text(Number(totrecepqty));
					$("#totissueqty").text(Number(totissueqty));
					$("#totcalcqty").text(Number(totcalcqty));
				}
				
				//$('#alternateMedModal').modal('show');
				$('#itemhistorytbl').DataTable( {
					"lengthChange": false,
					"pagingType":"full",
			    } );
			});
		}

	}
}
</script>

