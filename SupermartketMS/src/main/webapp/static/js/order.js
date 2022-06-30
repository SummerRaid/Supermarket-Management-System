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
            suppliers:{},
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
            payTheOrder:function(orderId) {
                axios({
                    method:"POST",
                    url:"order.do",
                    params:{
                        operate:"payOrder",
                        orderId: orderId,
                    }
                }).then(function(response){
                    vue.getOrders();
                }).catch(function(error){

                });
            },
            addNewOrder :function() {
                axios({
                    method:"POST",
                    url:"order.do",
                    params:{
                        operate:"addOrder",
                        productId:myModal.pName,
                        supplierId:myModal.sName,
                        remark:myModal.oRemark,
                        amount:myModal.oAmount,
                        price:myModal.oPrice,

                    }
                }).then(function(response){
                }).catch(function(error){

                });
            },
        },
        mounted:function(){
            this.getOrders();
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
                    url:"order.do",
                    params:{
                        operate:"searchOrders",
                        name:name
                    }
                }).then(function(response){
                    let order = response.data;
                    vue.orders = order;
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
        el:"#myModal",
        data:{
            pName:"",
            orderNO:"",
            sName:"",
            uName:"",
            oPayMoney:"",
            oPayDate:"",
            oStatus:"",
            oCreatDate:"",
            oRemark:"",
            oAmount:"",
            oPrice:"",
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
                    if(i==="oPrice") {
                        const reg = /^(0|[1-9][0-9]*)(\.\d+)?$/;
                        if(!reg.test(datum)) {
                            count++;
                            alert(i + "只能是正数");
                        }
                    }
                    if(i==="oPrice") {
                        const reg = /^[1-9]\d*$/;
                        if(!reg.test(datum)) {
                            count++;
                            alert(i + "只能是正整数");
                        }
                    }
                }
                if(count!==0){
                    vue.addNewOrder();
                    alert("新订单添加成功");
                }
            }
        }
    });
}
