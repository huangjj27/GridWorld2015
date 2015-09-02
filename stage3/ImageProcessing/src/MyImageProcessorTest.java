import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class MyImageProcessorTest {
  private MyImageIOTest ioTest;
  private MyImageProcessor myProcessor;
  private String filePath = "./bmptest/";
  private Image standardImg1;
  private Image standardImg2;

  @Before
  public void setUp() throws Exception {
    ioTest = new MyImageIOTest();
    myProcessor = new MyImageProcessor();
    standardImg1 = ImageIO.read(new File(filePath + "1.bmp"));
    standardImg2 = ImageIO.read(new File(filePath + "2.bmp"));
  }

  @Test
  public void testShowChanelR() throws IOException {
    Image standardRed1 = ImageIO.read(new File(filePath + "goal/1_red_goal.bmp"));
    Image myRed1 = myProcessor.showChanelR(standardImg1);
    assertEquals(true, ioTest.compare(myRed1, standardRed1));

    Image standardRed2 = ImageIO.read(new File(filePath + "goal/2_red_goal.bmp"));
    Image myRed2 = myProcessor.showChanelR(standardImg2);
    assertEquals(true, ioTest.compare(myRed2, standardRed2));
  }

  @Test
  public void testShowChanelG() throws IOException {
    Image standardGreen1 = ImageIO.read(new File(filePath + "goal/1_green_goal.bmp"));
    Image myGreen1 = myProcessor.showChanelG(standardImg1);
    assertEquals(true, ioTest.compare(myGreen1, standardGreen1));

    Image standardGreen2 = ImageIO.read(new File(filePath + "goal/2_green_goal.bmp"));
    Image myGreen2 = myProcessor.showChanelG(standardImg2);
    assertEquals(true, ioTest.compare(myGreen2, standardGreen2));
  }

  @Test
  public void testShowChanelB() throws IOException {
    Image standardBlue1 = ImageIO.read(new File(filePath + "goal/1_blue_goal.bmp"));
    Image myBlue1 = myProcessor.showChanelB(standardImg1);
    assertEquals(true, ioTest.compare(myBlue1, standardBlue1));

    Image standardBlue2 = ImageIO.read(new File(filePath + "goal/2_blue_goal.bmp"));
    Image myBlue2 = myProcessor.showChanelB(standardImg2);
    assertEquals(true, ioTest.compare(myBlue2, standardBlue2));
  }

  @Test
  public void testShowGray() throws IOException {
    Image standardGray1 = ImageIO.read(new File(filePath + "goal/1_gray_goal.bmp"));
    Image myGray1 = myProcessor.showGray(standardImg1);
    assertEquals(true, ioTest.compare(myGray1, standardGray1));

    Image standardGray2 = ImageIO.read(new File(filePath + "goal/2_gray_goal.bmp"));
    Image myGray2 = myProcessor.showGray(standardImg2);
    assertEquals(true, ioTest.compare(myGray2, standardGray2));
  }

}
