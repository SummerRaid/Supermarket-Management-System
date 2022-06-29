/**
 * Copyright (c) 2008-2024: Shizhong Shang
 * Project: SupermartketMS
 * @className: order
 * @Description: TODO
 *
 * @author: SHIZHONG SHANG
 * @date: 2022/6/29 14:41
 *
 */
window.onload=function(){
    let vue = new Vue({
        el:"#w2",
        data:{
            orders:{},
        },
        methods:{
            getOrders:function() {
                axios({
                    method:"POST",
                    url:"order.do",
                    params:{
                        operate:"getAllOrders"
                    }
                }).then(function(response){
                    let order = response.data;
                    vue.orders = order;
                    console.log(vue.orders);
                }).catch(function(error){

                });
            },
            deleteOrder:function(orderId) {
                axios({
                    method:"POST",
                    url:"order.do",
                    params:{
                        operate:"cancelOrder",
                        orderId: orderId,
                    }
                }).then(function(response){
                    vue.getOrders();
                }).catch(function(error){

                });
            },
            editTheOrder:function(orderId) {
                axios({
                    method:"POST",
                    url:"order.do",
                    params:{
                        operate:"editOrder",
                        orderId: orderId,
                    }
                }).then(function(response){
                    let order = response.data;
                    vue.orders = order;
                    console.log(vue.orders);
                }).catch(function(error){

                });
            },
        },
        mounted:function(){
            this.getOrders();
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