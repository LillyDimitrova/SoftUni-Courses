package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {
    private static final int DUMMY_HEALTH = 100;
    private static final int DUMMY_WITHOUT_HEALTH = 0;
    private static final int DUMMY_EXPERIENCE = 10;
    private static final int DUMMY_ATTACK_POINTS = 15;
    private static final int EXPECTED_HEALTH_AFTER_ATTACK = DUMMY_HEALTH - DUMMY_ATTACK_POINTS;

    private Dummy dummy;
    private Dummy dummyWithoutHealth;

    @Before
    public void initializeTestObject() {
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
        this.dummyWithoutHealth = new Dummy(DUMMY_WITHOUT_HEALTH, DUMMY_EXPERIENCE);
    }


    @Test
    public void testAttackedDummyLosesHealth() {

        this.dummy.takeAttack(DUMMY_ATTACK_POINTS);
        Assert.assertEquals(EXPECTED_HEALTH_AFTER_ATTACK, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackDummyThrowException() {

        this.dummyWithoutHealth.takeAttack(DUMMY_ATTACK_POINTS);
    }

    @Test
    public void testDeadDummyGivesXP() {


        int exp = this.dummyWithoutHealth.giveExperience();

        Assert.assertEquals(DUMMY_EXPERIENCE, exp);
    }

    @Test(expected = IllegalStateException.class)
    public void testAliveDummyDoesentGiveXP() {

        this.dummy.giveExperience();

    }
}