package com.humuson.huboard.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.humuson.huboard.model.BoardVo;

import org.tensorflow.ConcreteFunction;
import org.tensorflow.Signature;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.types.TInt32;

@Controller
public class LabController {
	//게시글 목록 조회 - R
	@GetMapping("/lab")
	public String goLab() { 
		return "lab/lab";
	}
	
	private static Signature dbl(Ops tf) {
	    Placeholder<TInt32> x = tf.placeholder(TInt32.DTYPE);
	    Add<TInt32> dblX = tf.math.add(x, x);
	    return Signature.builder().input("x", x).output("dbl", dblX).build();
	 }
	
	@GetMapping("/labtest")
	public String testLab() throws Exception{
		System.out.println("Hello TensorFlow " + TensorFlow.version());

	    try (ConcreteFunction dbl = ConcreteFunction.create(LabController::dbl);
	        Tensor<TInt32> x = TInt32.scalarOf(10);
	        Tensor<TInt32> dblX = dbl.call(x).expect(TInt32.DTYPE)) {
	    	System.out.println(x.data().getInt() + " doubled is " + dblX.data().getInt());
	    }
	    return "lab/lab";
	}
}
	  
	  
