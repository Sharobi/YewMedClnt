function getStateByCountry(){
	var country=$("#countryList").val();
	var commonResultObj = {};
	commonResultObj.countryId = country;
	
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getStateByCountry.htm", commonResultObj, function(response) {
		$('#pleasewaitModal').modal('hide');
//		console.log(response);
		var res=JSON.parse(response);
		var str="<option value='0'>Select....</option>";
		$.each(res, function (i) {
			str=str+"<option value='"+res[i].id+"'>"+res[i].name+"</option>";
        });
		$("#stateList").html(str);
	});
	
}

function getCityByState(){
	var country=$("#countryList").val();
	var state=$("#stateList").val();
	var commonResultObj = {};
	commonResultObj.countryId = country;
	commonResultObj.stateId = state;
	
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/getCityByState.htm", commonResultObj, function(response) {
		$('#pleasewaitModal').modal('hide');
//		console.log(response);
		var res=JSON.parse(response);
		var str="<option value='0'>Select....</option>";
		$.each(res, function (i) {
			str=str+"<option value='"+res[i].id+"'>"+res[i].name+"</option>";
        });
		$("#cityList").html(str);
	});
	
}

function openAddEditModal(id,countryId,stateId,cityId,districtName,zoneName) {
	document.getElementById('alertMsg').innerHTML = '';
	$("#zoneId").val(id);
	if (id == 0) { // add
		$("#headertext").text(getZoneText.headerTextAdd);
		$("#countryList").val(0);
		$('#countryList').attr("readonly",false);
		$('#stateList').attr("readonly",false);
		$('#cityList').attr("readonly",false);
	} else { // update
		$("#headertext").text(getZoneText.headerTextUpdate);
		$('#countryList').val(countryId);
		$('#countryList').attr("readonly",true)
		var commonResultObj1 = {};
		commonResultObj1.countryId = countryId;

		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL+ "/invsetup/getStateByCountry.htm", commonResultObj1,
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
		setTimeout(function() {
			$('#stateList').val(stateId);
			$('#stateList').attr("readonly",true)
			
			commonResultObj1.stateId = stateId;

			var ajaxCallObject = new CustomBrowserXMLObject();
			ajaxCallObject.callAjaxPost(BASE_URL+ "/invsetup/getCityByState.htm", commonResultObj1,
					function(response) {
						$('#pleasewaitModal').modal('hide');
						// console.log(response);
						var res = JSON.parse(response);
						var str = "<option value='0'>Select....</option>";
						$.each(res, function(i) {
							str = str + "<option value='" + res[i].id + "'>"
									+ res[i].name + "</option>";
						});
						$("#cityList").html(str);
					});
			$('#districtName').val(districtName);
			$('#zoneName').val(zoneName);
			setTimeout(function() {
				$('#cityList').val(cityId);
				$('#cityList').attr("readonly",true)
			}, 500);
		}, 300);
		
		
	}	
	$('#zoneAddEditModal').modal('show');
}

function addEditZone()
{
	document.getElementById('alertMsg').innerHTML='';
	var id=$('#zoneId').val();
	var countryId=$('#countryList').val();
	var stateId=$('#stateList').val();
	var cityId=$('#cityList').val();
	var districtName=$('#districtName').val();
	var zoneName=$('#zoneName').val();
	/*if(accTypeId==0)
	{
		document.getElementById('alertMsg').innerHTML=getLedgerText.catBlankError;
	}
	else
		{*/
			var country_label = $("#country_label").text();
			var country_field = country_label.substring(0, country_label.lastIndexOf(" "));	
			
			var state_label = $("#state_label").text();
			var state_field = state_label.substring(0, state_label.lastIndexOf(" "));
			
			var city_label = $("#city_label").text();
			var city_field = city_label.substring(0, city_label.lastIndexOf(" "));
			
			var name_label = $("#name_label").text();
			var name_field = name_label.substring(0, name_label.lastIndexOf(" "));
	
			if(countryId==0)
			{
				document.getElementById('alertMsg').innerHTML=country_field+ " " + getFieldText.fieldReq;
				return false;
			}
	
			if(stateId==0)
			{
				document.getElementById('alertMsg').innerHTML=state_field+ " " + getFieldText.fieldReq;
				return false;
			}
			
			if(cityId==0)
			{
				document.getElementById('alertMsg').innerHTML=city_field+ " " + getFieldText.fieldReq;
				return false;
			}
			
			var field_names = [["zoneName",name_field]];
			if(fieldValidation(field_names)>0)
				{			
				}
			else
				{
				$('#zoneAddEditModal').modal('hide');
				$('#pleasewaitModal').modal('show');
				if(id==0)
				{
					var commonResultObj = {};
					commonResultObj.countryId = countryId;
					commonResultObj.stateId = stateId;
					commonResultObj.cityId = cityId;
					commonResultObj.districtName = districtName;
					commonResultObj.name = zoneName;
					
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/addzone.htm", commonResultObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						var status = JSON.parse(response);
						chngeResultStat(status);
					});
				}
				else
				{
					var commonResultObj = {};
					commonResultObj.id=id;
					commonResultObj.countryId = countryId;
					commonResultObj.stateId = stateId;
					commonResultObj.cityId = cityId;
					commonResultObj.districtName = districtName;
					commonResultObj.name = zoneName;		
					
					var ajaxCallObject = new CustomBrowserXMLObject();
					ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/editzone.htm", commonResultObj, function(response) {
						$('#pleasewaitModal').modal('hide');
						var status = JSON.parse(response);
						chngeResultStat(status);
					});
				}
			}
		//}
}

function DoConfirm() {
	$('#pleasewaitModal').modal('show');
	var id = document.getElementById('confirmId').value;
	var commonResultObj = {};
	commonResultObj.id = id;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/invsetup/deletezone.htm",commonResultObj, function(response) {
		$('#pleasewaitModal').modal('hide');
		var status = JSON.parse(response);
		chngeResultStat(status);
	}, null);
}

function targetURL() {
	location.href = BASE_URL + '/invsetup/zone.htm';
}