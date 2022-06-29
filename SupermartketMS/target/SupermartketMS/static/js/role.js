/**
 * Copyright (c) 2008-2024: Shizhong Shang
 * Project: SupermartketMS
 * @className: role
 * @Description: TODO
 *
 * @author: SHIZHONG SHANG
 * @date: 2022/6/29 14:32
 *
 */
window.onload=function(){
    let vue = new Vue({
        el:"#w2",
        data:{
            roles:{},
        },
        methods:{
            getRoles:function() {
                axios({
                    method:"POST",
                    url:"role.do",
                    params:{
                        operate:"getAllRoles"
                    }
                }).then(function(response){
                    let role = response.data;
                    vue.roles = role;
                    console.log(vue.roles);
                }).catch(function(error){

                });
            },
            deleteRole:function(roleId) {
                axios({
                    method:"POST",
                    url:"role.do",
                    params:{
                        operate:"delRole",
                        roleId: roleId,
                    }
                }).then(function(response){
                    let role = response.data;
                    vue.roles = role;
                    console.log(vue.roles);
                }).catch(function(error){

                });
            },
            editTheRole:function(roleId) {
                axios({
                    method:"POST",
                    url:"role.do",
                    params:{
                        operate:"editRole",
                        roleId: roleId,
                    }
                }).then(function(response){
                    let role = response.data;
                    vue.roles = role;
                    console.log(vue.roles);
                }).catch(function(error){

                });
            },
        },
        mounted:function(){
            this.getRoles();
        }
    });
}
function checkEmpty(name) {
    var text = $(name).html();
    alert(text);
    if (text.value === "") {
        alert("不能为空哦！");
    } else {
        alert("新角色添加成功！");
    }
}