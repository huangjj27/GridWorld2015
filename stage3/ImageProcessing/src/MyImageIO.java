import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import imagereader.IImageIO;

class MyImageIO implements IImageIO {
  // Image to de dealed with
  private Image img;

  // serval constant to avoid magic number.
  // most infromation stor in a double word length
  private static int dWordSize = 4;
  private static int dWordBits = 8;
  private static int word = 2;
  // move a byte to the highest byte of an integer
  private static int highestByte = 24;
  
  // used in bit-or operation 
  private static int one = 0xff;

  // file Header length
  private static int lengthBmpHead = 14;
  // the location byte of the file size in file header
  private static int fileSizeOffset = 2;

  // bitmap image header length
  private static int lengthBmpInfo = 40;
  // the location byte of the image size in image header
  private static int imageSizeOffset = 20;
  // the location byte of the image size in image header
  private static int imageWidthOffset = 4;
  // the location byte of the image size in image header
  private static int imageHeightOffset = 8;
  // the location byte of how many bits each pixel has
  private static int pixelBitsOffset = 14;
  // each pixel has 3 bytes to store color
  private static int colorBits = 3;
  private static int trueColor = 24;

  @Override
  public Image myRead(String filePath) throws IOException {
    // creates a file
    File file = new File(filePath);

    // creates a file input stream to read the binary data
    FileInputStream fileInStream = new FileInputStream(file);

    // creates the array bmpHead & bmpInfo to store corresponding message.
    byte bmpHead[] = new byte[lengthBmpHead];
    byte bmpInfo[] = new byte[lengthBmpInfo];

    fileInStream.read(bmpHead, 0, lengthBmpHead);
    fileInStream.read(bmpInfo, 0, lengthBmpInfo);

    // gets the whole size of the bmp file in the unit of bytes
    @SuppressWarnings("unused")
    int fileSize = bytesToInt(bmpHead, fileSizeOffset, dWordSize);

    // gets the bmpInfo and store the size of the bmp in the unit of pixels'
    // bytes. each pixel length is 3 bytes
    int imageSize = bytesToInt(bmpInfo, imageSizeOffset, dWordSize);

    // gets the bmp width in unit of pixels
    int imageWidth = bytesToInt(bmpInfo, imageWidthOffset, dWordSize);

    // gets the bmp height in unit of pixels
    int imageHeight = bytesToInt(bmpInfo, imageHeightOffset, dWordSize);

    // gets the byte number of the bmp image
    // and judge if the bmp is an image of 24-bit-color pixels.
    int bitsForPixel = bytesToInt(bmpInfo, pixelBitsOffset, word);

    // if the image is a 24-bit one, which means the bytes for each pixel is
    // not the multiplication of 4 and it is hard for computer to process,
    // thus it needs to expand.
    // first calculates the number of empty bytes for each row.
    if (bitsForPixel == trueColor) {
      int numEmptyByteOfRow = imageSize / imageHeight - colorBits * imageWidth;

      // if it has already been easy to process, no need to expand.
      if (numEmptyByteOfRow == dWordSize) {
        numEmptyByteOfRow = 0;
      }

      // gets the pixel informations.
      // Reads From the <code>fileInStream</code> to the
      // <code>pixelBytes</code>
      // In the order of from buttom to top, from left to right. store them to
      // the correspond coordinate. for example, the (row, col) pixel is
      // stored to piexlArray[imageWidth*row + col]
      int pixel = 0;
      int pixelArray[] = new int[imageWidth * imageHeight];
      byte pixelBytes[] = new byte[imageSize];
      fileInStream.read(pixelBytes, 0, imageSize);

      for (int row = imageHeight - 1; row >= 0; row--) {
        for (int col = 0; col < imageWidth; col++) {
          // the "0xff << 24" indicates the diaphaneity. the number's smaller
          // the pixel more hyaline.
          int pixelLoc = imageWidth * row + col;
          pixelArray[pixelLoc] = bytesToInt(pixelBytes, pixel, colorBits);
          pixelArray[pixelLoc] |= (one << highestByte);
          // move to next pixel
          pixel += colorBits;
        }
        // skip the empty bytes.
        pixel += numEmptyByteOfRow;
      }

      img = Toolkit.getDefaultToolkit().createImage(
          (ImageProducer) new MemoryImageSource(imageWidth, imageHeight,
              pixelArray, 0, imageWidth));
    }

    // close the file stream and return the Image we get.
    fileInStream.close();
    return img;
  }

  @Override
  public Image myWrite(Image img, String filepath) {
    try {
      File imgFile = new File(filepath);
      BufferedImage buffer = new BufferedImage(img.getWidth(null),
          img.getHeight(null), BufferedImage.TYPE_INT_RGB);
      Graphics2D graph = buffer.createGraphics();
      graph.drawImage(img, 0, 0, null);
      graph.dispose();
      ImageIO.write(buffer, "bmp", imgFile);
      return img;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return img;
  }

  /**
   * <code>bytesToInt</code> is a function to manage a set of bytes to an
   * integer. for example, gets the size of the image (counting in bytes) from a
   * four-byte set.
   * 
   * @param bytes
   *          the array stores the bytes' information, which can be explained
   *          through the function
   * @param offset
   *          the first location in <code>bytes</code> needs to be processed.
   * @param length
   *          how many bytes need procession.
   * @return an integer transformed from the bytes with rule of Little-Endian.
   */
  private int bytesToInt(byte[] bytes, int offset, int length) {
    int result = 0;
    for (int i = 0; i < length; i++) {
      result |= (bytes[offset + i] & one) << (i * dWordBits);
    }
    return result;
  }
}
