/**
 * 
 */
 
 jQuery(document).ready(function($) {
 	$("#nav-placeholder").load("http://localhost:8080/navbaradmin");
 	$("#footer-placeholder").load("http://localhost:8080/footer");
 	
 	memberLineChart();
 	genderPieChart();
 	viewBarChart();

 });
 
 function genderPieChart(){
 	var ctx = document.getElementById('memberGenderPieChart'); 
 	var labelArr = [];
 	var dataArr = [];
 
 	
 	$.ajax({
	        url : "/admin/genderpie",
	        type : "POST",
	        async: false,
	        contentType: 'application/json',
	        success : function(data){
	        	for (i=0;i<data.length;i++){
	        		labelArr.push(data[i].categoryName);
	        		dataArr.push(data[i].cntView);
	        	}
	        },
			error:function(xhr,status,error){
				console.log('error:'+error);
			}
	});
 	
	var myChart = new Chart(ctx, { 
		type: 'pie', 
		data: { 
			labels: labelArr, 
			datasets: [{ 
				label: '남여 비율', 
				data: dataArr, 
				backgroundColor: [
					"#4f81bb",
					"#5f497a"
				], 
			}] 
		}, 
		options: {
			pieceLabel: {
            	render: 'value'
         	}
		}
	});
 }
 
 function viewBarChart(){
 	var ctx = document.getElementById('viewBarChart'); 
 	var labelArr = [];
 	var dataArr = [];
 	
 	var bgColorList = ['rgba(255, 99, 132, 0.2)', 
					'rgba(54, 162, 235, 0.2)', 
					'rgba(255, 206, 86, 0.2)', 
					'rgba(75, 192, 192, 0.2)', 
					'rgba(153, 102, 255, 0.2)', 
					'rgba(255, 159, 64, 0.2)' ];
					
	var bcColorList = ['rgba(255, 99, 132, 1)', 
					'rgba(54, 162, 235, 1)', 
					'rgba(255, 206, 86, 1)', 
					'rgba(75, 192, 192, 1)', 
					'rgba(153, 102, 255, 1)', 
					'rgba(255, 159, 64, 1)' ];
	
 	var backgroundColorArr = [];
 	var borderColorArr = [];
 	
 	$.ajax({
	        url : "/admin/viewbar",
	        type : "POST",
	        async: false,
	        contentType: 'application/json',
	        success : function(data){
	        	for (i=0;i<data.length;i++){
	        		labelArr.push(data[i].categoryName);
	        		dataArr.push(data[i].cntView);
	        	}
	        },
			error:function(xhr,status,error){
				console.log('error:'+error);
			}
	});
 	
 	for (i=0;i<labelArr.length;i++){
 		backgroundColorArr.push(bgColorList[i%6]);
 		borderColorArr.push(bcColorList[i%6]);
 	}
 	
	var myChart = new Chart(ctx, { 
		type: 'bar', 
		data: { 
			labels: labelArr, 
			datasets: [{ 
				label: '조회수 통계', 
				data: dataArr, 
				backgroundColor: 'rgba(255, 99, 132, 0.2)', 
				borderColor: 'rgba(255, 99, 132, 1)', 
				borderWidth: 1 
			}] 
		}, 
		options: {
			scales: { 
				yAxes: [{ 
					ticks: { 
						beginAtZero: true 
					} 
				}] 
			} 
		} 
	});

 }
 
 function memberLineChart(){
 	var ctx = document.getElementById('memberCntLineChart'); 
 	var labelArr = [];
 	var dataArr = [];
 
 	
 	$.ajax({
	        url : "/admin/memberline",
	        type : "POST",
	        async: false,
	        contentType: 'application/json',
	        success : function(data){
	        	for (i=0;i<data.length;i++){
	        		labelArr.push(data[i].yearMonth);
	        		dataArr.push(data[i].cntMember);
	        	}
	        },
			error:function(xhr,status,error){
				console.log('error:'+error);
			}
	});
 	
	var myChart = new Chart(ctx, { 
		type: 'line', 
		data: { 
			labels: labelArr, 
			datasets: [{ 
				label: '회원수 통계', 
				data: dataArr, 
				backgroundColor: 'transparent', 
				borderColor: 'green',
				lineTension: 0.1
			}] 
		}, 
		options: {
			hover: {
	            mode: 'nearest',
	            intersect: true
	        },
	        scales: {
	            xAxes: [{
	                display: true,
	                scaleLabel: {
	                    display: true,
	                    labelString: '날짜(월)'
	                }
	            }],
	            yAxes: [{
	                display: true,
	                ticks: {
	                    suggestedMin: 0,
	                },
	                scaleLabel: {
	                    display: true,
	                    labelString: '회원수(명)'
	                }
	            }]
	        }
		} 
	});

 }
 
