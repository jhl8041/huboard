/**
 * 
 */
 
 //포커스 처리
$(function(){
	$("#id").blur(function(){$("#alertid").hide();});
	$("#pwd").blur(function(){$("#alertpwd").hide();});
	$("#pwd2").blur(function(){$("#alertpwd2").hide();});
	$("#nickname").blur(function(){$("#alertnickname").hide();});
	$("#email").blur(function(){$("#alertemail").hide();});
})

//아이디 형식 맞는지 체크
function idCheck(){
	console.log("id sending");
    var idStr = document.getElementById("id").value;
    $.ajax({
        url : "/idcheck",
        type : "POST",
        data : JSON.stringify({userId:idStr}),
        contentType: 'application/json',
        success : function(data){
        	console.log("id receiving");
        	function good(){
        		document.getElementById("alertid").className="alert alert-success";
                document.getElementById("id").className="form-control is-valid";
        	}
        	function bad(){
        		document.getElementById("alertid").className="alert alert-danger";
                document.getElementById("id").className="form-control is-invalid";
        	}
        	
        	if(data == "empty"){
        		idgood = false;
        		$("#idcheck").text("");
        		$("#alertid").hide();
        		document.getElementById("id").className="form-control";
        	}
        	else{
        		idgood = false;
        		$("#alertid").show();  		
        		if (data == "short"){	
            		$("#idcheck").text("아이디는 6자리이상이어야 합니다");
            		bad();        		
            	}
            	else if (data == "wrongChar"){
            		$("#idcheck").text("아이디는 영문소문자와 숫자만 쓸 수 있습니다");
            		bad();
            	}  	
            	else if(data == "unique"){
            		idgood = true;
                    $("#idcheck").text("사용가능한 아이디입니다");
                    good();             
                }
                else if(data == "notunique"){
                    $("#idcheck").text("이미 등록된 아이디입니다");
                    bad();
                }
        	}       	
        },
		error:function(xhr,status,error){
			console.log('error:'+error);
		}
    });
}

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

//닉네임 중복검사
function nicknameCheck(){
    var nicknameStr = document.getElementById("nickname").value;
    $.ajax({
        url : "/nicknamecheck",
        type : "POST",
        data : JSON.stringify({nickname:nicknameStr}),
        contentType: 'application/json',
        success : function(data){
        	function good(){
        		document.getElementById("alertnickname").className="alert alert-success";
                document.getElementById("nickname").className="form-control is-valid";
        	}
        	function bad(){
        		document.getElementById("alertnickname").className="alert alert-danger";
                document.getElementById("nickname").className="form-control is-invalid";
        	}
        	
        	if(data == "empty"){
        		nicknamegood = false;
        		$("#nicknamecheck").text("");
        		$("#alertnickname").hide();
        		document.getElementById("nickname").className="form-control";
        	}
        	else{
        		nicknamegood = false;
        		$("#alertnickname").show();
            	if(data == "unique"){
            		nicknamegood = true;
                    $("#nicknamecheck").text("사용가능한 닉네임입니다");
                    good();             
                }
                else if(data == "notunique"){
                    $("#nicknamecheck").text("이미 사용중인 닉네임입니다");
                    bad();
                }
        	}
        	
        }
    });
}

