<%@ page language="java" contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
.panel-green {
    border-color: #5cc16cc9;
}
.panel-green > .panel-heading {
    border-color: #5cc16cc9;
    color: white;
    background-color: #5cc16cc9;
}
 .panel-yellow {
    border-color: #75b1cc;
}
.panel-yellow > .panel-heading {
    border-color: #e2760cbd;
    color: white;
    background-color: #e2760cbd;
}
.panel-red {
    border-color: #5a7579;
}
.panel-red > .panel-heading {
    border-color: #5a7579;
    color: white;
    background-color: #5a7579;
}
.panel-primary {
    border-color: #486a88;
}
.panel-primary > .panel-heading {
    color: #fff;
    background-color: #486a88;
    border-color: #486a88;
    }


 .wrapperContent
 {
    padding-left: 12px;
  padding-right: 1px;
    margin-top: 68px;
 }

 .coldivider
 {
     margin-right: -1%;
 }

 profit_and_loss{
  background-color: #ec1313;
    border-color: #ec1313;

}

 #main-content{
     margin-right: -4%;
 }

 canvas{
			-moz-user-select: none;
			-webkit-user-select: none;
			-ms-user-select: none;
		}

.panel{
    margin-bottom: 3%;
}
.boxheading{
    font-family: "Karla", sans-serif;
    color: white;
}
</style>

