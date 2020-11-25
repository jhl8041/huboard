package com.humuson.huboard.tensorflow.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Model to store the data of a bounding box
 */

@Getter @Setter
public class BoundingBox {
    private double x;
    private double y;
    private double width;
    private double height;
    private double confidence;
    private double[] classes;

}
