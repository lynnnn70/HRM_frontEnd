//===== 取得元素 =====
let inputDeptName_el = $("#inputDeptName");
let inputDeptLoc_el = $("#inputDeptLoc");

//===== 判斷錯誤 =====
function isValid(inputElement){
    if(inputElement.val() === null || inputElement.val().trim() === ""){
        return false;
    }
    return true;
}

//===== 判斷錯誤處理新增的文字 =====
function showWarnMsg(inputElement , message){
    $("<span>").html(message).css("color", "red").addClass("error_message").insertAfter(inputElement);
}



$(document).ready(function(){

    $("#deptSubmitBtn").on('click', function(e){

        //將form表單預設行為先關閉
        e.preventDefault();
        let control = true;

        //讓錯誤文字只顯示一次
        $(".error_message").remove();

        //按下Btn時先做輸入格式等錯誤處理
        if(!isValid(inputDeptName_el)|| !isValid(inputDeptLoc_el)){
            showWarnMsg(inputDeptName_el, "請填寫部門名稱");
            showWarnMsg(inputDeptLoc_el, "請填寫部門地點");
            control = false;
        }

        //取得表單input資料
        let deptNameValue = inputDeptName_el.val();
        let deptLocValue = inputDeptLoc_el.val();

        let data = {
            deptName:deptNameValue,  //前 物件名稱 : 後 取到的值
            loc:deptLocValue
        }
  
        if(control){
             //使用AJAX發送請求
            $.ajax({
                url:'http://localhost:8080/api/addDepartment',
                method:'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: function(response){
                    if(response.status === -6){
                        console.log("資料重複");
                        showWarnMsg(inputDeptName_el, "部門名稱重複");
                    }else if(response){
                        console.log("新增成功", response);
                        Swal.fire({
                            position: "center",
                            icon: "success",
                            title: "部門新增成功!",
                            showConfirmButton: false,
                            timer: 1500
                          });
                    }else{
                        console.log("新增失敗");
                    }
                },
                error: function(error){
                    //處理錯誤
                    console.error('資料送出失敗:', error);
                }
            }); 

        }

    });

});