<c:set var="today" value="<%=new java.util.Date()%>" />
		<section class="wrapper wrapperContent" id="wrapperContainer">


            <!-- first row  start -->

            <div class="row">
		        <div class="col-lg-4 col-md-4 ">
		       	 <div class="form-group">
	    				<label for="startdate">From Date:</label>
	                       <input type="text" class="" id="startdate"  readonly   style="text-align: center;" value="${financialyear}">
	  				</div>
		        </div>
	         <div class="col-lg-4 col-md-4 ">
	         	<div class="form-group">
    				<label for="enddate">To Date :</label>
	         			 <input type="text" class="" id="enddate" onchange="getDataDetails()" style="text-align: center;" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${today}" />">
	          	</div>
	          </div>
	 			<div class="col-lg-4 col-md-4 " >
					<div class="form-group">
	    				<label for="enddate">Store Name :</label>
	    				<select class=" " name="storelist"   onchange="getDataDetails()"  id="storelist"  style="height: 26px;">
			 			 <option value="0">Select...</option>
				 		   <c:forEach items="${allstorelist}" var="store">
				 		     <option value="${store.id}">${store.name }</option>
							</c:forEach>
		 				</select>

	 				</div>
	 			</div>
            </div>

           <div class="row">


         <!--  backup start here  -->

            <div class="col-lg-2 col-md-2 coldivider">
	                    <div class="panel panel-green">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                                    <i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                   <div><b class="boxheading">Inventory</b></div>
	                                </div>
	                            </div>
	                              <div class="row">
	                             	 <div class="col-xs-12 text-right">
										<div class="huge" id="inventory_stock">0</div>
                                      		 <span> ${cur}: </span>  <span id="inventory_valuation" class="count">0</span>
									 </div>
	                           	 </div>
	                           </div>
	                   <!--      <a href="#">
	                            <div class="panel-footer">
	                                <span class="pull-left">View Details</span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a> -->
	                    </div>
	                </div>

	             <div class="col-lg-2 col-md-2  coldivider" >
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                      <!--           <i class="custom-pos fa-2x" ></i> -->
                            	  <img  style="width: 44px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/sale.png">
								</div>
                                <div class="col-xs-9 text-right">
                                    <div><b class="boxheading">Sale</b></div>
								</div>
                            </div>
							 <div class="row">
								<div class="col-xs-12 text-right">

									<span> ${cur}: </span>  <span id="sale" class="count">0</span>
								</div>
                            </div>
                        </div>
                    <!--  <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>
                <div class="col-lg-2 col-md-2 coldivider">
	                    <div class="panel panel-red">
	                        <div class="panel-heading">
	                            <div class="row">
	                                <div class="col-xs-3">
	                               		<i class="fa fa-user fa-2x"" aria-hidden="true"></i>
	                                </div>
	                                <div class="col-xs-9 text-right">
	                                    <div><b class="boxheading">Purchase </b></div>
	                                  </div>
	                              </div>
	                              <div class="row">
									 <div class="col-xs-12 text-right">
										 <br>
									 	 <span> ${cur}: </span>  <span id="purchase" class="count">0</span>
									</div>
	                            </div>
	                        </div>
	                     <!-- <a href="#">
	                            <div class="panel-footer">
	                                <span class="pull-left">View Details</span>
	                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
	                                <div class="clearfix"></div>
	                            </div>
	                        </a> -->
	                    </div>
	                </div>
			  <div class="col-lg-2 col-md-2 coldivider">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
									 <i class="fa fa-bank fa-2x" aria-hidden="true" style="color:#ffffff"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div><b class="boxheading">Bank</b></div>
                                 </div>
                            </div>
                             <div class="row">
								 <div class="col-xs-12 text-right">
									<br>
                                  	<span> ${cur}: </span>  <span id="bank" class="count">0</span>
                                </div>
                            </div>
                        </div>
                     <!--    <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>

 	          <div class="col-lg-2 col-md-2 coldivider">
                    <div class="panel panel"  style="background-color: #ffa50069;">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                   <img  style="width: 44px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/cashinhand.png">
                                </div>
                                <div class="col-xs-9 text-right">
 									<div><b class="boxheading">Cash In Hand</b></div>
								 </div>
                            </div>

                              <div class="row">
								 <div class="col-xs-12 text-right">
									<span> ${cur}: </span>  <span id="cash_in_hand_dramt" class="count">0</span>
                                </div>
                            </div>
                        </div>
                    <!--     <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>
               	<div class="col-lg-2 col-md-2 coldivider">
                    <div class="panel panel" id="profit_loss_back" style="background-color: #ff000069;">
                        <div class="panel-heading ">
                            <div class="row">
                                <div class="col-xs-3">
                                       <i class="fa fa-shopping-cart fa-2x" style="color:white;"></i>
                                </div>
                                <div class="col-xs-9 text-right">
									 <div><b class="boxheading">Profit &amp; Loss</b></div>
                                </div>
                            </div>
                             <div class="row">

                                <div class="col-xs-12 text-right">
                                  <br>
                                  <span> ${cur}: </span>  <span id="profit_loss" class="count">0</span>
                                </div>
                            </div>
                        </div>
                        <!-- <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>
           </div>
 <!--./ first row  start -->
           <div class="row">
	          <div class="col-lg-6 col-md-6" style="margin-right: -1%;" >
	          	 <div class="panel panel-primary" style="width:96%;margin-bottom: 1%;">
	          	 <canvas id="canvas" style="background-color: #31708f00;" height="258vw" width="558vw" ></canvas>
	          	</div>
			 </div>
			 <!--497px; width:96% -->
               <div class="col-lg-6 col-md-6" style="margin-left: -2%;">
                 <div class="panel panel-primary" style="width:96%;margin-bottom: 1%;">
					<canvas id="paichart" style="background-color: #31708f00;" height="258vw" width="558vw" ></canvas>
          		 </div>
                </div>
           </div>

           <div class="row">
 				<div class="col-lg-6 col-md-6" style="margin-left: 0%;">
                 <div class="panel panel-primary" style="width:96%;margin-bottom: 1%;">
					<canvas id="doughnut" style="background-color: #31708f00;" height="258vw" width="558vw"></canvas>
          		 </div>
                </div>
                <div class="col-lg-6 col-md-6" style="margin-left: -3%;">
                <div class="row">

                  <div class="col-lg-12 ">

                          <a title="Go to Item" href="${pageContext.request.contextPath}/item/loaditem.htm" class="btn btn-danger btn-lg" role="button" style="width: 18%;background-color: #008b8bb3;    margin-right: 1%;"><span class="glyphicon glyphicon-list-alt"></span> <br>Item</a>
                          <a title="Go to Customer"  href="${pageContext.request.contextPath}/customer/loadcustomer.htm" class="btn btn-warning btn-lg" role="button" style="width: 18%;background-color: lightslategrey; margin-right: 1%;"><span class="glyphicon glyphicon-user"></span> <br>Customer</a>
                          <a title="Go to Vendor"  href="${pageContext.request.contextPath}/vendor/loadvendor.htm" class="btn btn-warning btn-lg" role="button" style="width: 18%;  margin-right: 1%;"><span class="glyphicon glyphicon-user"></span> <br>Vendor</a>
                          <a title="Go to Purchase"  href="${pageContext.request.contextPath}/purchaseinvoice/loadpurchaseinvoice.htm" class="btn btn-primary btn-lg" role="button" style="width: 18%;    margin-right: 1%;">	<i class="fa fa-user fa-1x" style="height: 25px;" ></i><br>Purchase</a>
						  <a title="Go to Sale" href="${pageContext.request.contextPath}/pos/cashmemo.htm" class="btn btn-primary btn-lg" role="button" style="width: 17%;background-color: yellowgreen;"><img style="width: 27px;" src="/yewpospharma/assets/images/dashboardicon/sale.png"> <br>Sale</a>
                  </div>
                </div>
                 <div class="row">
                 <div class="col-lg-4  coldivider" style="margin-top: 1%;margin-right: -3%;">
                    <div class="panel panel" style="background-color: #5300ff4f;" title="Total Customers">
                    <!--#00fffc40  -->

                        <div class="panel-heading ">
                            <div class="row">
                                <div class="col-xs-3">
                                <i class="fa fa-users fa-2x" style="color:white"></i>

                                </div>
                                <div class="col-xs-9 text-right">
									 <div><b class="boxheading">Customers</b></div>
                                </div>
                            </div>
                             <div class="row">

                                <div class="col-xs-12 text-right">
                                  <br>
                                   <span id="customers" class="count">0</span>
                                </div>
                            </div>
                        </div>
                        <!-- <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>
 				<div class="col-lg-4  coldivider" style="margin-top: 1%;margin-right: -2%;">
                    <div class="panel panel" style="background-color: #ff00554f;" title="Total Products">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                             <!--    <i class="fa fa-users fa-2x"></i> -->
                           <img  style="width: 27px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/product.png">

                                </div>
                                <div class="col-xs-9 text-right">
									 <div><b class="boxheading">Products</b></div>
                                </div>
                            </div>
                             <div class="row">

                                <div class="col-xs-12 text-right">
                                  <br>
                                   <span id="product" class="count">0</span>
                                </div>
                            </div>
                        </div>
                        <!-- <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>
                <div class="col-lg-4  coldivider" style="margin-top: 1%;">
                    <div class="panel panel" style="background-color: #3f7c987d;" title="Total Vendors">
                        <div class="panel-heading ">
                            <div class="row">
                                <div class="col-xs-3">
                                 <i class="fa fa-users fa-2x" style="color:white"></i>

                                </div>
                                <div class="col-xs-9 text-right">
									 <div><b class="boxheading">Vendors</b></div>
                                </div>
                            </div>
                             <div class="row">

                                <div class="col-xs-12 text-right">
                                  <br>
                                   <span id="vendor" class="count">0</span>
                                </div>
                            </div>
                        </div>
                        <!-- <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>
                 </div>
                 <div class="row">
                 <!--   for tax out -->
                  <div class="col-lg-4  coldivider" style="margin-top: 0%; margin-right: -3%;">
                    <div class="panel panel" style="background-color: #ff00d729;" title="Total Tax Out">
                        <div class="panel-heading ">
                            <div class="row">
                                <div class="col-xs-3">
                               <!--  <i class="fa fa-users fa-2x"></i> -->
      					<img  style="width: 27px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/tax.png">
                                </div>
                                <div class="col-xs-9 text-right">
									 <div class="${cur_id==1?'':'hide'}"><b class="boxheading">Tax Input</b></div>
									  <div class="${cur_id==2?'':'hide'}"><b class="boxheading">VAT Input</b></div>

                                </div>
                            </div>
                             <div class="row">

                                <div class="col-xs-12 text-right">
                                  <br>
                                   <span id="taxinput" class="count">0</span>
                                </div>
                            </div>
                        </div>
                        <!-- <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>

                <!--  for tax in -->
                       <div class="col-lg-4  coldivider" style="margin-top: 0%; margin-right: -2%;">
                    <div class="panel panel" style="background-color: #1e734f6e;" title="Total Tax In">
                        <div class="panel-heading ">
                            <div class="row">
                                <div class="col-xs-3">
                              <!--   <i class="fa fa-users fa-2x"></i> -->
								<img  style="width: 27px;" src="${pageContext.request.contextPath }/assets/images/dashboardicon/tax.png">
                                </div>
                                <div class="col-xs-9 text-right">
									 <div class="${cur_id==1?'':'hide'}"><b class="boxheading">Tax Output</b></div>
									  <div class="${cur_id==2?'':'hide'}"><b class="boxheading">VAT Output</b></div>

                                </div>
                            </div>
                             <div class="row">

                                <div class="col-xs-12 text-right">
                                  <br>
                                   <span id="taxoutput" class="count">0</span>
                                </div>
                            </div>
                        </div>
                        <!-- <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>

                      <div class="col-lg-4  coldivider" style="margin-top: 0%; margin-right: -3%;">
                    <div class="panel panel" style="background-color: #7657c373;" title="Total Expire Items">
                        <div class="panel-heading ">
                            <div class="row">
                                <div class="col-xs-3">
                                <i class="fa fa-users fa-2x"></i>

                                </div>
                                <div class="col-xs-9 text-right">
									 <div><b class="boxheading">Expire</b></div>
                                </div>
                            </div>
                             <div class="row">

                                <div class="col-xs-12 text-right">
                                  <br>
                                   <span id="expire" class="count">0</span>
                                </div>
                            </div>
                        </div>
                        <!-- <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a> -->
                    </div>
                </div>

                 </div>
                </div>

           </div>


         </section><!--/wrapper -->

