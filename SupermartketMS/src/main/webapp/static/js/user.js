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
            products:{}
        },
        methods:{
            getProducts:function() {
                axios({
                    method:"POST",
                    url:"user.do",
                    params:{
                        operate:"getAllProducts"
                    }
                }).then(function(response){
                    let product = response.data;
                    vue.products = product;
                    console.log(vue.products);

                }).catch(function(error){

                });
            },
            editCart:function(cartItemId, buyCount) {
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:"editCart",
                        cartItemId:cartItemId,
                        buyCount:buyCount
                    }
                }).then(function(response){
                    vue.getCart();
                }).catch(function(error){

                });
            }
        },
        mounted:function(){
            this.getProducts();
        }
    });
}
function checkEmpty(){
    var text=document.getElementById("pType");
    if(text.value===""){
        alert("不能为空哦！");
    }else{
        alert("新产品添加成功！");
    }
}