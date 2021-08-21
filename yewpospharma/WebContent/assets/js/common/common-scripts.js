/*---LEFT BAR ACCORDION----*/
var $BODY = $('body'),
    $HEADER_FILTER1 = $('#header_filter1'),
    $HEADER_FILTER2 = $('#header_filter2'),
    $DETAIL_TABLE = $('#detail_table'),
    $FOOTER_DETAIL = $('#footer_detail');
$(function() {
    $('#nav-accordion').dcAccordion({
        eventType: 'click',
        autoClose: true,
        saveState: true,
        disableLink: true,
        speed: 'slow',
        showCount: false,
        autoExpand: true,
//        cookie: 'dcjq-accordion-1',
        classExpand: 'dcjq-current-parent'
    });
});

var Script = function () {


//    sidebar dropdown menu auto scrolling

    jQuery('#sidebar .sub-menu > a').click(function () {
        var o = ($(this).offset());
        diff = 250 - o.top;
        if(diff>0)
            $("#sidebar").scrollTo("-="+Math.abs(diff),500);
        else
            $("#sidebar").scrollTo("+="+Math.abs(diff),500);
    });



//    sidebar toggle

    $(function() {
        function responsiveView() {
            var wSize = $(window).width();
            if (wSize <= 768) {
                $('#container').addClass('sidebar-close');
                $('#sidebar > ul').hide();
            }

            if (wSize > 768) {
                $('#container').removeClass('sidebar-close');
                $('#sidebar > ul').show();
            }
        }
        $(window).on('load', responsiveView);
        $(window).on('resize', responsiveView);
    });

    $('.fa-bars').click(function () {
        if ($('#sidebar > ul').is(":visible") === true) {
            $('#main-content').css({
                'margin-left': '0px'
            });
            $('#sidebar').css({
                'margin-left': '-210px'
            });
            $('#sidebar > ul').hide();
            $("#container").addClass("sidebar-closed");
        } else {
            $('#main-content').css({
                'margin-left': '210px'
            });
            $('#sidebar > ul').show();
            $('#sidebar').css({
                'margin-left': '0'
            });
            $("#container").removeClass("sidebar-closed");
        }
    });

// custom scrollbar
    $("#sidebar").niceScroll({styler:"fb",cursorcolor:"#4ECDC4", cursorwidth: '3', cursorborderradius: '10px', background: '#404040', spacebarenabled:false, cursorborder: ''});

    $("html").niceScroll({styler:"fb",cursorcolor:"#4ECDC4", cursorwidth: '6', cursorborderradius: '10px', background: '#404040', spacebarenabled:false,  cursorborder: '', zindex: '1000'});

// widget tools

    jQuery('.panel .tools .fa-chevron-down').click(function () {
        var el = jQuery(this).parents(".panel").children(".panel-body");
        if (jQuery(this).hasClass("fa-chevron-down")) {
            jQuery(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
            el.slideUp(200);
        } else {
            jQuery(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
            el.slideDown(200);
        }
    });

    jQuery('.panel .tools .fa-times').click(function () {
        jQuery(this).parents(".panel").parent().remove();
    });


//    tool tips

    $('.tooltips').tooltip();

//    popovers

    $('.popovers').popover();



// custom bar chart

    if ($(".custom-bar-chart")) {
        $(".bar").each(function () {
            var i = $(this).find(".value").html();
            $(this).find(".value").html("");
            $(this).find(".value").animate({
                height: i
            }, 2000)
        })
    }


}();

var setContentHeight = function () {
    // reset height
    //$RIGHT_COL.css('min-height', $(window).height());

    var bodyHeight = $BODY.outerHeight(),
    		bodyInner=$BODY.innerHeight(),
    		headerFilter1Height=$HEADER_FILTER1.height(),
        	headerFilter2Height=$HEADER_FILTER2.height(),
        	detailTableHeight=$DETAIL_TABLE.height(),
            footerHeight = $FOOTER_DETAIL.height(),
            screenHeight = window.outerHeight || document.body.clientHeight;
    headerFilter1Height=headerFilter1Height==null?0:headerFilter1Height;
    headerFilter2Height=headerFilter2Height==null?0:headerFilter2Height;
    detailTableHeight=detailTableHeight==null?0:detailTableHeight;
    footerHeight=footerHeight==null?0:footerHeight;
   /* alert('screen='+screenHeight);
    alert('body='+bodyHeight);
    alert('bodyinner='+bodyInner);
    alert('header1='+headerFilter1Height);
    alert('header2='+headerFilter2Height);
    alert('detail='+detailTableHeight);
    alert('footer='+footerHeight);*/
    detailTableHeight=screenHeight-60-headerFilter1Height-headerFilter2Height-footerHeight-114;
    /*alert('detailafter='+detailTableHeight);*/
        /*leftColHeight = $LEFT_COL.eq(1).height() + $SIDEBAR_FOOTER.height(),
        contentHeight = bodyHeight < leftColHeight ? leftColHeight : bodyHeight;

    // normalize content
    contentHeight -= $NAV_MENU.height() + footerHeight;

    $RIGHT_COL.css('min-height', contentHeight);*/
    $DETAIL_TABLE.css('max-height',detailTableHeight);
    $DETAIL_TABLE.css('height',detailTableHeight);
};
$( window ).resize(function() {
	setContentHeight();
	});
$(document).ready(function() {
	setContentHeight();	
});

