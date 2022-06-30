/**
 * Copyright (c) 2008-2024: Shizhong Shang
 * Project: SupermartketMS
 * @className: deletedProduct
 * @Description: TODO
 *
 * @author: SHIZHONG SHANG
 * @date: 2022/6/30 11:20
 *
 */
window.onload=function(){
    let vue = new Vue({
        el:"#w2",
        data:{
            products:{},
            deleted:1
        },
        methods:{
            getProducts:function() {
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"getAllProducts"
                    }
                }).then(function(response){

                }).catch(function(error){

                });
            },
            deleteProduct:function(productId) {
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"delProduct",
                        productId:productId,
                    }
                }).then(function(response){
                    vue.getProducts();
                }).catch(function(error){

                });
            },
            recoverTheProduct:function(productId) {
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"recoverProduct",
                        productId:productId,
                    }
                }).then(function(response){
                    vue.getProducts();
                }).catch(function(error){

                });
            },
        },
        mounted:function(){
            this.getProducts();
        },
    });
}