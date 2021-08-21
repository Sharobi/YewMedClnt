var storeNamefontsize = 20;
var taglineText = 'Sample Tagline';
var isTagline = 'N';
var addressfontsize = 9;
var addressfontweight = 'normal';
var dlfontsize = 9;
var dlfontweight = 'normal';
var billinginfoalignment = 'left';
var invinfoalignment = 'right';
var itemdetailtableBorder = 'default';
var itemdetailtableBorderType = 'normal';
var isTaxdetailtable = 'Y';
var taxdetailtableBorder = 'default';
var mfg = 'Y';
var grp = 'N';
var sch = 'N';
var mrp='Y';
var net = 'N';
var unit = 'Y';
var cgst = 'Y';
var sgst = 'Y';
var reverseTax = 'N';
var noteline1 = 'Sales return: Cold chain goods are liable to rejection at the discretion <br>'
+'of the seller, while returning temperature sensitive products, <br>'+
'it should bemaintained and delivered under strict cold chain.';
var noteline2 = 'Sunday Closed!';
var pharmacistsignature = 'Y';
var salesmansignature = 'Y';
var gender = 'Y';
var placeoftreatment = 'Y';
var SLNO = 'Y';
var PresIssueDate = 'Y';
var BillType = 'Y';
var CardNo = 'Y';
var netAmtfontSize = 10;
var isitemdetailtableFixedHeight = "Y";
$(document).ready(function() {
	$("#reprint_p").hide();
	if ($("#reprint_stat").val() == "Y") {
		$("#reprint_p").show();
	} else {
		$("#reprint_p").hide();
	}
	$("#storecontId").css('font-size', storeNamefontsize + 'px');
	$("#addcontId").css('font-size', addressfontsize + 'px');
	$("#addcontId").css('font-weight', addressfontweight);
	$("#dlcontId").css('font-size', dlfontsize + 'px');
	$("#dlcontId").css('font-weight', dlfontweight);
	$("#billinginfo_div").css('float', billinginfoalignment);
	$("#invinfo_div").css('float', invinfoalignment);
	$("#taglinecontId").text(taglineText);
	$("#totInvValue").css('font-size', netAmtfontSize + 'px');
	if (isTagline != 'Y') {
		$("#taglinecontId").css('display', 'none');
	}
	if (itemdetailtableBorder != 'default') {
		$("#billitemtable th").css({
			'border' : itemdetailtableBorder
		});
		if (itemdetailtableBorderType == 'vertical') {
			$('#billitemtable').find('tr td').css({
				'border-left' : itemdetailtableBorder
			});
			$('#billitemtable').find('tr td').css({
				'border-right' : itemdetailtableBorder
			});
		} else if (itemdetailtableBorderType == 'horizontal') {
			$('#billitemtable').find('tr td').css({
				'border-top' : itemdetailtableBorder
			});
			$('#billitemtable').find('tr td').css({
				'border-bottom' : itemdetailtableBorder
			});
		} else {
			$('#billitemtable').find('tr td').css({
				'border' : itemdetailtableBorder
			});
		}
		//$('#billitemtable').find('tr td').css({'border':itemdetailtableBorder});
		$('#billitemtable').find('tr:last td').css({
			'border' : 'none'
		});
		$("#billitemtable tfoot td").css({
			'border-top' : itemdetailtableBorder
		});
	}
	
	if (SLNO != 'Y') {
		$("#slno").css('display', 'none');
	}
	if (PresIssueDate != 'Y') {
		$("#pid").css('display', 'none');
	}
	if (BillType != 'Y') {
		$("#etype").css('display', 'none');
	}
	if (CardNo != 'Y') {
		$("#ccode").css('display', 'none');
	}
	if (gender != 'Y') {
		$("#genders").css('display', 'none');
	}
	if (placeoftreatment != 'Y') {
		$("#placetreatment").css('display', 'none');
	}
	if (mfg != 'Y') {
		$(".mfg").css('display', 'none');
	}
	if (mrp != 'Y') {
		$(".smrp").css('display', 'none');
	}
	if (grp != 'Y') {
		$(".grp").css('display', 'none');
	}
	if (sch != 'Y') {
		$(".sch").css('display', 'none');
	}
	if (net != 'Y') {
		$(".net").css('display', 'none');
	}
	if (unit != 'Y') {
		$(".unit").css('display', 'none');
	}
	if (cgst != 'N') {
		$(".cgst").css('display', 'none');
	}
	if (sgst != 'N') {
		$(".sgst").css('display', 'none');
	}
	if (reverseTax != 'Y') {
		$("#reversetaxRow").css('display', 'none');
	}
	if (isTaxdetailtable != 'Y') {
		$("#taxdetailtable").css('display', 'none');
	}
	if (taxdetailtableBorder != 'default') {
		$("#taxdetailtable th").css({
			'border' : taxdetailtableBorder
		});
		$('#taxdetailtable').find('tr td').css({
			'border' : taxdetailtableBorder
		});
		//$('#billitemtable').find('tr:last td').css({'border':'none'});
	}
	$("#noteline1").html(noteline1);
	$("#noteline2").text(noteline2);
	if (pharmacistsignature != 'Y') {
		$("#pharmacistsig").text('');
	}
	if (salesmansignature != 'Y') {
		$("#salesmansig").text('');
	}
	if (isitemdetailtableFixedHeight != 'Y') {
		$(".extraRow").hide();
	}
	window.print();
	//location.href = BASE_URL + '/pos/' + $("#backUrl").val() + '.htm';
	setTimeout(function(){ location.href = BASE_URL + '/pos/' + $("#backUrl").val() + '.htm'; }, 50);//26-12-2019
	//location.href = '#';
});

