package com.humuson.huboard.tensorflow.util.math;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ArgMax function to select the higher value and its index from the array.
 *
 * Created by Zoltan Szabo on 1/5/18.
 * URL: https://github.com/szaza/android-yolo-v2
 */
public class ArgMax {

    private double[] params;

    public ArgMax(double[] params) {
        this.params = params;
    }

    public Result getResult() {
        int maxIndex = 0;
        for (int i=0; i<params.length; i++) {
            if (params[maxIndex] < params[i]) {
                maxIndex = i;
            }
        }

        return new Result(maxIndex, params[maxIndex]);
    }
    
    @Getter @AllArgsConstructor
    public class Result {
        private int index;
        private double maxValue;
    }
}
