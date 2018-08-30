
$(function() {
    responsive();
    $(window).resize(function(){
        responsive();
    });

    $(document).on('click','#a_account',function (){;
        $("#topbar-menu").toggleClass("show");
        $("#topbar-menu").toggleClass("hide");
        $(this).find('#ico-account').toggleClass('ion-chevron-down');
        $(this).find('#ico-account').toggleClass('ion-chevron-up');
    })
    $(document).on('click','.a_submenu',function (){
        $(this).find("#a_sub-content").toggleClass("show");
        $(this).find("#a_sub-content").toggleClass("hide");
        $(this).find('#icon').toggleClass('ion-chevron-right');
        $(this).find('#icon').toggleClass('ion-chevron-up');
    })
    $(document).on('click','#a_nav-button-responsive',function (){
        $('#a_nav').toggleClass('hide');
        $('#a_nav').toggleClass('show');
    })
    
    
    function responsive(){
        if( jQuery(window).width() < 1024 ){
        	$('#a_nav').removeClass("hide");
            $('#a_nav').removeClass("show")
            $(document).on('click','#a_search-responsive',function (){
                $("#a_search-input").addClass('responsive');
                $("#a_breadcrumb").addClass('hide');
                $("#a_search-close").addClass('responsive');
            })
            $(document).on('click','#a_search-close',function (){
                $("#a_search-input").removeClass('responsive');
                $("#a_breadcrumb").removeClass('hide');
                $("#a_search-close").removeClass('responsive');
            })
        }
        if(jQuery(window).width() < 768){
            $('#a_nav').addClass("hide");
            $("div").removeClass("content_login");
            $("backlog").addClass("content_login_sm");
        }
    }
     
});
