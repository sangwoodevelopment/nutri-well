<!-- FullCalendar Initialization -->
document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        events: '/calendar/api/calendars', // URL to fetch events
        displayEventTime: false,
        eventDisplay: 'block'
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