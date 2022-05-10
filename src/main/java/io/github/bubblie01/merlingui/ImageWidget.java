package io.github.bubblie01.merlingui;

import com.spinyowl.legui.component.ImageView;
import com.spinyowl.legui.image.Image;


public class ImageWidget extends ImageView {

    private Image image;
    private int x;
    private int y;
    private int height;
    private int width;


    public ImageWidget(Image image, int x, int y, int height, int width) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        onInitialize();
    }

    private void onInitialize() {
        this.getStyle().setMinHeight(height);
        this.getStyle().setMinWidth(width);
        this.setPosition(x,y);
    }


}
