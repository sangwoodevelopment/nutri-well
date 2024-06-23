(function ($) {
    "use strict";

    $(document).ready(function() {
            console.log("shop.js 로드됨");
            // shop.js 관련 코드 작성
    });
    //main 검색기능
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
    //카테고리 조회
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
    //제외품목 조회
    var nutrients = JSON.parse(localStorage.getItem('nutrients') || '[]');
    $('input[type="checkbox"]').each(function() {
        if (nutrients.includes($(this).val())) {
            $(this).prop('checked', true);
        }
    });

    $('input[type="checkbox"]').change(function() {
        const nutrient = $(this).val();
        const isChecked = $(this).is(':checked');
        var queryValue = $('#queryContainer').data("query");
        var category = $('#categoryContainer').data("category") || 0;
        var nutrients = JSON.parse(localStorage.getItem('nutrients') || '[]');

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
        localStorage.setItem('nutrients', JSON.stringify(nutrients));
        $('#nutrientContainer').data("array", nutrients);
        var params = {
            query: queryValue,
            page: 0,
            size: 12,
            category: category,
            nutrients: nutrients
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
        var nutrients = JSON.parse(localStorage.getItem('nutrients') || '[]');
        var page = $(this).data("filter-value");

        $(this).addClass('active');

        var params = {
            query: queryValue,
            page: page,
            size: 12,
            nutrients: nutrients
        };

        if (category !== undefined) {
            params.category = category;
        }

        var searchParams = new URLSearchParams(params);
        var url = '/search?' + searchParams.toString();
        location.href = url;
    });

})(jQuery);

