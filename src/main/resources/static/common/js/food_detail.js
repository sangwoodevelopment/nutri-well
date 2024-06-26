(function ($) {
    "use strict";

    const foodname = $('#foodname').text();
    const baseUrl = '/api/food/detail';
    const urlWithParams = `${baseUrl}?foodname=${encodeURIComponent(foodname)}`;

    fetch(urlWithParams)
        .then(response => response.json())
        .then(foodData => {
            if (!foodData || !foodData.nutrientlist) {
                console.error('Food 객체나 nutrientlist 배열이 정의되지 않았습니다.');
                return;
            }

            const nutrientTables = [
                {
                    tableId: 'nutrientTable',
                    nutrients: ['탄수화물', '단백질', '지방', '당류', '나트륨'],
                    values: ['g', 'g', 'g', 'g', 'mg']
                },
                {
                    tableId: 'nutrientTable2',
                    nutrients: ['마그네슘', '비타민 C', '칼슘', '콜레스테롤', '트랜스지방산'],
                    values: ['mg', 'mg', 'mg', 'mg', 'g']
                }
            ];

            nutrientTables.forEach(({ tableId, nutrients, values }) => {
                nutrients.forEach((nutrient, index) => {
                    const unit = values[index];
                    const nutrientData =
                        foodData.nutrientlist.find(n => n.name === nutrient) || { name: nutrient, amount: 0, value: '' };

                    const row = $('<tr></tr>');
                    row.append($('<td></td>').text(nutrientData.name));
                    row.append($('<td></td>').addClass('amount').text(`${nutrientData.amount} ${unit}`));

                    const progressBar =
                        $('<div></div>').addClass('progress-bar').css('width', `${nutrientData.amount / 5}%`);
                    const progressDiv =
                        $('<div></div>').addClass('progress').append(progressBar);

                    row.append($('<td></td>').append(progressDiv));
                    $(`#${tableId} tbody`).append(row);
                });
            });
        })
        .catch(error => {
            console.error('데이터를 가져오는 데 실패했습니다:', error);
        });
    let userid = null;
    window.setSessionUser = function(user) {
        if (user != null) {
            userid = user.userId;
            loadBookmark();
        }
    };
    //즐겨찾기 이벤트
    function updatePreferredState() {
        const userId = userid ? userid : null;
        const foodId = $("#foodContainer").data("food");
        let querydata = {
            "userId": userId,
            "foodId": foodId,
            "preferredState": $('#favorite-button').hasClass('favorited'),
        };
        $.ajax({
            url: "/bookmark/favorited",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(querydata),
            success: function(result) {
                if(result.preferredState){
                    $("#favorite-button").addClass('favorited')
                    alert("즐겨찾기 추가되었습니다!");
                }else{
                    $("#favorite-button").removeClass('favorited')
                    alert("즐겨찾기에서 삭제되었습니다.");
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
    //제외식품이벤트
    function updateExcludedState() {
            const userId = userid ? userid : null;
            const foodId = $("#foodContainer").data("food");
            let querydata = {
                "userId": userId,
                "foodId": foodId,
                "excludedState": $('#exclude-button').hasClass('excluded')
            };
            $.ajax({
                url: "/bookmark/excluded",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(querydata),
                success: function(result) {
                    if(result.excludedState){
                        $('#exclude-button').addClass('excluded')
                        alert("제외식품으로 추가되었습니다!");
                    }else{
                        $('#exclude-button').removeClass('excluded')
                       alert("제외식품에서 제거되었습니다!");
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
    function loadBookmark() {
        const userId = userid ? userid : null;
        const foodId = $("#foodContainer").data("food");
        let querydata = {
           "userId": userId,
           "foodId": foodId,
        }
        $.ajax({
            url: "/bookmark/check",
            type: "POST",
            dataType: "json",
            data: querydata,
            success: function(result) {
                if(result.preferredState){
                    const favoriteButton = $('#favorite-button')
                                .addClass('favorited')
                }
                if(result.excludedState){
                    const excludeButton = $('#exclude-button')
                                .addClass('excluded')
                }
            },
            error: function(xhr, status, error) {
                console.error("Error occurred: " + error);
                if (typeof error_run === 'function') {
                    error_run(xhr, status, error);
                }
            },
            complete: function(xhr, status) {
                try {
                    if (!xhr.responseText) {
                        throw new Error("Empty response from server");
                    }
                    const response = JSON.parse(xhr.responseText);
                    if (!response) {
                        throw new Error("Response is null");
                    }
                } catch (error) {
                    console.error("Error occurred during JSON parsing or empty response: " + error);
                }
            }
        });
    }
    // 즐겨찾기
    window.toggleFavorite = function () {
        if (userid == null) {
            alert("로그인 해주세요!");
            return;
        }else{
            updatePreferredState();
        }
    };

    // 제외식품
    window.toggleExcludeFood = function () {
         if (userid == null) {
            alert("로그인 해주세요!");
            return;
        }else{
            updateExcludedState();
        }

    };
})(jQuery);