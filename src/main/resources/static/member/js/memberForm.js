/**
 * 
 */
 
//아이디 형식 맞는지 체크
function idCheck(){
    var idStr = document.getElementById("id").value;
    $.ajax({
        url : "/idcheck",
        type : "POST",
        data :  {userId:idStr},
        success : function(data){
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
        	
        }
    });
}