<%-- <script src="${pageContext.request.contextPath }/assets/js/common/jquery.js"></script> --%>
<script src="${pageContext.request.contextPath }/assets/js/common/jquery.backstretch.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/chart/chart_js.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/chart/util.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/chart/test.js"></script>

<script>
var cur='${cur}';
$(document).ready(function(){
    
	var currentDate = new Date();
	/* $('#startdate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	}); */

	$('#enddate').datepicker({
		format : 'yyyy-mm-dd',
		endDate : currentDate,
		autoclose : true,
	});

});
function isEmpty(val) {
    return (val === undefined || val == null || val.length <= 0) ? true : false;
}

/*   for line chart
 */

	/* labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'], */
var config = {
type: 'line',
data: {
	labels: ['April', 'May', 'June', 'July', "August", "September", "October", "November", "December",'January', 'February', 'March'],
	datasets: [{
		label: 'Sale',
		backgroundColor: window.chartColors.blue,
		borderColor: window.chartColors.blue,
	    data: [0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0],

		fill: false,
	},
	{
		label: 'Sale Return',
		fill: false,
		backgroundColor: window.chartColors.red,
		borderColor: window.chartColors.red,
		 data: [0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0],

	}]
},
options: {
	responsive: false,

	title: {
		display: true,
		text: 'Sale Details'
	},
	scales: {
		yAxes: [{
			/* ticks: {
				min: 1000,
				max: 5000000
			} */
		}]
	},
	chartArea: {
        backgroundColor: 'rgb(57, 129, 113)'
    }
}
};


