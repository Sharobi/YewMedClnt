function openAddEditModal(id, countryid, stid, cname) {
	document.getElementById('alertMsg').innerHTML = '';
	$('#selcityId').val(id);
	if (id == 0) { // add
		$('#grpSelect').val(0);
		$('#stateList').html('');
		$('#stateList').val(0);
		$('#cityName').val('');

		$("#headertext").text(getCityText.headerTextAdd);
	} else { // update

		$("#headertext").text(getCityText.headerTextUpdate);
		$('#grpSelect').val(countryid);
		var commonResultObj1 = {};
		commonResultObj1.countryId = countryid;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL
				+ "/invsetup/getStateByCountry.htm", commonResultObj1,
				function(response) {
					$('#pleasewaitModal').modal('hide');
					// console.log(response);
					var res = JSON.parse(response);
					var str = "<option value='0'>Select....</option>";
					$.each(res, function(i) {
						str = str + "<option value='" + res[i].id + "'>"
								+ res[i].name + "</option>";
					});
					$("#stateList").html(str);
				});
		$('#cityName').val(cname);
		setTimeout(function() {
			$('#stateList').val(stid);
		}, 300);

	}
	$('#cityAddEditModal').modal('show');
}

function addEditCity() {
	var id=$('#selcityId').val();
	document.getElementById('alertMsg').innerHTML = '';
	var country = $('#grpSelect').val();
	var state = $('#stateList').val();
	var cityName = $('#cityName').val();

	var name_label = $("#name_label_country").text();
	var name_field = name_label.substring(0, name_label.lastIndexOf(" "));

	var name_label_state = $("#name_label_state").text();
	var name_field1 = name_label_state.substring(0, name_label_state
			.lastIndexOf(" "));

	var name_label_city = $("#name_label_city").text();
	var name_field2 = name_label_city.substring(0, name_label_city
			.lastIndexOf(" "));

	var field_names = [ [ "grpSelect", name_field ],
			[ "stateList", name_field1 ], [ "cityName", name_field2 ] ];
	if (fieldValidation(field_names) > 0) {
	} else {
		$('#cityAddEditModal').modal('hide');
		$('#pleasewaitModal').modal('show');
		if (id == 0) {
			var d = new Date();
			var commonresultsetObj = {};
			commonresultsetObj.countryId = country;
			commonresultsetObj.stateId = state;
			commonresultsetObj.name = cityName;

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addcity.htm",
					commonresultsetObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						var status = JSON.parse(response);
						chngeResultStat(status);
					});
		} else {
			var d = new Date();
			var commonresultsetObj = {};
			commonresultsetObj.id=id;
			commonresultsetObj.countryId = country;
			commonresultsetObj.stateId = state;
			commonresultsetObj.name = cityName;

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/editcity.htm",
					commonresultsetObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						console.log("response=" + response);
						var status = JSON.parse(response);
						chngeResultStat(status);
					});

		}
	}
}
function showItemDelModal(id,stid) {
	$("#confirmModalCity").modal('show');
	$("#confirmId").val(id);
	$("#constId").val(stid);
}
function DoConfirm() {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;
	var stateid=$("#constId").val();
	var commonresultsetObj = {};
	commonresultsetObj.id = id;
	commonresultsetObj.stateId = stateid;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/deleteCity.htm",
			commonresultsetObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				console.log("response=" + response);
				var status = JSON.parse(response);
				chngeResultStat(status);
			});
}
function getStateByCountry() {
	var country = $("#grpSelect").val();
	var commonResultObj = {};
	commonResultObj.countryId = country;

	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getStateByCountry.htm",
			commonResultObj, function(response) {
				$('#pleasewaitModal').modal('hide');
				// console.log(response);
				var res = JSON.parse(response);
				var str = "<option value='0'>Select....</option>";
				$.each(res, function(i) {
					str = str + "<option value='" + res[i].id + "'>"
							+ res[i].name + "</option>";
				});
				$("#stateList").html(str);
			});

}
function targetURL() {
	location.href = BASE_URL + '/invsetup/city.htm';
}