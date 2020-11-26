package com.humuson.huboard.tensorflow.model;

import lombok.Getter;
import lombok.Setter;


//감지한 물체를 감싸주는 박스
@Getter @Setter
public class BoundingBox {
    private double x;
    private double y;
    private double width;
    private double height;
    private double confidence;
    private double[] classes;

}