// end line chart

// start pai

var randomScalingFactor = function() {
return Math.round(Math.random() * 100);
};

var config2 = {
type: 'pie',
data: {
	datasets: [{
		data: [0,0,0,0],
		backgroundColor: [
			window.chartColors.orange,
			window.chartColors.yellow,
			window.chartColors.green,
			window.chartColors.blue,
		],

	}],
	labels: [
		'Purchase Invoice',
		'Purchase Challan',
		'Purchase order',
		'Purchase Return'
	]
},
options: {
	responsive: false
}
};


/*
 * for do nut chart
 */

 var config3 = {
		 type: 'doughnut',
		 data: {
		 	datasets: [{
		 		data: [50,100,150],
		 		backgroundColor: [
		 			window.chartColors.blue,
		 			window.chartColors.orange,
		 			window.chartColors.red,

		 		],
		 		label: 'Dataset 1'
		 	}],
		 	labels: [
		 		'Cash Sale',
		 		'Card Sale',
		 		'Credit Sale'

		 	]
		 },
		 options: {
		 	responsive: false
		 }
		 };

function getAlltotal()
{
	    $.ajax({
		           url: BASE_URL + "/home/getalltotal.htm",
		           type: "POST",
		           data: {
		               startDate: document.getElementById("startdate").value,
		               endDate:document.getElementById("enddate").value,
		               id:document.getElementById("storelist").value
		           },
		           dataType: "json",
		           success: function(data) {
		        	   console.log(data);
		        	   var res = JSON.parse(JSON.stringify(data));

			             $("#inventory_stock").html("Qty: "+res.stockqty);
			             $("#inventory_valuation").html(isEmpty(res.valuation)==true?0:res.valuation);
			             $("#sale").html(isEmpty(res.saleTotalAmount)==true?0:res.saleTotalAmount);
			             $("#purchase").html(isEmpty(res.purchaseTotalAmount)==true?0:res.purchaseTotalAmount);
			             var temp_cash=res.cashInHandDr-res.cashInHandCr;
			             $("#cash_in_hand_dramt").html(isEmpty(temp_cash)==true?0:temp_cash);
			             var tempbank=res.BankAmtDr-res.BankAmtCr;
			             $("#bank").html(isEmpty(tempbank)==true?0:tempbank);


			              $("#customers").html(isEmpty(res.totalCustomer)==true?0:res.totalCustomer);
			             $("#product").html(isEmpty(res.totalProduct)==true?0:res.totalProduct);
			             $("#vendor").html(isEmpty(res.totalVendor)==true?0:res.totalVendor);

			             $("#taxinput").html(isEmpty(res.taxAndDutiesDr)==true?0:res.taxAndDutiesDr);
			             $("#taxoutput").html(isEmpty(res.taxAndDutiesCr)==true?0:res.taxAndDutiesCr);

			             $("#profit_loss").html(isEmpty(res.profitAndLoss)==true?0:res.profitAndLoss);
			             if(res.profitAndLoss>0)
			            {
			            	 $("#profit_loss_back").css("background-color","#5cc16cc9");
			            }else
			            {
			            	 $("#profit_loss_back").css("background-color","#ff000069");
			            }

					    /*
			             * for pic chart
			             */
			         	config2.data.datasets[0].data[0]=parseInt(res.totalPurchaseInv);
			         	config2.data.datasets[0].data[1]=parseInt(res.totalPurchase);
			         	config2.data.datasets[0].data[2]=parseInt(res.totalPurchaseOrder);
			         	config2.data.datasets[0].data[3]=parseInt(res.totalPurchaseReturn);
			         	window.myPie.update();



			         	config3.data.datasets[0].data[0]=parseInt(res.totalSaleCashAmount);
			         	config3.data.datasets[0].data[1]=parseInt(res.totalSaleCardAmount);
			         	config3.data.datasets[0].data[2]=parseInt(res.totalSaleCreditAmount);
			         	window.doughnut.update();

			        	 $('.count').each(function () {
			        		    $(this).prop('Counter',0).animate({
			        		        Counter: $(this).text()
			        		    }, {
			        		        duration: 4000,
			        		        easing: 'swing',
			        		        step: function (now) {
			        		            $(this).text(Math.ceil(now).toLocaleString('en'));
			        		        }
			        		    });
			        		});

			
		           },
		           error: function(error) {

		               
		           }
		       });
			
	

}
/*
for line  chart ajax call
*/

