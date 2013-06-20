/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Window.MainWindow;
import junit.framework.TestCase;

/**
 *
 * @author Nels
 */
public class MazeTest extends TestCase {

    private Maze maze;
    private MainWindow main;

    public MazeTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        main = new MainWindow();
        main.startGame();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBuildMaze1() {
        int[][] integers = {{}};
        main.getGame().maze.setNodes(new Node[1][1]);
        main.getGame().getMaze().buildMaze(integers);
        if (main.getGame().getMaze().getNodes()[0][0] != null) {
            fail("testBuildMaze1 failed.");
        }
    }

    public void testBuildMaze2() {
        int[][] integers = {{1}, {0}};
        main.getGame().maze.setNodes(new Node[2][2]);
        main.getGame().getMaze().buildMaze(integers);
        if (main.getGame().getMaze().getNodes()[0][0].isWall()) {
            fail("testBuildMaze2 failed.");
        }
        if (main.getGame().getMaze().getNodes()[0][0].getOccupant(0) == null) {
            fail("testBuildMaze2 failed.");
        }
        if (main.getGame().getMaze().getNodes()[0][0].isWall()) {
            fail("testBuildMaze2 failed.");
        }
        if (main.getGame().getMaze().getNodes()[1][0].getOccupant(0) == null) {
            fail("testBuildMaze2 failed.");
        }
    }
}
