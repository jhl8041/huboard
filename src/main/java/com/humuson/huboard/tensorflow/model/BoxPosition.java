package com.humuson.huboard.tensorflow.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model to store the position of the bounding boxes
 */
@ToString
@Getter
public class BoxPosition {
    private float left;
    private float top;
    private float right;
    private float bottom;
    private float width;
    private float height;

    public BoxPosition(float left, float top, float width, float height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;

        init();
    }

    public BoxPosition(final BoxPosition boxPosition) {
        this.left = boxPosition.left;
        this.top = boxPosition.top;
        this.width = boxPosition.width;
        this.height = boxPosition.height;

        init();
    }

    public BoxPosition(final BoxPosition boxPosition, final float scaleX, final float scaleY) {
        this.left = boxPosition.left * scaleX;
        this.top = boxPosition.top * scaleY;
        this.width = boxPosition.width * scaleX;
        this.height = boxPosition.height * scaleY;

        init();
    }

    public void init() {
        float tmpLeft = this.left;
        float tmpTop = this.top;
        float tmpRight = this.left + this.width;
        float tmpBottom = this.top + this.height;

        this.left = Math.min(tmpLeft, tmpRight); // left should have lower value as right
        this.top = Math.min(tmpTop, tmpBottom);  // top should have lower value as bottom
        this.right = Math.max(tmpLeft, tmpRight);
        this.bottom = Math.max(tmpTop, tmpBottom);
    }


    public int getLeftInt() {
        return (int) left;
    }

    public int getTopInt() {
        return (int) top;
    }

    public int getWidthInt() {
        return (int) width;
    }

    public int getHeightInt() {
        return (int) height;
    }

    public int getRightInt() {
        return (int) right;
    }

    public int getBottomInt() {
        return (int) bottom;
    }

}