function lineChartcall()
{
   $.ajax({
           url: BASE_URL + "/home/getlinechart.htm",
           type: "POST",
           data: {
               startDate: document.getElementById("startdate").value,
               endDate:document.getElementById("enddate").value,
               id:document.getElementById("storelist").value
           },
           dataType: "json",
           success: function(data) {
        	   console.log(JSON.stringify(data));
        	    var res = JSON.parse(JSON.stringify(data));
      			var salearr=res.sales.split(",");
      			config.data.datasets[0].data[0]=parseInt(salearr[0]);
      			config.data.datasets[0].data[1]=parseInt(salearr[1]);
      			config.data.datasets[0].data[2]=parseInt(salearr[2]);
      			config.data.datasets[0].data[3]=parseInt(salearr[3]);
      			config.data.datasets[0].data[4]=parseInt(salearr[4]);
      			config.data.datasets[0].data[5]=parseInt(salearr[5]);
      			config.data.datasets[0].data[6]=parseInt(salearr[6]);
      			config.data.datasets[0].data[7]=parseInt(salearr[7]);
      			config.data.datasets[0].data[8]=parseInt(salearr[8]);
      			config.data.datasets[0].data[9]=parseInt(salearr[9]);
      			config.data.datasets[0].data[10]=parseInt(salearr[10]);
      			config.data.datasets[0].data[11]=parseInt(salearr[11]);

      			var salesReturnarr=res.salesReturn.split(",");
      			config.data.datasets[1].data[0]=parseInt(salesReturnarr[0]);
      			config.data.datasets[1].data[1]=parseInt(salesReturnarr[1]);
      			config.data.datasets[1].data[2]=parseInt(salesReturnarr[2]);
      			config.data.datasets[1].data[3]=parseInt(salesReturnarr[3]);
      			config.data.datasets[1].data[4]=parseInt(salesReturnarr[4]);
      			config.data.datasets[1].data[5]=parseInt(salesReturnarr[5]);
      			config.data.datasets[1].data[6]=parseInt(salesReturnarr[6]);
      			config.data.datasets[1].data[7]=parseInt(salesReturnarr[7]);
      			config.data.datasets[1].data[8]=parseInt(salesReturnarr[8]);
      			config.data.datasets[1].data[9]=parseInt(salesReturnarr[9]);
      			config.data.datasets[1].data[10]=parseInt(salesReturnarr[10]);
      			config.data.datasets[1].data[11]=parseInt(salesReturnarr[11]);
				window.myLine.update();
           },
           error: function(error) {

               
           }
       });
	
	
	
  

}











