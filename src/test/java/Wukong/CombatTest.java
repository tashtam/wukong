package Wukong;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * The CombatTest class is used to test the combat logic in the Combat class,
 * including the interactions between the player and the monster.
 * This test class includes multiple test cases to ensure the correctness
 * and stability of the combat mechanics.
 *
 * @author Yang Cao
 */

public class CombatTest {

    private Player player;
    private Monster monster;
    private Combat combat;
    private Inventory sword;
    private Inventory shield;

    @Before
    public void setUp() {
        String simulatedInput = "1\n\n"; 
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);
        
        ArrayList<Inventory> playerInventory = new ArrayList<>();
        sword = new Inventory("A sharp sword", "Sword", 5, 20, 0);
        shield = new Inventory("A sturdy shield", "Shield", 10, 0, 5);
        playerInventory.add(sword);
        playerInventory.add(shield);

        player = new Player(100, playerInventory, "Wukong");
        monster = new Monster("Bull Demon King", 50, 20, null);
        
        combat = new Combat(monster, player, scanner);
    }
    

    @Test
    public void testPlayerAttacksMonster() {
        
        monster.setHealth(100);
        
        combat.playerAttacks(sword);
        
        assertEquals("monster's health should decrease", 80, monster.getHealth(), 0.01);
    }

    @Test
    public void testMonsterAttacksPlayer() {
        player.setHealth(100);
        
        combat.monsterAttacks();
        
        assertTrue("player's health should decrease", player.getHealth() < 100);
    }
}
