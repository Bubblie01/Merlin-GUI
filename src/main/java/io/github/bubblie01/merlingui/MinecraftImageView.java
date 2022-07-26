package io.github.bubblie01.merlingui;

import com.spinyowl.legui.component.Component;
import com.spinyowl.legui.image.Image;
import com.spinyowl.legui.theme.Themes;

import java.util.Objects;

public class MinecraftImageView extends Component {


    /**
     * BufferedImage (or image data).
     */
    private Image image;

    /**
     * Creates image view with specified image.
     *
     * @param image image to set.
     */
    public MinecraftImageView(Image image) {
        this.image = image;
        initialize();
    }


    /**
     * Default constructor of image view.
     */
    public MinecraftImageView() {
        initialize();
    }

    /**
     * This method used to initialize image view component.
     */
    private void initialize() {
        Themes.getDefaultTheme().getThemeManager().getComponentTheme(MinecraftImageView.class).applyAll(this);
    }

    /**
     * Returns image of image view.
     *
     * @return image of image view or null.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Used to set image.
     *
     * @param image image to set.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        MinecraftImageView that = (MinecraftImageView) o;
        return Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), image);
    }

    @Override
    public String toString() {
        return "MinecraftImageView[" +
            "image=" + image +
            ']';
    }
}
