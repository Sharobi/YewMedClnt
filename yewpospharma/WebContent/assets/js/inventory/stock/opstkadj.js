$(document).ready(function() {

});

function changebat(id) {
	var a = checkRes(id);
	if (a == 1) {
		$("#edbt_" + id).addClass("myDiv");
	} else {
		$("#edbt_" + id).removeClass("myDiv");
		$("#edbt_" + id).removeClass("blink");
	}

}
function changeexp(id) {
	//$("#edbt_" + id).addClass("myDiv");
	var a = checkRes(id);
	if (a == 1) {
		$("#edbt_" + id).addClass("myDiv");
	} else {
		$("#edbt_" + id).removeClass("myDiv");
		$("#edbt_" + id).removeClass("blink");
	}
}
function changepqty(id) {
	//$("#edbt_" + id).addClass("myDiv");
	var a = checkRes(id);
	if (a == 1) {
		$("#edbt_" + id).addClass("myDiv");
	} else {
		$("#edbt_" + id).removeClass("myDiv");
		$("#edbt_" + id).removeClass("blink");
	}
}
function changelqty(id) {
	//$("#edbt_" + id).addClass("myDiv");
	var a = checkRes(id);
	if (a == 1) {
		$("#edbt_" + id).addClass("myDiv");
	} else {
		$("#edbt_" + id).removeClass("myDiv");
		$("#edbt_" + id).removeClass("blink");
	}
}
function changeconv(id) {
	//$("#edbt_" + id).addClass("myDiv");
	var a = checkRes(id);
	if (a == 1) {
		$("#edbt_" + id).addClass("myDiv");
	} else {
		$("#edbt_" + id).removeClass("myDiv");
		$("#edbt_" + id).removeClass("blink");
	}
}
function chVen(id) {
	//$("#edbt_" + id).addClass("myDiv");
	var a = checkRes(id);
	if (a == 1) {
		$("#edbt_" + id).addClass("myDiv");
	} else {
		$("#edbt_" + id).removeClass("myDiv");
		$("#edbt_" + id).removeClass("blink");
	}
}
function checkRate(id) {
	var mrp = $("#mrp_" + id).val();
	var rate = $("#rate_" + id).val();
	if (Number(rate) > Number(mrp)) {
		$("#rate_" + id).val(parseFloat(mrp).toFixed(2));
	}
	var a = checkRes(id);
	if (a == 1) {
		$("#edbt_" + id).addClass("myDiv");
	} else {
		$("#edbt_" + id).removeClass("myDiv");
		$("#edbt_" + id).removeClass("blink");
	}
}
function checkSaleRate(id) {
	var mrp = $("#mrp_" + id).val();
	var taxprcnt = $("#hidtax_" + id).val();
	var salrt = $("#salerate_" + id).val();
	if ($("#isexclusive").val() == 0) {
		$("#salerate_" + id).val(parseFloat(mrp).toFixed(2));
		if (Number(salrt) > Number(mrp)) {
			$("#salerate_" + id).val(parseFloat(mrp).toFixed(2));
		}
	} else {
		var mrpWithoutTax = (mrp * 100) / (100 + Number(taxprcnt));
		$("#salerate_" + id).val(parseFloat(mrpWithoutTax).toFixed(2));
		if (Number(salrt) > Number(mrpWithoutTax)) {
			$("#salerate_" + id).val(parseFloat(mrpWithoutTax).toFixed(2));
		}
	}
	var a = checkRes(id);
	if (a == 1) {
		$("#edbt_" + id).addClass("myDiv");
	} else {
		$("#edbt_" + id).removeClass("myDiv");
		$("#edbt_" + id).removeClass("blink");
	}
}
function calculateSaleRate(id) {
	var mrp = $("#mrp_" + id).val();
	var qty = $("#pqty_" + id).val();
	var ratio = $("#con_" + id).val();
	var lqty = $("#lqty_" + id).val();
	var taxprcnt = $("#hidtax_" + id).val();
	var totlqty = (qty * ratio) + Number(lqty);
	if (mrp == "") {
		mrp = 0;
	}
	if (qty == "") {
		qty = 0;
	}
	if (ratio == "") {
		ratio = 0;
	}
	if (lqty == "") {
		lqty = 0;
	}
	if (taxprcnt == "") {
		taxprcnt = 0;
	}
	//rate calculation
	var mrpWithoutTax = (mrp * 100) / (100 + Number(taxprcnt));
	var ptr = mrpWithoutTax * (1 - (Number($("#retailerProfitPrcnt").val()) / 100));
	$("#rate_" + id).val(parseFloat(ptr).toFixed(2));
	var rate = $("#rate_" + id).val();

	//sale rate calculation
	if ($("#isexclusive").val() == 0) {
		$("#salerate_" + id).val(parseFloat(mrp).toFixed(2));
	} else {
		//var mrpWithoutTax = (mrp * 100) / (100 + Number(taxprcnt));
		//console.log(mrpWithoutTax);
		$("#salerate_" + id).val(parseFloat(mrpWithoutTax).toFixed(2));
	}
	var a = checkRes(id);
	if (a == 1) {
		$("#edbt_" + id).addClass("myDiv");
	} else {
		$("#edbt_" + id).removeClass("myDiv");
		$("#edbt_" + id).removeClass("blink");
	}
}

