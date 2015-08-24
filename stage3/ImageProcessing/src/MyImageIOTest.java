import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

/**
 * @author 13331087_HuangJunJie
 */
public class MyImageIOTest {
  private MyImageIO imageIOer;
  private Image standardImg;
  private String filePath;

  @Before
  public void setUp() throws Exception {
    imageIOer = new MyImageIO();
    filePath = "./bmptest/1.bmp";
    standardImg = ImageIO.read(new File("./bmptest/1.bmp"));
  }

  /**
   * Test method for {@link MyImageIO#myRead(java.lang.String)}.
   */
  @Test
  public void testMyRead() throws IOException {
    Image img1 = imageIOer.myRead(filePath);
    assertEquals(true, compare(img1, standardImg));
  }

  /**
   * Test method for {@link MyImageIO#myWrite(java.awt.Image, java.lang.String)}
   */
  @Test
  public void testMyWrite() {
    Image img2 = imageIOer.myWrite(standardImg, "./bmptest/test1.bmp");
    assertEquals(true, compare(img2, standardImg));
  }

  /**
   * Compares the pixel of two images <code>img1</code> and <code>img2</code>
   * 
   * @param img1
   *          an image type object
   * @param img2
   *          another image object
   * @return true if the two images match and false otherwise.
   */
  public boolean compare(Image img1, Image img2) {
    BufferedImage buffer1 = getBuffer(img1);
    BufferedImage buffer2 = getBuffer(img2);

    if (img1.getHeight(null) != img2.getHeight(null)
        || img1.getWidth(null) != img2.getWidth(null)) {
      return false;
    }
    for (int row = 0; row < img1.getHeight(null); row++) {
      for (int col = 0; col < img1.getWidth(null); col++) {
        if (buffer1.getRGB(col, row) != buffer2.getRGB(col, row)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * gets a bufferImage type for an image type.
   * 
   * @param <code>img</code> an image type object
   * @return the bufferedImage of <code>img</code>
   */
  public BufferedImage getBuffer(Image img) {
    BufferedImage buffer = new BufferedImage(img.getWidth(null),
        img.getHeight(null), BufferedImage.TYPE_INT_RGB);
    Graphics2D graph = buffer.createGraphics();
    graph.drawImage(img, 0, 0, null);

    return buffer;
  }

}
