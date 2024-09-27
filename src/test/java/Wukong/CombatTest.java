package Wukong;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class CombatTest {

    private Player player;
    private Monster monster;
    private Combat combat;
    private Inventory sword;
    private Inventory shield;

    @Before
    public void setUp() {
        // 模拟用户输入，包括选择物品和按回车
        String simulatedInput = "1\n\n";  // 模拟用户选择第一个物品并按下回车
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // 设置玩家和怪物
        ArrayList<Inventory> playerInventory = new ArrayList<>();
        sword = new Inventory("A sharp sword", "Sword", 5, 20, 0);
        shield = new Inventory("A sturdy shield", "Shield", 10, 0, 5);
        playerInventory.add(sword);
        playerInventory.add(shield);

        player = new Player(100, playerInventory, "Wukong");
        monster = new Monster("Bull Demon King", 50, 20, null);

        // 初始化 Combat 类
        combat = new Combat(monster, player, scanner);
    }

    @Test
    public void testCombatPlayerWins() {
        // 设置玩家健康高，怪物健康低以确保玩家获胜
        player.setHealth(100);
        monster.setHealth(10);  // 怪物很弱

        boolean result = combat.Combat();  // 执行战斗
        assertTrue("玩家应该获胜", result);
    }

    @Test
    public void testCombatMonsterWins() {
        // 设置怪物健康高，玩家健康低以确保怪物获胜
        player.setHealth(10);  // 玩家很弱
        monster.setHealth(100);  // 怪物很强

        boolean result = combat.Combat();  // 执行战斗
        assertFalse("怪物应该获胜", result);
    }

    @Test
    public void testPlayerAttacksMonster() {
        // 初始化怪物健康
        monster.setHealth(100);

        // 玩家用剑攻击怪物
        combat.playerAttacks(sword);

        // 检查怪物的健康是否减少
        assertEquals("怪物的健康值应该减少", 80, monster.getHealth(), 0.01);
    }

    @Test
    public void testMonsterAttacksPlayer() {
        // 初始化玩家健康
        player.setHealth(100);

        // 怪物攻击玩家
        combat.monsterAttacks();

        // 检查玩家的健康是否减少
        assertTrue("玩家的健康值应该减少", player.getHealth() < 100);
    }
}