function editOpStk(id) {
	var chkexp = 0;
	function normalizeYear(year) {
		// Century fix
		var YEARS_AHEAD = 10;
		if (year < 100) {
			var nowYear = new Date().getFullYear();
			year += Math.floor(nowYear / 100) * 100;
			if (year > nowYear + YEARS_AHEAD) {
				year -= 100;
			} else if (year <= nowYear - 100 + YEARS_AHEAD) {
				year += 100;
			}
		}
		return year;
	}

	var match = $('#exp_' + id).val().match(/^\s*(0?[1-9]|1[0-2])\/(\d\d|\d{4})\s*$/);
	if (!match) {
		// alert('Input string isn\'t match the expiration date format or date fragments are invalid.')
		//$('#exp_'+id).val($('#hidexp_'+id).val());
		chkexp = 1;
		$("#inputbarcode").text("Exp date fragments are invalid.");
		$('#expModal').modal('show');
	}
	var exp = new Date(normalizeYear(1 * match[2]), 1 * match[1] - 1, 1).valueOf();
	var now = new Date();
	var currMonth = new Date(now.getFullYear(), now.getMonth(), 1).valueOf();
	if (exp <= currMonth) {
		//alert('Expired');
		//$('#exp_'+id).val($('#hidexp_'+id).val());
		chkexp = 1;
		$("#inputbarcode").text("Date Expired.");
		$('#expModal').modal('show');
	} else {
		chkexp = 0;
		$("#inputbarcode").text("");
	}
	if (chkexp == 0) {
		var batch = $("#bat_" + id).val();
		if (batch == '') {
			batch = $("#hidbat_" + id).val();
		}
		var item = $("#hiditmid_" + id).val();
		var exp = $("#exp_" + id).val();
		if (exp == '') {
			exp = $("#hidexp_" + id).val();
		}
		var pqty = $("#pqty_" + id).val();
		if (pqty == '') {
			pqty = $("#hidpqty_" + id).val();
		}
		var lqty = $("#lqty_" + id).val();
		if (lqty == '') {
			lqty = $("#hidlqty_" + id).val();
		}
		var con = $("#con_" + id).val();
		if (con == '') {
			con = $("#hidcon_" + id).val();
		}
		var mrp = $("#mrp_" + id).val();
		if (mrp == '') {
			mrp = $("#hidmrp_" + id).val();
		}
		var rate = $("#rate_" + id).val();
		if (rate == '') {
			rate = $("#hidrate_" + id).val();
		}
		var salerate = $("#salerate_" + id).val();
		if (salerate == '') {
			salerate = $("#hidsalerate_" + id).val();
		}
		var tbl_vendorName = $("#tbl_vendorName_" + id).val();
		if (tbl_vendorName == '') {
			tbl_vendorName = $("#hiddist_" + id).val();
		}

		var stkadj = {};
		stkadj.id = id;
		stkadj.itemId = item;
		stkadj.batchNo = batch;
		stkadj.expiryDateFormat = exp;
		stkadj.packQty = pqty;
		stkadj.looseQty = lqty;
		stkadj.conversion = con;
		stkadj.mrp = mrp;
		stkadj.rate = rate;
		stkadj.saleRate = salerate;
		stkadj.distributorId = tbl_vendorName;
		$('#pleasewaitModal').modal('show');
		console.log(JSON.stringify(stkadj));
		var ajaxCallObject = new CustomBrowserXMLObject();
		ajaxCallObject.callAjaxPost(BASE_URL + "/stock/stkadjupdate.htm", stkadj, function(response) {
			$('#pleasewaitModal').modal('hide');
			console.log(response);
			$("#edbt_" + id).removeClass("myDiv");
			$("#edbt_" + id).removeClass("blink");
			var status = JSON.parse(response);
			//console.log(status);
			chngeResultStat(status);
		});
	}
}
function checkRes(id) {
	var ch = 0;
	if ($("#bat_" + id).val() == $("#hidbat_" + id).val()) {
		ch = 0;
	} else {
		ch = 1;
	}
	if (ch == 0) {
		if ($("#exp_" + id).val() == $("#hidexp_" + id).val()) {
			ch = 0;
		} else {
			ch = 1;
		}
		
	}
	if (ch == 0) {
		if (Number($("#pqty_" + id).val()) == Number($("#hidpqty_" + id).val())) {
			ch = 0;
		} else {
			ch = 1;
		}
	}
	if (ch == 0) {
		if (Number($("#lqty_" + id).val()) == Number($("#hidlqty_" + id).val())) {
			ch = 0;
		} else {
			ch = 1;
		}
	}
	if (ch == 0) {
		if (Number($("#con_" + id).val()) == Number($("#hidcon_" + id).val())) {
			ch = 0;
		} else {
			ch = 1;
		}
	}
	if (ch == 0) {
		if (Number($("#mrp_" + id).val()) == Number($("#hidmrp_" + id).val())) {
			ch = 0;
		} else {
			ch = 1;
		}
	}
	if (ch == 0) {
		if (Number($("#rate_" + id).val()) == Number($("#hidrate_" + id).val())) {
			ch = 0;
		} else {
			ch = 1;
		}
	}
	if (ch == 0) {
		if (Number($("#salerate_" + id).val()) == Number($("#hidsalerate_" + id).val())) {
			ch = 0;
		} else {
			ch = 1;
		}
	}
	if (ch == 0) {
		if (Number($("#hiddist_" + id).val()) == Number($("#tbl_vendorName_" + id).val())) {
			ch = 0;
		} else {
			ch = 1;
		}
	}
	return ch;
}
function targetURL() {
	//location.href = '#'
}
function deleteOpStk(id) {
	$("#confirmModalStk").modal("show");
	$("#confirmIdStk").val(id);
}
function ConfirmDel(){
	var id=$("#confirmIdStk").val();
	var commonResultsetmap = {};
	commonResultsetmap.id = id;
	var ajaxCallObject = new CustomBrowserXMLObject();
	ajaxCallObject.callAjaxPost(BASE_URL + "/stock/stkadjdelete.htm", commonResultsetmap, function(response) {
		$('#pleasewaitModal').modal('hide');
		var status = JSON.parse(response);
		if (status.id == -1) {
			$('#' + id).remove();
		}
		chngeResultStat(status);

	});
}