/* function Piechartcall()
{
	var commonResultSetMapper={
			   startDate:document.getElementById("startdate").value,
			   endDate:document.getElementById("enddate").value,
			   id:document.getElementById("storelist").value
			 };
  	var ajaxCallObject = new CustomBrowserXMLObject();
    	ajaxCallObject.callAjaxPost(BASE_URL + "/home/getalltotal.htm", commonResultSetMapper, function(response) {
			var res = JSON.parse(response);

		});
    	config2.data.datasets[0].data[0]=85;
    	window.myPie.update();
	} */




	window.onload = function()
 {

	    var ctx = document.getElementById('canvas').getContext('2d');

		window.myLine = new Chart(ctx, config);

		var ctx2 = document.getElementById('paichart').getContext('2d');
		window.myPie = new Chart(ctx2, config2);

		var ctx3 = document.getElementById('doughnut').getContext('2d');
		window.doughnut = new Chart(ctx3, config3);


	    getAlltotal();
	    lineChartcall();
	    //Piechartcall();

	/*     $('.count').each(function () {
		    $(this).prop('Counter',0).animate({
		        Counter: $(this).text()
		    }, {
		        duration: 4000,
		        easing: 'swing',
		        step: function (now) {
		            $(this).text(Math.ceil(now));
		        }
		    });
		}); */

 }

 function getDataDetails()
 {
	 getAlltotal();
	 lineChartcall();
	// Piechartcall();



  /* config.data.datasets[0].data[0]=85;
  config.data.datasets[0].data[1]=50;
  window.myLine.update();
  window.myPie.update(); */

 }

	</script>
<script type="text/javascript">
/* $("#wrapperContainer").backstretch(['${pageContext.request.contextPath}/assets/images/home/pharma_home-background.jpg']); */
</script>