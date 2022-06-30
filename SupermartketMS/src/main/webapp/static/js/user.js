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
        el:"#w2",
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
                    console.log(vue.users);
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
                    vue.getUsers();
                }).catch(function(error){

                });
            }
        },
        mounted:function(){
            this.getUsers();
            this.getRoles();
        }
    });
}
function checkEmpty(name){
    var text=$(name).html();
    alert(text);
    if(text.value===""){
        alert("不能为空哦！");
    }else{
        vue.addNewUser();
        alert("新用户添加成功！");
    }
}
//<![CDATA[
// 获取弹窗
var modal = document.getElementById('myModal');

// 打开弹窗的按钮对象
var btn = document.getElementById("addBtn");

// 获取 <span> 元素，用于关闭弹窗
var span = document.querySelector('.close');
var w1 = document.getElementById("w1");
var w2 = document.getElementById("w2");
var modal = document.getElementById("myModal");
var main = $("body *:not(.modal, .modal-content, .modal-content>object, .model-content>span)");

// 点击按钮打开弹窗
btn.onclick = function() {
    let hl = w1.children[0];
    let hr = w1.children[1];
    let cs = hr.children[0];
    let ab = hr.children[1];
    let button = ab.children[0];
    modal.style.display = "block";
    /*main.css("filter","blur(5px)");
    $(".close").css("color", "#aaa");*/
    w2.style.filter = "blur(5px)";
    hl.style.filter = "blur(5px)";
    cs.style.filter = "blur(5px)";
    button.style.filter = "blur(5px)";
}

// 点击 <span> (x), 关闭弹窗
span.onclick = function() {
    modal.style.display = "none";
    let hl = w1.children[0];
    let hr = w1.children[1];
    let cs = hr.children[0];
    let ab = hr.children[1];
    let button = ab.children[0];
    /*main.css("filter","blur(5px)");
    $(".close").css("color", "#aaa");*/
    w2.style.filter = "none";
    hl.style.filter = "none";
    cs.style.filter = "none";
    button.style.filter = "none";
}

// 在用户点击其他地方时，关闭弹窗
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
//]]>
