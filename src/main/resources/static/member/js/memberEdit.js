/**
 * 
 */
 
 jQuery(document).ready(function($) {
 	$("#nav-placeholder").load("http://localhost:8080/navbar");
 
 	$("#id").blur(function(){$("#alertid").hide();});
	$("#pwd").blur(function(){$("#alertpwd").hide();});
	$("#pwd2").blur(function(){$("#alertpwd2").hide();});
	$("#nickname").blur(function(){$("#alertnickname").hide();});
	$("#email").blur(function(){$("#alertemail").hide();});
 });


// 비밀번호 형식 맞는지 체크
function pwdCheck(){
    var idStr = document.getElementById("id").value;
    var pwdStr = document.getElementById("pwd").value;
    $.ajax({
        url : "/pwdcheck",
        type : "POST",
        data : JSON.stringify({userId:idStr, password:pwdStr}),
        contentType: 'application/json',
        success : function(data){
        	function good(){
        		document.getElementById("alertpwd").className="alert alert-success";
                document.getElementById("pwd").className="form-control is-valid";
        	}
        	function bad(){
        		document.getElementById("alertpwd").className="alert alert-danger";
                document.getElementById("pwd").className="form-control is-invalid";
        	}
        	
            if(data == "empty"){
            	pwdgood=false;
                $("#pwdcheck").text("");
                $("#alertpwd").hide();
         		document.getElementById("pwd").className="form-control";
            }
            else{
            	pwdgood=false;
            	$("#alertpwd").show();
            	if (data == "digit"){
                	$("#pwdcheck").text("비밀번호는 최소 8자리여야 합니다");
                	bad();
                }
            	else if (data == "strength1"){
                	$("#pwdcheck").text("보안 1단계 (문자와 숫자를 섞어서 입력해주세요)");
                	bad();
            	}
            	else if (data == "strength2"){
	            	$("#pwdcheck").text("보안 2단계 (특수문자와 대문자를 포함해주세요)");
	            	bad();
	            }
	            else if (data == "strength3"){
	            	$("#pwdcheck").text("보안 3단계 (특수문자 또는 대문자를 포함해주세요)");
	            	bad();
	            }
	            else if (data == "strength4"){
	            	pwdgood=true;
	            	$("#pwdcheck").text("보안 4단계 (안전)");
	            	good();
	            }
	            else if (data == "notInclude"){
	            	$("#pwdcheck").text("특수문자,대소문자,숫자를 모두 포함시켜주세요");
	            	bad();
	            }
	            else if (data == "fourChar"){
	            	$("#pwdcheck").text("같은문자 4개를 포함할 수 없습니다");
	            	bad();
	            }
	            else if (data == "includeId"){
	            	$("#pwdcheck").text("비밀번호에 아이디를 포함할 수 없습니다");
	            	bad();
	            }
	            else if (data == "includeSpace"){
	            	$("#pwdcheck").text("비밀번호에 공백을 포함할 수 없습니다");
	            	bad();
	            }             
             }    
        }
    });
}

// 비밀번호 확인란 일치하는지 체크
function pwdMatchCheck(){
    var pwdStr = document.getElementById("pwd").value;
    var pwdStr2 = document.getElementById("pwd2").value;
    
    function good(){
		document.getElementById("alertpwd2").className="alert alert-success";
        document.getElementById("pwd2").className="form-control is-valid";
	}
	function bad(){
		document.getElementById("alertpwd2").className="alert alert-danger";
        document.getElementById("pwd2").className="form-control is-invalid";
	}
    
    if (pwdStr2 == ""){
    	pwd2good=false;
    	$("#pwdmatchcheck").text("");
    	$("#alertpwd2").hide();
		document.getElementById("pwd2").className="form-control";
    }
    else if (pwdStr2 == pwdStr){
    	$("#alertpwd2").show();
    	pwd2good=true;
    	$("#pwdmatchcheck").text("비밀번호 확인이 일치합니다");
    	good();
    } 	
    else{
    	$("#alertpwd2").show();
    	pwd2good=false;
    	$("#pwdmatchcheck").text("비밀번호 확인이 일치하지 않습니다");
    	bad();
    }
}



// 전화번호 형식 체크
function phoneCheck(){
	var phoneStr = document.getElementById("phone").value;
	if (isNaN(phoneStr)){
		alert("숫자만 입력해주세요");
		$("#phone").val("");
	}
}


// 회원가입 유효검사
var pwdgood = false;
var pwd2good = false;
var nicknamegood = false;
var emailCheckVal = false;
function formCheck(){
	var idStr = $("#id").val();
	var pwdStr = $("#pwd").val();
	var pwd2Str = $("#pwd2").val();
	var nameStr = $("#name").val();
	var nicknameStr = $("#nickname").val();
	var roadAddrPart1 = $("#roadAddrPart1").val();
	var addrDetail = $("#addrDetail").val();	
	
	if (pwdStr!="" && pwdgood==false){
		alert("비밀번호 보안을 확인해주세요");
		$("#pwd").focus();
		return false;
	}
	else if (pwd2Str!="" && pwd2good==false){
		alert("비밀번호 재입력을 확인해주세요");
		$("#pwd2").focus();
		return false;
	}
	else if (nameStr==""){
		alert("이름을 확인해주세요");
		$("#name").focus();
		return false;
	}
	else if (roadAddrPart1=="" || addrDetail==""){
		alert("주소를 확인해주세요");
		return false;
	}
}
