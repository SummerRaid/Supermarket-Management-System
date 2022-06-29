/**
 * Copyright (c) 2008-2024: Shizhong Shang
 * Project: SupermartketMS
 * @className: user
 * @Description: TODO
 *
 * @author: SHIZHONG SHANG
 * @date: 2022/6/29 12:00
 *
 */
window.onload=function(){
    let vue = new Vue({
        el:"#t1",
        data:{
            users:{},
        },
        methods:{
            getUsers:function() {
                axios({
                    method:"POST",
                    url:"user.do",
                    params:{
                        operate:"getAllUser"
                    }
                }).then(function(response){
                    let user = response.data;
                    vue.users = user;
                    console.log(vue.users);
                }).catch(function(error){

                });
            },
        },
        mounted:function(){
            this.getUsers();
        }
    });
}
function checkEmpty(name){
    var text=$(name).html();
    alert(text);
    if(text.value===""){
        alert("不能为空哦！");
    }else{
        alert("新用户添加成功！");
    }
}