document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('button.approve-btn').forEach(button => {
        button.addEventListener('click', () => approveFood(button, button.dataset.id));
    });
});

function approveFood(button, id) {
    if (confirm("승인하시겠습니까?")) {
        $.ajax({
            url: `/food-approve/approve/${id}`,
            type: 'GET',
            contentType: 'application/json',
            success: function(data) {
                if (data.success) {
                    alert("승인완료");
                    button.closest('td').innerHTML = '승인됨';
                } else {
                    alert("승인에 실패했습니다.");
                }
            },
            error: function(error) {
                console.error("Error:", error);
                alert("승인에 실패했습니다.");
            }
        });
    }
}