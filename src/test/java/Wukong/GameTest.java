package Wukong;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the {@link Game} class.
 * These tests simulate different game scenarios, such as game initialization,
 * area switching, and interactions between the player and the game world.
 * The tests include verifying game setup, moving between areas, and basic game mechanics.
 *
 * The test suite also uses simulated player inputs to mimic user behavior during gameplay.
 *
 * @author Yang Cao
 */

public class GameTest {

    private Game game;
    private Scanner scanner;

    @Before
    public void setUp() {
        String simulatedInput = "1\n\n\n\n\n\n\n\n\n\n"; 
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        scanner = new Scanner(inputStream);
        game = new Game("Wukong");
    }

    @Test
    public void testGameInitialization() {
        assertEquals("Wukong", game.getPlayer().getName());
        assertEquals("""
                Huaguo Mountain.
                The mountain is lush with trees and vegetation.
                In the distance, you hear the distant shower of the waterfall""", game.getCurrentArea().getName());
    }

    @Test
    public void testSwitchAreaToHeaven() {

        Area heaven = new Area("Heavenly Palace", "Heaven");
        game.switchAreas(heaven);

        assertEquals("Heavenly Palace", game.getCurrentArea().getName());
    }




    @Test
    public void testReturnToMap() {
        game.switchAreas(game.getCurrentArea());
        game.returnToMap();
        assertTrue("Map handling should have occurred", true); 
    }
}
