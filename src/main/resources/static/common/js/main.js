(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(window).width() < 992) {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow');
            } else {
                $('.fixed-top').removeClass('shadow');
            }
        } else {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow').css('top', -55);
            } else {
                $('.fixed-top').removeClass('shadow').css('top', 0);
            }
        } 
    });
    
    
   // Back to top button
   $(window).scroll(function () {
    if ($(this).scrollTop() > 300) {
        $('.back-to-top').fadeIn('slow');
    } else {
        $('.back-to-top').fadeOut('slow');
    }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:1
            },
            768:{
                items:1
            },
            992:{
                items:2
            },
            1200:{
                items:2
            }
        }
    });


    // vegetable carousel
    $(".vegetable-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1500,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav : true,
        navText : [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0:{
                items:1
            },
            576:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            },
            1200:{
                items:4
            }
        }
    });


    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });
        console.log($videoSrc);

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });



    // Product Quantity
    $('.quantity button').on('click', function () {
        var button = $(this);
        var oldValue = button.parent().parent().find('input').val();
        if (button.hasClass('btn-plus')) {
            var newVal = parseFloat(oldValue) + 1;
        } else {
            if (oldValue > 0) {
                var newVal = parseFloat(oldValue) - 1;
            } else {
                newVal = 0;
            }
        }
        button.parent().parent().find('input').val(newVal);
    });
$('#searchButton').on('click', function ()  {
        var queryValue = $('#query').val();
        // 파라미터들을 객체(map)로 구성
        var params = {
            query: queryValue,
            page: 0,
            size: 12
        };
        // URLSearchParams 객체를 사용하여 파라미터를 URL 형식으로 변환
        var searchParams = new URLSearchParams(params);
        var url = '/search?' + searchParams.toString();
        location.href = url;
    });
    $('.searchCategory').on('click', function ()  {
        var queryValue = $('#queryContainer').data("query");
        var category = $(this).data("filter-value");

        var params = {
            query: queryValue,
            page: 0,
            size: 12,
            category: category
        };
        /*$('#queryContainer').data("query");*/
        // URLSearchParams 객체를 사용하여 파라미터를 URL 형식으로 변환
        var searchParams = new URLSearchParams(params);
        var url = '/search?' + searchParams.toString();
        location.href = url;
    });

     $('input[type="checkbox"]').change(function() {
        const nutrient = $(this).val();
        const isChecked = $(this).is(':checked');
        var queryValue = $('#queryContainer').data("query");
        var category = $('#categoryContainer').data("category") || 0;
        var nutrients = $('#nutrientContainer').data("nutrients") || [];//널 체크및 초기화

        if (isChecked) {
            if (!nutrients.includes(nutrient)) {
                nutrients.push(nutrient);
            }
        } else {
            if (nutrients.includes(nutrient)) {
                nutrients = nutrients.filter(item => item !== nutrient);
            }
        }

        // nutrients 배열을 다시 data에 설정
        $('#nutrientContainer').data("nutrients", nutrients);

        let newNutrients = nutrients.join('|');
        var params = {
            query: queryValue,
            page: 0,
            size: 12,
            category: category,
            nutrients: newNutrients // 여기에 newNutrients를 사용
        };

        var searchParams = new URLSearchParams(params);
        var url = '/search?' + searchParams.toString();
        location.href = url;
    });
/*=============================shop.html pagination=============================*/
    const totalPage = $('#pageContainer').data("pages");;
    const maxPagesToShow = 10;
    let currentPageGroup = 0;

    renderPagination();
    function renderPagination() {
        $('#page-container').empty();
        const startPage = currentPageGroup * maxPagesToShow;
        const endPage = Math.min(startPage + maxPagesToShow, totalPage);

        for (let i = startPage; i < endPage; i++) {
            const $pageLink = $('<a href="#" class="rounded"></a>');
            $pageLink.attr('data-filter-type', 'itemPage');
            $pageLink.attr('data-filter-value', i);
            $pageLink.text(i + 1);

            $('#page-container').append($pageLink);
        }
    }

    $('#prev').on('click', function(event) {
        event.preventDefault();
        if (currentPageGroup > 0) {
            currentPageGroup--;
            renderPagination();
        }
    });

    $('#next').on('click', function(event) {
        event.preventDefault();
        if ((currentPageGroup + 1) * maxPagesToShow < totalPage) {
            currentPageGroup++;
            renderPagination();
        }
    });

    $(document).on('click', '#page-container .rounded', function(event) {
        event.preventDefault();
        var queryValue = $('#queryContainer').data("query");
        var category = $('#categoryContainer').data("category") || 0;
        var page = $(this).data("filter-value");

        $(this).addClass('active');

        var params = {
            query: queryValue,
            page: page,
            size: 12,
        };

        if (category !== undefined) {
            params.category = category;
        }

        var searchParams = new URLSearchParams(params);
        var url = '/search?' + searchParams.toString();
        location.href = url;
    });

})(jQuery);

