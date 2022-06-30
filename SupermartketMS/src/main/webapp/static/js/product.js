/*<![CDATA[*/
window.onload=function(){
    let vue = new Vue({
        el:"#w2",
        data:{
            products:{},
            deleted:0
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
            addNewProduct:function(productId) {
                axios({
                    method:"POST",
                    url:"product.do",
                    params:{
                        operate:"addProduct",
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
}
function checkEmpty(name){
    var text=$(name).html();
    alert(text);
    if(text.value===""){
        alert("不能为空哦！");
    }else{
        vue.addNewProduct();
        alert("新产品添加成功！");
    }
}
/*]]>*/
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