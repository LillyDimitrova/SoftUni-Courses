package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AxeTest {
    private static final int AXE_ATTACK_POINTS = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int AXE_WITHOUT_DURABILITY = 0;
    private static final int DUMMY_HEALTH = 10;
    private static final int DUMMY_EXPERIENCE = 10;
    private static final int EXPECTED_DURABILITY_POINTS = AXE_DURABILITY - 1;


    private Dummy dummy;

    @Before
    public void initializeTestObject() {
       this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
    }


    @Test
    public void testAxeLosesDurabilityAfterAttack() {

        Axe axe = new Axe(AXE_ATTACK_POINTS, AXE_DURABILITY);
        axe.attack(this.dummy);

        Assert.assertEquals(EXPECTED_DURABILITY_POINTS,axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackWithBrokenAxeShouldThrowException() {

        Axe axe = new Axe(AXE_ATTACK_POINTS,AXE_WITHOUT_DURABILITY);

        axe.attack(this.dummy);
    }

}