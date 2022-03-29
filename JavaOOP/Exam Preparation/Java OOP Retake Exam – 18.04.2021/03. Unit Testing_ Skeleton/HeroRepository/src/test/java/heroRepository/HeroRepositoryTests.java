package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {
    private HeroRepository heroRepository;
    private Hero hero;

    @Before
    public void setUp() {
        this.heroRepository = new HeroRepository();
        this.hero = new Hero("Lili", 2);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateThrowHeroIsNull() {
        this.heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateThrowHeroIsDuplicated() {
        this.heroRepository.create(this.hero);
        this.heroRepository.create(this.hero);
    }

    @Test
    public void testCreateSuccessfully() {
        Assert.assertEquals(0, this.heroRepository.getCount());
        this.heroRepository.create(this.hero);
        Assert.assertEquals(1, this.heroRepository.getCount());
        Hero hero1 = this.heroRepository.getHero("Lili");

        Assert.assertEquals(hero1.getName(),this.hero.getName());
        Assert.assertEquals(hero1.getLevel(),this.hero.getLevel());

    }
     @Test(expected = NullPointerException.class)
    public void testRemoveThrowNameIsNull() {
        this.heroRepository.remove(null);
     }

    @Test(expected = NullPointerException.class)
    public void testRemoveThrowNameIsEmpty() {
        this.heroRepository.remove("");
    }

    @Test
    public void testRemoveSuccessfully() {
        Assert.assertEquals(0,this.heroRepository.getCount());
        this.heroRepository.create(this.hero);
        Assert.assertEquals(1,this.heroRepository.getCount());
        this.heroRepository.remove("Lili");
        Assert.assertEquals(0,this.heroRepository.getCount());
        Hero removeHero = this.heroRepository.getHero("Lili");
        Assert.assertNull(removeHero);
    }
     @Test
    public void testGetHeroWithHighestLevel() {
        Hero hero1 = new Hero("Ivan", 3);
        Hero hero2 = new Hero("Dragan", 4);
        Hero hero3 = new Hero("Georgi", 5);
        this.heroRepository.create(hero1);
        this.heroRepository.create(hero2);
        this.heroRepository.create(hero3);

        Assert.assertEquals(3,this.heroRepository.getHeroes().size());
        Hero heroWithHighestLevel = this.heroRepository.getHeroWithHighestLevel();

        Assert.assertEquals(heroWithHighestLevel.getName(), hero3.getName());
        Assert.assertEquals(heroWithHighestLevel.getLevel(), hero3.getLevel());
     }
}
