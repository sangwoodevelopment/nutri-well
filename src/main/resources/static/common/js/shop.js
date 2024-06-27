(function ($) {
    "use strict";
    let userid = null;
    window.setSessionUser = function(user) {
        if (user != null) {
            userid = user.userId;
        }
    };
    loadPreferredFood();
    //추천상품목록 load
    function loadPreferredFood() {
        $.ajax({
            url: "/bookmark/preferredlist",
            type: "POST",
            dataType: "json",
            success: function(result) {
                console.log(result);
                const $container = $('#preferrerd-food-list');
                $container.empty(); // 기존 내용을 지우기
               for (let i = 0; i < result.length; i++) {
                   const food = result[i];
                   const $foodItem = $('<div class="d-flex align-items-center justify-content-start"></div>');

                   const $imgDiv = $('<div class="rounded me-4" style="width: 100px; height: 100px;"></div>');
                   const $img = $('<img class="img-fluid rounded" alt="">').attr('src', "common/img/featur-1.jpg");
                   $imgDiv.append($img);

                   const $infoDiv = $('<div></div>');
                   const $name = $('<h6 class="mb-2"></h6>').text(food.name);

                   const $energyDiv = $('<div class="d-flex mb-2"></div>');
                   const $energy = $('<h5 class="fw-bold me-2"></h5>').text(`${food.nutrientlist[0].amount} kcal`);
                   $energyDiv.append($energy);

                   $infoDiv.append($name).append($energyDiv);
                   $foodItem.append($imgDiv).append($infoDiv);
                   $container.append($foodItem);
               }
            },
            error: function(xhr, status, error) {
                console.error("Error occurred: " + error);
                if (typeof error_run === 'function') {
                    error_run(xhr, status, error);
                }
            }
        });
    }
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
    var nutrients = JSON.parse(sessionStorage.getItem('nutrients') || '[]');
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
        var nutrients = JSON.parse(sessionStorage.getItem('nutrients') || '[]');
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
        sessionStorage.setItem('nutrients', JSON.stringify(nutrients));
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
        var nutrients = JSON.parse(sessionStorage.getItem('nutrients') || '[]');
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

