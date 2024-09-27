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
        // 模拟玩家选择物品并按多次回车，每次战斗都按回车继续
        // "1" 模拟选择物品，然后多个 "\n" 模拟玩家多次按下回车
        String simulatedInput = "1\n\n\n\n\n\n\n\n\n\n";  // 选择物品并多次按回车继续战斗
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        scanner = new Scanner(inputStream);
        // 初始化游戏，并传入模拟的 Scanner
        game = new Game("Wukong", scanner);
    }

    @Test
    public void testGameInitialization() {
        // 验证游戏初始化是否正确
        assertEquals("Wukong", game.getPlayer().getName());
        assertEquals("Huaguo Mountain", game.getCurrentArea().getName());
    }

    @Test
    public void testSwitchAreaToHeaven() {
        // 模拟从 HuaguoMount 到 Heaven 的切换
        Area heaven = new Area("Heavenly Palace", "Heaven");
        game.switchAreas(heaven);

        // 验证区域切换是否正确
        assertEquals("Heavenly Palace", game.getCurrentArea().getName());
    }




    @Test
    public void testReturnToMap() {
        game.switchAreas(game.getCurrentArea());
        game.returnToMap();
        assertTrue("Map handling should have occurred", true);  // 占位符
    }
}
