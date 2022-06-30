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
                    vue.getRoles();
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
                    vue.getRoles();
                }).catch(function(error){

                });
            },
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
            this.getRoles();
        }
    });
    let w1 = new Vue({
        el:"#w1",
        data:{
        },
        methods:{
            openWindow:function (){
                // 获取弹窗
                let modal = document.getElementById('myModal');
                modal.style.display = "block";
            }
        }
    });
    let myModal = new Vue({
        el:"#myModal",
        data:{
            rName:"",
            rPermission:"",
            rRemark:"",
        },
        methods:{
            closeWindow:function () {
                // 获取弹窗
                let modal = document.getElementById('myModal');
                modal.style.display = "none";
            },
            checkEmpty: function(){
                let count = 0;
                for(let i=0; i<data.size(); i++){
                    if(data[i]===""){
                        alert(i+"不能为空");
                        count++;
                    }
                }
                if(count!==0){
                    vue.addNewRole();
                    alert("新角色添加成功");
                }
            }
        }
    });
};
