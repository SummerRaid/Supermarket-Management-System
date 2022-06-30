/**
 * Copyright (c) 2008-2024: Shizhong Shang
 * Project: SupermartketMS
 * @className: supplier
 * @Description: TODO
 *
 * @author: SHIZHONG SHANG
 * @date: 2022/6/29 14:46
 *
 */
window.onload=function(){
    let vue = new Vue({
        el:"#w2",
        data:{
            suppliers:{},
        },
        methods:{
            getSuppliers:function() {
                axios({
                    method:"POST",
                    url:"supplier.do",
                    params:{
                        operate:"getAllSuppliers"
                    }
                }).then(function(response){
                    let supplier = response.data;
                    vue.suppliers = supplier;
                    console.log(vue.suppliers);
                }).catch(function(error){

                });
            },
            deleteSupplier:function(supplierId) {
                axios({
                    method:"POST",
                    url:"supplier.do",
                    params:{
                        operate:"delSupplier",
                        supplierId: supplierId,
                    }
                }).then(function(response){
                    vue.getSuppliers();
                }).catch(function(error){

                });
            },
            editTheSupplier:function(supplierId) {
                axios({
                    method:"POST",
                    url:"supplier.do",
                    params:{
                        operate:"editSupplier",
                        supplierId:supplierId,
                    }
                }).then(function(response){
                    vue.getSuppliers();
                }).catch(function(error){

                });
            },
            addNewSupplier:function() {
                axios({
                    method:"POST",
                    url:"supplier.do",
                    params:{
                        operate:"addSupplier",
                    }
                }).then(function(response){
                }).catch(function(error){

                });
            },
        },
        mounted:function(){
            this.getSuppliers();
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
                    url:"supplier.do",
                    params:{
                        operate:"getSupplierByName",
                        name:name
                    }
                }).then(function(response){
                    let suppliers = response.data;
                    vue.suppliers = suppliers;
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
            sAddress:"",
            sName:"",
            sContactPerson:"",
            sContact:"",
            sRemark:"",
            sShop:"",
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
                    vue.addNewSupplier();
                    alert("新供应商添加成功");
                }
            }
        }
    });
}