$("#totInvValueWord").text(number2text($("#totInvValue").text()));
/* ============== Convert number to Word in currency ========================*/

function number2text(value) {
	var fraction = Math.round(frac(value) * 100);
	var f_text = "";

	if (fraction > 0) {
		f_text = "AND " + convert_number(fraction) + " PAISE";
	}

	return convert_number(value) + " RUPEES " + f_text + " ONLY";
}

function frac(f) {
	return f % 1;
}

function convert_number(number) {
	if ((number < 0) || (number > 999999999)) {
		return "NUMBER OUT OF RANGE!";
	}
	var Gn = Math.floor(number / 10000000); /* Crore */
	number -= Gn * 10000000;
	var kn = Math.floor(number / 100000); /* lakhs */
	number -= kn * 100000;
	var Hn = Math.floor(number / 1000); /* thousand */
	number -= Hn * 1000;
	var Dn = Math.floor(number / 100); /* Tens (deca) */
	number = number % 100; /* Ones */
	var tn = Math.floor(number / 10);
	var one = Math.floor(number % 10);
	var res = "";

	if (Gn > 0) {
		res += (convert_number(Gn) + " CRORE");
	}
	if (kn > 0) {
		res += (((res == "") ? "" : " ") + convert_number(kn) + " LAKH");
	}
	if (Hn > 0) {
		res += (((res == "") ? "" : " ") + convert_number(Hn) + " THOUSAND");
	}

	if (Dn) {
		res += (((res == "") ? "" : " ") + convert_number(Dn) + " HUNDRED");
	}

	var ones = Array("", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN");
	var tens = Array("", "", "TWENTY", "THIRTY", "FOURTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY");

	if (tn > 0 || one > 0) {
		if (!(res == "")) {
			res += " AND ";
		}
		if (tn < 2) {
			res += ones[tn * 10 + one];
		} else {

			res += tens[tn];
			if (one > 0) {
				res += (ones[one]);
			}
		}
	}

	if (res == "") {
		res = "zero";
	}
	return res;
}
/* ===================== End */