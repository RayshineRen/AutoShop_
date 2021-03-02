'use strict';
// alert("Welcome!");
let score = 123;
let asd = "asd";
if(asd == "asd"){
    console.log(score);
}
let array = new Array('asd', 213, true, null, 'asdasqwe');
let name = "ray";
console.log(`Hello, ${name}`);
let person = {
    name:"asd",
    age:123
};
let map = new Map([["asd", 123], ['ret', 12312], ['qwe', 123124]]);
map.set('admin', 15213);
let nam2 = map.get("asd");
console.log(nam2);
let set = new Set([1,1,1,3,4,5,2]);
//>Set(5) {1, 3, 4, 5, 2}
for(var awsd of map){
    console.log(awsd);
}
// for(var awsd of array){
//     console.log(awsd);
// }

var abs = function (x) {
    if(typeof x !== 'number'){
        throw 'Not a Number';
    }
    else{
        console.log("this is abs function");
    }
    return 1;
}

var abs = function (x) {
    if(typeof x !== 'number'){
        throw 'Not a Number';
    }
    else{
        console.log("this is abs function");
    }
    console.log(x);
    for(var i of arguments){
        console.log("i "+i);
    }
    return 0;
}

var abs = function (a,b,...rest) {
    if(typeof a !== 'number'){
        throw 'Not a Number';
    }
    else{
        console.log("this is abs function");
    }
    console.log(a);
    console.log(rest);

}

var outer = function () {
    var x = 1;
    var inner = function () {
        var y = x+1;
        console.log(y);
    }
    var z = y+1;
}

// alert(window.array);

// var old_alert = window.alert;
// old_alert("old");
// var alert = function (x) {
//     console.log("new alert");
// }

var RaySpace = {};
RaySpace.name = "Ray";
RaySpace.add = function (a, b) {
    return a+b;
}

var f1 = function () {
    for (var i = 0;i<100;i++){
        console.log(i);
    }
    console.log(i);
}
var f2 = function () {
    for (let i = 0;i<100;i++){
        console.log(i);
    }
    console.log(i);
}

var test = {
    name: "asd",
    birth: 2000,
    age: function () {
        let fullYear = new Date().getFullYear();
        return fullYear - this.birth;
    }
}

let date = new Date();

let s = JSON.stringify(person);
let parse = JSON.parse(s);

var Stu = {
    name: "default",
    age : 213,
    run : function () {
        console.log(this.name + "is running...");
    }
};

let xiaoming = {
    name: "xiaoming"
};
xiaoming.__proto__ = Stu;

// function Student(name) {
//     this.name = name;
// }
//
// Student.prototype.Hello = function () {
//     alert("Hello");
// }

class Student{
    constructor(name){
        this.name = name;
    }
    hello(){
        alert("Hello");
    }
}

let asdqwe = new Student("asdasd");

class Pupil extends Student{
    constructor(name, grade){
        super(name);
        this.grade = grade;
    }
    mygrade(){
        alert("I'm pupil");
    }
}

let qweasd = new Pupil("xiaopeng", 5);


