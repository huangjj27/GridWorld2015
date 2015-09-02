import org.junit.Test;

import static org.junit.Assert.*;

public class HelloWorldTest {
  public HelloWorld helloworld = new HelloWorld();

  @Test
  public void testHello() {
    helloworld.hello();
    assertEquals("Hello World!", helloworld.getStr());
  }
}
