// ImagaReaderRunner.java

import imagereader.Runner;

public final class ImageReaderRunner {
  private ImageReaderRunner() {
  }

  public static void main(String[] args) {
    MyImageIO imageioer = new MyImageIO();
    MyImageProcessor processor = new MyImageProcessor();
    Runner.run(imageioer, processor);
  }
}
