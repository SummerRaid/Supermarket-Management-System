/**
 * Copyright (c) 2008-2024: Shizhong Shang
 * Project: SupermartketMS
 * @className: addRole
 * @Description: TODO
 *
 * @author: SHIZHONG SHANG
 * @date: 2022/6/30 12:58
 *
 */
window.onload=function(){
    let vue = new Vue({
        el:".addR",
        data:{
            roles:{},
        },
        methods:{
            addNewRole:function(roleId) {
                axios({
                    method:"POST",
                    url:"role.do",
                    params:{
                        operate:"addRole",
                    }
                }).then(function(response){

                }).catch(function(error){

                });
            },
        },
        mounted:function(){
        }
    });
}
function check(){
    if (checkEmpty(name)==true){
        //vue.addNewRole();
    }
}
function checkEmpty(name) {
    var text = $(name).html();
    alert(text);
    if (text.value === "") {
        alert("不能为空哦！");
        return false;
    } else {
        //vue.addNewRole();
        alert("");
        return true;
    }
}