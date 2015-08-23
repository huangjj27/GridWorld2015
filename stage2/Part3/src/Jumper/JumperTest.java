import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class JumperTest {
        private Jumper jumper = new Jumper();
        private Jumper jumper2 = new Jumper(Color.black);
        private ActorWorld world = new ActorWorld();
        private Rock newRock = new Rock();
        
        @Before
        public void setUp() throws Exception {
                int row = 0;
                int col = 0;
                col = world.getGrid().getNumCols();
                row = world.getGrid().getNumRows();
                int i = 0;
                int j = 0;
                while (i < row) {
                        j = 0;
                        while (j < col) {
                            world.remove(new Location(i,j));
                            j++;
                        }
                        i++;
                }
        }
        
        
        /**
         * check the act method is right or not.
         */
        @Test
        public void testAct() {
                world.add(new Location(2,2), jumper);
                jumper.act();
                jumper.act();
                jumper.act();
                jumper.act();
                assertEquals(new Location(0,4), jumper.getLocation());
        }
        
        
        @Test
        public void testJumperInt() {
                assertEquals(Color.red, jumper.getColor());
        }
        
        @Test
        public void testJumperColorInt() {
                assertEquals(Color.black, jumper2.getColor());
        }
        
        /**
         * check the turn method is right or not.
         */
        @Test
        public void testTurn() {
                world.add(new Location(2,2), jumper);
                jumper.turn();
                assertEquals(45, jumper.getDirection());
        }
        
        /**
         * check the move method is right or not.
         */
        @Test
        public void testMove() {
                world.add(new Location(2,2), jumper);
                jumper.move();
                assertEquals(new Location(0,2),jumper.getLocation());
        }
        
        /**
         * check the canMove method is right or not.
         */
        @Test
        public void testCanMove() {
        	    world.add(new Location(2,2), jumper);
                world.add(new Location(0,2),newRock);
                assertEquals(false, jumper.canMove());
        }
        
}
