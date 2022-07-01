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
/*<![CDATA[*/
window.onload=function(){
    let vue = new Vue({
        el: "#w2",
        data:{
            users:{},
            roles:{}
        },
        methods:{
            getUsers:function() {
                axios({
                    method:"POST",
                    url:"user.do",
                    params:{
                        operate:"getAllUsers"
                    }
                }).then(function(response){
                    let user = response.data;
                    vue.users = user;
                }).catch(function(error){

                });
            },
            getRoles:function(){
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
            deleteUser:function(userId){
                axios({
                    method:"POST",
                    url:"user.do",
                    params:{
                        operate:"delUser",
                        userId: userId,
                    }
                }).then(function(response){
                    vue.getUsers();
                }).catch(function(error){

                });
            },
            editTheUser:function(userId){
                axios({
                    method:"POST",
                    url:"user.do",
                    params:{
                        operate:"editUser",
                        userId:userId,
                    }
                }).then(function(response){
                    vue.getUsers();
                }).catch(function(error){

                });
            },
            addNewUser:function(){
                axios({
                    method:"POST",
                    url:"user.do",
                    params:{
                        operate:"register",
                        verifyCode:"",
                        roleId:myModal.uRole,
                        uname:myModal.uName,
                        pwd:myModal.uPwd,
                        tname:myModal.uTName,
                        tel:myModal.uPhone,
                        address:myModal.uAddress,
                    }
                }).then(function(response){
                }).catch(function(error){

                });
            }
        },
        mounted:function(){
            this.getUsers();
            this.getRoles();
        }
    });
    let w1 = new Vue({
        el:"#w1",
        data:{
        },
        methods:{
            searchP:function(name) {
                axios({
                    method:"POST",
                    url:"user.do",
                    params:{
                        operate:"getUsersByName",
                        name:name
                    }
                }).then(function(response){
                    let user = response.data;
                    vue.users = user;
                }).catch(function(error){

                });
            },
            checkEmpty:function() {
                let search = document.getElementById("search").value;
                if(search != null && search !== "") {
                    w1.searchP(search);
                }
            },
            openWindow:function (){
                // 获取弹窗
                let modal = document.getElementById('myModal');
                modal.style.display = "block";
            }
        }
    });
    let myModal = new Vue({
        el: "#myModal",
        data:{
            uName:"",
            uPwd:"",
            uTName:"",
            uRole:"",
            uPhone:"",
            uAddress:"",
        },
        method:{
            closeWindow:function () {
                // 获取弹窗
                let modal = document.getElementById('myModal');
                modal.style.display = "none";
            },
            checkEmpty: function(){
                let count = 0;
                for(let i=0; i<data.size();i++){
                    if(data[i]===""){
                        alert(i+"不能为空");
                        count++;
                    }
                }
                if(count!==0){
                    vue.addNewUser();
                    alert("新用户添加成功");
                }
            }
        },
    })
}
/*]]>*/