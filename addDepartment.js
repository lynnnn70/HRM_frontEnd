//===== 取得元素 =====
let inputDeptName_el = $("#inputDeptName");
let inputDeptLoc_el = $("#inputDeptLoc");

//===== 錯誤處理 =====
let inputDeptName = $("#inputDeptName");
let inputDeptLoc = $("#inputDeptLoc");

//===== 判斷錯誤處理新增的文字 =====
let warnStr = $("<span>").css("color", "red");

//===== 初始化 =====
inputDeptName.html('');
inputDeptLoc.html('');



$(document).ready(function(){

    $("#deptSubmitBtn").on('click', function(e){

        //將form表單預設行為先關閉
        e.preventDefault();
        let control = true;

        //按下Btn時先做輸入格式等錯誤處理
        if(inputDeptName_el.val() === null || inputDeptName_el.val().trim() === ""){
            control = false;
            warnStr.html("請填寫部門名稱");
            return;
        }
        if(inputDeptLoc_el.val() === null || inputDeptLoc_el.val().trim() === ""){
            control = false;
            warnStr.html("請填寫部門地點");
            return;
        }

        //取得表單input資料
        let deptNameValue = inputDeptName_el.val();
        let deptLocValue = inputDeptLoc_el.val();

        let data = {
            deptName:deptNameValue,  //前 物件名稱 : 後 取到的值
            loc:deptLocValue
        }
  

        //使用AJAX發送請求
        $.ajax({
            url:'http://localhost:8080/api/addDepartment',
            method:'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response){
                //處理成功的回應
                console.log('資料送出成功', response);
            },
            error: function(error){
                //處理錯誤
                console.error('資料送出失敗:', error);
            }
        });
    });
});