// 주소 API
function goPopup(){
    var pop = window.open("/addressPop","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}

function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn
						, detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){
	document.form.roadAddrPart1.value = roadAddrPart1;
	document.form.addrDetail.value = addrDetail;
}

// 전화번호 형식 체크
function phoneCheck(){
	var phoneStr = document.getElementById("phone").value;
	if (isNaN(phoneStr)){
		alert("숫자만 입력해주세요");
		$("#phone").val("");
	}
}

//이메일 중복검사
function emailCheck(){
    var emailStr = document.getElementById("email").value;
    
    $.ajax({
        url : "/emailcheck",
        type : "POST",
        data : JSON.stringify({email:emailStr}),
        contentType: 'application/json',
        success : function(data){
        	function good(){
        		document.getElementById("alertemail").className="alert alert-success";
                document.getElementById("email").className="form-control is-valid";;
                $("#emailbutton").prop("disabled", false);
        	}
        	function bad(){
        		document.getElementById("alertemail").className="alert alert-danger";
                document.getElementById("email").className="form-control is-invalid";
                $("#emailbutton").prop("disabled", true);
        	}
        	
        	if(data == "empty"){
        		$("#emailcheck").text("");
        		$("#alertemail").hide();
        		document.getElementById("email").className="form-control";
        		$("#emailbutton").prop("disabled", true);
        	}
        	else{
        		$("#alertemail").show();
            	if(data == "unique"){
                    $("#emailcheck").text("사용가능한 이메일입니다");
                    good();             
                }
                else if(data == "notunique"){
                    $("#emailcheck").text("이미 등록된 이메일입니다");
                    bad();
                }
        	}
        
        }
     });
}

// 인증이메일 보내기
var auth;
function sendCode(){
	var emailStr = document.getElementById("email").value;
	var nicknameStr = document.getElementById("nickname").value;		
	// 6자리 인증번호 생성
	auth = Math.floor(Math.random() * 1000000)+100000;
	if(auth>1000000)
		auth = auth - 100000;
	$.ajax({
        url : "/doSendEmail",
        type : "POST",
 		data : JSON.stringify({email:emailStr, code:auth, name:nicknameStr}),
        contentType: 'application/json',
        success : function(data){
        	if (data=="success"){
        		alert("인증코드가 전송되었습니다 최대 2-3분 정도소요될 수 있습니다");
            	codeTimer();
        	}
        	else{
        		alert("이메일주소를 확인해주세요");
        	}
        }
    });
}

// 인증 타이머
var x;
function codeTimer(){
	$("#codetimer").text("");
	clearInterval(x);
	var time = 50;
	var min = "";
	var sec = "";
	$("#alertcode").show();
	x = setInterval(function(){
		min = parseInt(time/60);
		sec = time%60;
		$("#codetimer").text("남은 시간: " + min+":"+(sec<10?'0':'')+sec);
		time--;
		
		if(time<0){
			clearInterval(x);
			$("#codetimer").text("시간초과");
			auth="invalid";
		}	
	},1000);
}

//인증코드 체크
function codeCheck(){
	var tempcodeStr = auth;
	var codeStr = document.getElementById("code").value;
	
	if (codeStr == tempcodeStr){
		emailCheckVal=true;
		clearInterval(x);
		$("#alertcode").hide();
        document.getElementById("code").className="form-control is-valid";
	}
	else{
		emailCheckVal=false;
        document.getElementById("code").className="form-control is-invalid";
	}
}

// 회원가입 유효검사
var idgood = false;
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
	
	if (idStr=="" || idgood==false){
		alert("아이디를 확인해주세요");
		$("#id").focus();
		return false;
	}
	else if (pwdStr=="" || pwdgood==false){
		alert("비밀번호 보안을 확인해주세요");
		$("#pwd").focus();
		return false;
	}
	else if (pwd2Str=="" || pwd2good==false){
		alert("비밀번호 재입력을 확인해주세요");
		$("#pwd2").focus();
		return false;
	}
	else if (nameStr==""){
		alert("이름을 확인해주세요");
		$("#name").focus();
		return false;
	}
	else if (nicknameStr=="" || nicknamegood==false){
		alert("닉네임을 확인해주세요");
		$("#nickname").focus();
		return false;
	}
	else if (roadAddrPart1=="" || addrDetail==""){
		alert("주소를 확인해주세요");
		return false;
	}
	else if (emailCheckVal==false){
		alert("이메일 인증 후 회원가입 가능합니다");
		$("#code").focus();
		return false;
	}
}

/**
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(header, token); 
});*/