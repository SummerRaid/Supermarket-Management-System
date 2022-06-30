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
            addNewUser:function(userId){
                axios({
                    method:"POST",
                    url:"user.do",
                    params:{
                        operate:"register",
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
                    alert("新用户添加成功")
                }
            }
        },
    })
}
