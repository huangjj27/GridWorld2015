import imagereader.IImageProcessor;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class MyImageProcessor implements IImageProcessor {
  // use integer to Represent the Color
  private static int red = 1;
  private static int green = 2;
  private static int blue = 3;
  private static int gray = 0;

  @Override
  public Image showChanelR(Image sourceImage) {
    ColorFilter redFilter = new ColorFilter(red);
    Toolkit toolKit = Toolkit.getDefaultToolkit();
    Image img = toolKit.createImage(new FilteredImageSource(sourceImage
            .getSource(), redFilter));
    return img;
  }

  @Override
  public Image showChanelG(Image sourceImage) {
    ColorFilter greenFilter = new ColorFilter(green);
    Toolkit toolKit = Toolkit.getDefaultToolkit();
    Image img = toolKit.createImage(new FilteredImageSource(sourceImage
            .getSource(), greenFilter));
    return img;
  }

  @Override
  public Image showChanelB(Image sourceImage) {
    ColorFilter blueFilter = new ColorFilter(blue);
    Toolkit toolKit = Toolkit.getDefaultToolkit();
    Image img = toolKit.createImage(new FilteredImageSource(sourceImage
            .getSource(), blueFilter));
    return img;
  }

  @Override
  public Image showGray(Image sourceImage) {
    ColorFilter grayFilter = new ColorFilter(gray);
    Toolkit toolKit = Toolkit.getDefaultToolkit();
    Image img = toolKit.createImage(new FilteredImageSource(sourceImage
            .getSource(), grayFilter));
    return img;
  }
}

class ColorFilter extends RGBImageFilter {
  // 3 types of color channal 
  private static int redChannel = 0xffff0000;
  private static int greenChannel = 0xff00ff00;
  private static int blueChannel = 0xff0000ff;

  // get the byte of one kind of color
  private static int getRed = 0x00ff0000;
  private static int getGreen = 0x0000ff00;
  private static int getBlue = 0x000000ff;
  private static int getHyaline = 0xff000000;

  // use integer to Represent the Color
  private static int red = 1;
  private static int green = 2;
  private static int blue = 3;
  private int color;

  public ColorFilter(int c) {
    color = c;
    canFilterIndexColorModel = true;
  }

  public int filterRGB(int x, int y, int rgb) {
    if (color == red) {
      return (rgb & redChannel);
    } else if (color == green) {
      return (rgb & greenChannel);
    } else if (color == blue) {
      return (rgb & blueChannel);
    } else {
      // algorithm of get the Gray
      int g = (int) (((rgb & getRed) >> 16) * 0.299
              + ((rgb & getGreen) >> 8) * 0.587 + ((rgb & getBlue)) * 0.114);
      return (rgb & getHyaline) + (g << 16) + (g << 8) + g;
    }
  }
}
