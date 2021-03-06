/*<![CDATA[*/
window.onload=function(){
    let vue = new Vue({
        el: "#w2",
        data:{
            products:{},
            deleted:0,
            types:{},
            selectType:""
        },
        methods:{
            getTypes:function(){
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"getAllTypes"
                    }
                }).then(function(response){
                    let type = response.data;
                    vue.types = type;
                }).catch(function(error){

                });
            },
            getProducts:function() {
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"getAllProducts"
                    }
                }).then(function(response){
                    vue.getTypes();
                    let product = response.data;
                    vue.products = product;
                    if(product[0].deleted === 1) {
                        vue.deleted = 1;
                    }
                }).catch(function(error){

                });
            },
            editPrice:function(id) {
                let price = event.srcElement.value;
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"editPrice",
                        productId:id,
                        price:price
                    }
                }).then(function(response){
                    vue.getProducts();
                }).catch(function(error){

                });
            },
            editAmount:function(id) {
                let amount = event.srcElement.value;
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"saleProduct",
                        productId:id,
                        amount:amount
                    }
                }).then(function(response){
                    vue.getProducts();
                }).catch(function(error){

                });
            },
            productToBin:function(productId) {
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"putToBin",
                        productId:productId,
                    }
                }).then(function(response){
                    vue.getProducts();
                }).catch(function(error){

                });
            },
            editTheProduct:function(productId) {
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"editProduct",
                        productId:productId,
                    }
                }).then(function(response){
                    vue.getProducts();
                }).catch(function(error){

                });
            },
            selectT:function(type){
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"getProductsByType",
                        type:type
                    }
                }).then(function(response){
                    let product = response.data;
                    vue.products = product;
                    if(product[0].deleted === 1) {
                        vue.deleted = 1;
                    }
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
            addNewProduct:function() {
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"addProduct",
                        type: myModal.pType,
                        name: myModal.pName,
                        unit: myModal.pUnit,
                        remark: myModal.pRemark,
                        salePrice: myModal.pPrice
                    }
                }).then(function(response){
                }).catch(function(error){
                });
            },
        },
        watch:{
            selectType:function (newval, oldval) {
                if(newval === "all") {
                    vue.getProducts();
                }else if(newval !== oldval) {
                    vue.selectT(newval);
                }
            }
        },
        mounted:function(){
            this.getProducts();
        },
        updated:function(){
            let btn = document.getElementById("add-btn");
            console.log("btn style" + btn.style);
            if(vue.deleted === 1) {
                btn.style.display = "none";
            } else {
                btn.style.display = "block";
            }
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
                    url:"product.do",
                    params:{
                        operate:"getProductsByName",
                        pName:name
                    }
                }).then(function(response){
                    let products = response.data;
                    vue.products = products;
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
                // ????????????
                let modal = document.getElementById('myModal');
                modal.style.display = "block";
            }
        }
    });
    let myModal = new Vue({
        el:"#myModal",
        data:{
            pName:"",
            pType:"",
            pUnit:"",
            pPrice:"",
            pRemark:""
        },
        methods:{
            closeWindow:function () {
                // ????????????
                let modal = document.getElementById('myModal');
                modal.style.display = "none";
            },
            checkTEmpty: function(){
                let count = 0;
                let data = myModal.$data;
                for(let i in data){
                    let datum = data[i];
                    if(datum==="" || datum===undefined){
                        count++;
                        alert(i + "????????????");
                    }
                    if(i==="pPrice") {
                        const reg = /^(0|[1-9][0-9]*)(\.\d+)?$/;
                        if(!reg.test(datum)) {
                            count++;
                            alert(i + "???????????????");
                        }
                    }
                }
                if(count===0){
                    vue.addNewProduct();
                    alert("?????????????????????");
                }
            }
        },
        watch:{
            pName:function (newVal, oldVal) {
                console.log("new: " + newVal);
                console.log("old: " + oldVal);
            }
        }
    });
}
