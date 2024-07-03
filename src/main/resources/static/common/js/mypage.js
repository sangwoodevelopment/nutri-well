<!-- FullCalendar Initialization -->
document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        events: '/calendar/api/calendars', // URL to fetch events
        displayEventTime: false,
        eventDisplay: 'block',
        eventClick: function(info) {
            fetchDailyNutrition(info.event.id); // 이벤트 클릭 시 호출
        }
    });
    calendar.render();
});

<!-- 캘린더 캡쳐 -->
document.getElementById('capture-btn').addEventListener('click', function () {
    html2canvas(document.getElementById('calendar'), {
        onrendered: function (canvas) {
            var img = canvas.toDataURL('image/png');
            document.getElementById('captured-image').src = img;
            document.getElementById('captured-image').style.display = 'block';

            var downloadLink = document.getElementById('download-link');
            downloadLink.href = img;
            downloadLink.style.display = 'inline'; // 다운로드 링크 표시
        }
    });
});

<!--수정성공-->
function showSuccessAlert() {
    alert("회원수정이 완료되었습니다");
    return true;
}

function fetchDailyNutrition(calendarId) {
    $.ajax({
        url: '/calendar/api/dailyNutrition',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ calendarId: calendarId }),
        success: function(response) {
            showNutritionModal(response);
        },
        error: function(xhr, status, error) {
            console.error('Error occurred:', error);
            alert('데이터를 가져오는 데 실패했습니다.');
        }
    });
}

function showNutritionModal(data) {
    const modal = $('#nutritionModal');
    const chartEl = $('#nutritionChart');
    const ctx = chartEl[0].getContext('2d');

    // 권장 섭취량 설정
    const recommendedIntake = {
        '탄수화물': 300, // 예시 값, 실제 권장 섭취량으로 수정
        '단백질': 50,    // 예시 값, 실제 권장 섭취량으로 수정
        '지방': 70,      // 예시 값, 실제 권장 섭취량으로 수정
        '당류': 50,      // 예시 값, 실제 권장 섭취량으로 수정
        '나트륨': 2300,  // 예시 값, 실제 권장 섭취량으로 수정
        '트랜스지방산': 2,  // 예시 값, 실제 권장 섭취량으로 수정
        '포화지방산': 20,   // 예시 값, 실제 권장 섭취량으로 수정
        '콜레스테롤': 300   // 예시 값, 실제 권장 섭취량으로 수정
    };

    // 섭취량 데이터 생성
    const intakeData = Object.keys(data.nutrients).map(nutrient => data.nutrients[nutrient]);
    // const intakeData = Object.keys(data.nutrients).map(nutrient => {
    //     return data.nutrients[nutrient] * (data.weight / data.servingSize);
    // });

    // 권장 섭취량 데이터 생성
    const maxIntakeData = Object.keys(data.nutrients).map(nutrient => recommendedIntake[nutrient]);

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: Object.keys(data.nutrients),
            datasets: [
                {
                    label: '섭취량',
                    data: intakeData,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1,
                    barThickness: 15, // 막대 두께 조정
                },
                {
                    label: '권장 섭취량',
                    data: maxIntakeData,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1,
                    barThickness: 15, // 막대 두께 조정
                }
            ]
        },
        options: {
            indexAxis: 'y',
            scales: {
                x: {
                    beginAtZero: true,
                    max: Math.max(...Object.values(recommendedIntake)) * 1.1 // 최대 권장 섭취량의 110%로 설정
                }
            }
        }
    });

    modal.modal('show');
}