("p")  //p標籤
("#p") //id為p的元素
(".p") //class為p的元素


$("select[name='deptId_select']").val();

// DOM 載入完成之後
document.addEventListener("DOMContentLoaded", function(){
});

$(document).ready(function(){
});

$(function(){
});

// 圖片等資源載入完成之後
window.addEventListener("load", function(){
})

$(window).on("load", function(){
})

//click事件
$("selcet[name='deptId_select']").on(click,function(){

})

//點擊id為search_button時觸發
$("#search_button").on("click", function(e){

});

//停止預設行為
e.preventDefault();

//停止連結的預設行為
//連結的預設行為是是將連結開啟。可以透過以下程式來停止：
<a href="https://www.google.com.tw" id="the_link" target="_blank">開啟分頁連到 google</a>
$(function(){
    $("#the_link").on("click", function(e){
        e.preventDefault();
        alert("提示訊息")
    });
})

//表單送出事件
// 如果沒有填寫資料，表單不能送出，然後用 alert() 顯示「請輸入資料」
// 如果有填寫資料，就表單按鈕原來預設的形式將資料送出。
$("#empForm").on("submit", function(){

    if(("#empForm").val() ==""){
        e.preventDefault();
        alert("請輸入資料");
    }

    $("#empForm").on("keydown", function(e){

        if(e.which >= 48 && e.which <=57 || e.which == 8){
    
        }else{
            e.preventDefault();
        }
    });

});

//部門編號選擇後響應
$("#deptId_selectList")("change", function(){
    deptIdSelect = this.value;
    $("#")
})

//生成部門選單
function generateEmpSelect(){
    let htmlTemplate = "";  //宣告一個變數 htmlTemplate，用來存儲生成的 HTML 代碼


}

// 使用 AJAX 請求從伺服器獲取資料
$.ajax({
    url: 'your_backend_endpoint', // 將 'your_backend_endpoint' 替換為實際的後端端點
    method: 'GET', // 或 'POST'，取決於你的後端實現
    dataType: 'json', // 預期的資料類型，這裡假設資料是 JSON 格式
    success: function (data) {
        // 成功請求後的處理
        deptSelectList(data);
    },
    error: function (error) {
        // 請求失敗時的處理
        console.error('Error fetching data:', error);
    }
});

function deptSelectList(data) {
    // 清空選擇列表
    $('#deptId_selectList').empty();

    // 添加新的選項
    $('#deptId_selectList').append('<option selected>選擇部門</option>');

    // 迭代資料並將每個元素添加為選項
    data.forEach(function (item) {
        $('#deptId_selectList').append('<option value="' + item.value + '">' + item.text + '</option>');
    });
}