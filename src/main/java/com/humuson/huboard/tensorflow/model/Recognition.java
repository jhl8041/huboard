package com.humuson.huboard.tensorflow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//인식한 물체 및 신뢰도
@ToString
@AllArgsConstructor
@Getter @Setter
public final class Recognition {
    private final Integer id;
    private final String title;
    private final Float confidence;
    private BoxPosition location;

    public BoxPosition getScaledLocation(final float scaleX, final float scaleY) {
        return new BoxPosition(location, scaleX, scaleY);
    }

    public BoxPosition getLocation() {
        return new BoxPosition(location);
    }
}
