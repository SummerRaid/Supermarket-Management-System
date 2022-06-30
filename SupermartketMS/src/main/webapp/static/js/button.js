/**
 * Copyright (c) 2008-2024: Shizhong Shang
 * Project: SupermartketMS
 * @className: button
 * @Description: TODO
 *
 * @author: SHIZHONG SHANG
 * @date: 2022/6/26 11:19
 *
 */
//<![CDATA[
// 获取弹窗
var modal = document.getElementById('myModal');

// 打开弹窗的按钮对象
var btn = document.getElementById("addBtn");

// 获取 <span> 元素，用于关闭弹窗
var span = document.querySelector('.close');
var w1 = document.getElementById("w1");
var w2 = document.getElementById("w2");

// 点击按钮打开弹窗
btn.onclick = function() {

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
//]]>