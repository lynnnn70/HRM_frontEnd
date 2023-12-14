$(document).ready(function(){

    $("#deptSubmitBtn").click(function(){
        //取得表單input資料
        var inputDeptName_el = $("#inputDeptName").val();
        var inputDeptLoc_el = $("#inputDeptLoc").val();

        console.log("XXX");
        //使用AJAX發送請求
        $.ajax({
            url:'http://localhost:8080/api/addDepartment',
            method:'POST',
            data:{
                deptName: inputDeptName_el, //:後是從表單來的值
                loc: inputDeptLoc_el
            },
            success:function(response){
                //處理成功的回應
                console.log('資料送出成功',response);
            },
            error: function(error){
                //處理錯誤
                console.error('資料送出失敗:', error);
            }
        });
    });
});