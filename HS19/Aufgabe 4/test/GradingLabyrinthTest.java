import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Test;

public class GradingLabyrinthTest {

  /** exactly once tests **/
  @Test
  public void testColorExactlyOnce1() {
    Room a0 = new Room(0);
    Room a1 = new Room(9);
    Room a2 = new Room(8);
    Room a3 = new Room(4);
    Room a4 = new Room(1);
    Room a5 = new Room(3);
    Room a6 = new Room(5);
    Room a7 = new Room(6);
    Room a8 = new Room(7);
    Room a9 = new Room(2);

    a0.doorsTo.add(a1);
    a0.doorsTo.add(new Room(1));

    a1.doorsTo.add(a2);

    a2.doorsTo.add(a3);

    a3.doorsTo.add(new Room(a6.getColor()));
    a3.doorsTo.add(a4);

    a4.doorsTo.add(new Room(a8.getColor()));
    a4.doorsTo.add(a5);

    a5.doorsTo.add(a6);

    a6.doorsTo.add(new Room(a7.getColor()));
    a6.doorsTo.add(a7);
    a6.doorsTo.add(new Room(a7.getColor()));

    a7.doorsTo.add(a8);

    a8.doorsTo.add(a9);

    assertTrue(Labyrinth.colorExactlyOnce(a0));

    a8.doorsTo.remove(a9);
    a8.doorsTo.add(new Room(1));

    assertFalse(Labyrinth.colorExactlyOnce(a0));
  }

  @Test
  public void testColorExactlyOnce1B() {
    Room a0 = new Room(0);
    Room a1 = new Room(9);
    Room a2 = new Room(8);
    Room a3 = new Room(4);
    Room a4 = new Room(1);
    Room a5 = new Room(3);
    Room a6 = new Room(5);
    Room a7 = new Room(6);
    Room a8 = new Room(7);
    Room a9 = new Room(2);

    a0.doorsTo.add(a1);
    a0.doorsTo.add(new Room(1));

    a1.doorsTo.add(a2);

    a2.doorsTo.add(a3);

    a3.doorsTo.add(new Room(a6.getColor()));
    a3.doorsTo.add(a4);

    a4.doorsTo.add(new Room(a5.getColor()));
    a4.doorsTo.add(a5);

    a5.doorsTo.add(a6);

    a6.doorsTo.add(new Room(a7.getColor()));
    a6.doorsTo.add(a7);
    a6.doorsTo.add(new Room(a7.getColor()));

    a7.doorsTo.add(a8);

    a8.doorsTo.add(a9);

    a9.doorsTo.add(new Room(9));

    assertFalse(Labyrinth.colorExactlyOnce(a0));

    a8.doorsTo.add(new Room(a9.getColor()));

    assertTrue(Labyrinth.colorExactlyOnce(a0));
  }

  @Test
  public void testColorExactlyOnce2() {
    Room a0 = new Room(0);
    Room a1 = new Room(1);
    Room a2 = new Room(8);
    Room a3 = new Room(4);
    Room a4 = new Room(1);
    Room a5 = new Room(3);
    Room a6 = new Room(5);
    Room a7 = new Room(6);
    Room a8 = new Room(7);
    Room a9 = new Room(2);
    Room a10 = new Room(9);

    a0.doorsTo.add(a1);
    a0.doorsTo.add(a2);

    a1.doorsTo.add(a3);

    a2.doorsTo.add(a3);

    a3.doorsTo.add(new Room(7));
    a3.doorsTo.add(a4);

    a4.doorsTo.add(a5);

    a5.doorsTo.add(a9);
    a5.doorsTo.add(a8);
    a5.doorsTo.add(a6);

    a6.doorsTo.add(a7);

    a7.doorsTo.add(a8);

    a8.doorsTo.add(a9);

    a9.doorsTo.add(a10);

    assertTrue(Labyrinth.colorExactlyOnce(a0));

    a9.doorsTo.remove(a10);

    a9.doorsTo.add(new Room(1));

    assertFalse(Labyrinth.colorExactlyOnce(a0));
  }

  @Test
  public void testColorExactlyOnce3() {
    Room a0 = new Room(8);
    Room a1 = new Room(1);
    Room a2 = new Room(3);
    Room a3 = new Room(7);
    Room a4 = new Room(5);
    Room a5 = new Room(4);
    Room a6 = new Room(2);
    Room a7 = new Room(9);
    Room a8 = new Room(6);
    Room a9 = new Room(0);

    Room b0 = new Room(5);
    Room b1 = new Room(4);
    Room b2 = new Room(2);

    Room c0 = new Room(0);

    Room d0 = new Room(9);
    Room d1 = new Room(6);
    Room d2 = new Room(0);

    a0.doorsTo.add(c0);
    a0.doorsTo.add(b0);
    a0.doorsTo.add(a1);

    a1.doorsTo.add(a2);

    a2.doorsTo.add(c0);
    a2.doorsTo.add(d0);
    a2.doorsTo.add(a3);

    a3.doorsTo.add(a4);

    a4.doorsTo.add(a5);

    a5.doorsTo.add(a6);

    a6.doorsTo.add(d0);
    a6.doorsTo.add(a7);

    a7.doorsTo.add(a8);

    a8.doorsTo.add(a9);

    b0.doorsTo.add(b1);
    b1.doorsTo.add(b2);
    b2.doorsTo.add(b0);

    c0.doorsTo.add(c0);

    d0.doorsTo.add(d1);
    d1.doorsTo.add(d2);
    d2.doorsTo.add(d0);

    assertTrue(Labyrinth.colorExactlyOnce(a0));

    a6.doorsTo.remove(a7);

    assertFalse(Labyrinth.colorExactlyOnce(a0));

    d2.doorsTo.remove(d0);

    assertTrue(Labyrinth.colorExactlyOnce(a0));
  }

  @Test
  public void testColorExactlyOnce4() {
    Room a0 = new Room(8);
    Room a1 = new Room(1);
    Room a2 = new Room(3);
    Room a3 = new Room(7);
    Room a4 = new Room(5);
    Room a5 = new Room(4);
    Room a6 = new Room(2);
    Room a7 = new Room(9);
    Room a8 = new Room(6);
    Room a9 = new Room(0);

    Room b0 = new Room(6);
    Room b1 = new Room(0);
    Room c0 = new Room(7);

    b0.doorsTo.add(b1);

    b1.doorsTo.add(a1);

    a0.doorsTo.add(a4);
    a0.doorsTo.add(c0);
    a0.doorsTo.add(a1);

    a1.doorsTo.add(a2);

    a2.doorsTo.add(b0);
    a2.doorsTo.add(a3);
    a2.doorsTo.add(a2);

    a3.doorsTo.add(a4);

    a4.doorsTo.add(b0);
    a4.doorsTo.add(a5);
    a4.doorsTo.add(a4);

    a5.doorsTo.add(a5);
    a5.doorsTo.add(a6);

    a6.doorsTo.add(a5);
    a6.doorsTo.add(a7);

    a7.doorsTo.add(b0);
    a7.doorsTo.add(a8);

    a8.doorsTo.add(b1);
    a8.doorsTo.add(a9);

    assertTrue(Labyrinth.colorExactlyOnce(a0));

    a2.doorsTo.clear();

    assertFalse(Labyrinth.colorExactlyOnce(a0));

    a2.doorsTo.add(c0);
    c0.doorsTo.add(a4);

    assertTrue(Labyrinth.colorExactlyOnce(a0));
  }

  /** not successively tests **/

  @Test
  public void testColorNotSuccessively1() {
    Room a0 = new Room(0);
    Room a1 = new Room(0);
    Room a2 = new Room(2);

    a0.doorsTo.add(new Room(a0.getColor()));
    a0.doorsTo.add(a1);
    a0.doorsTo.add(a2);
    a2.doorsTo.add(new Room(a2.getColor()));
    a2.doorsTo.add(a1);

    assertTrue(Labyrinth.colorNotSuccessively(a0));

    for (int i = 0; i < 30; i++) {
      a1.doorsTo.add(new Room(a1.getColor()));
    }

    assertFalse(Labyrinth.colorNotSuccessively(a0));
  }

  @Test
  public void testColorNotSuccessively2() {
    Room a0 = new Room(0);

    Room b0 = new Room(1);
    Room b1 = new Room(2);
    Room b2 = new Room(3);

    Room c1 = new Room(5);
    Room c2 = new Room(6);

    a0.doorsTo.add(b0);

    b0.doorsTo.add(b1);
    b1.doorsTo.add(b2);
    b2.doorsTo.add(b0);

    b0.doorsTo.add(c1);
    c1.doorsTo.add(c2);
    c2.doorsTo.add(b0);

    assertFalse(Labyrinth.colorNotSuccessively(a0));

    b2.doorsTo.remove(b0);

    assertTrue(Labyrinth.colorNotSuccessively(a0));
  }

  @Test
  public void testColorNotSuccessively3() {
    Room[] rooms = new Room[100];

    for (int i = 0; i < rooms.length; i++) {
      rooms[i] = new Room(i % 2);
    }

    for (int i = 0; i < rooms.length - 2; i++) {
      if (i > rooms.length / 2 + 1) {
        rooms[i].doorsTo.add(rooms[i + 2]);
        rooms[i].doorsTo.add(rooms[i + 1]);
      } else {
        rooms[i].doorsTo.add(rooms[i + 1]);
        rooms[i].doorsTo.add(rooms[i + 2]);
      }
    }

    assertTrue(Labyrinth.colorNotSuccessively(rooms[0]));

    rooms[rooms.length / 2].doorsTo.remove(rooms[(rooms.length / 2) + 1]);

    assertFalse(Labyrinth.colorNotSuccessively(rooms[0]));

    rooms[rooms.length / 2].doorsTo.add(rooms[(rooms.length / 2) + 3]);
    assertTrue(Labyrinth.colorNotSuccessively(rooms[0]));
  }

  @Test
  public void testColorNotSuccessively4() {
    Room a0 = new Room(0);

    assertTrue(Labyrinth.colorNotSuccessively(a0));

    a0.doorsTo.add(a0);

    assertFalse(Labyrinth.colorNotSuccessively(a0));
  }

  @Test
  public void testColorNotSuccessively5() {
    Room a0 = new Room(0);
    Room a1 = new Room(1);
    Room a2 = new Room(0);
    Room a3 = new Room(1);
    Room b0 = new Room(0);
    Room b1 = new Room(1);

    a0.doorsTo.add(b0);
    a0.doorsTo.add(a1);

    a1.doorsTo.add(a2);

    a2.doorsTo.add(b0);
    a2.doorsTo.add(b1);
    a2.doorsTo.add(a3);

    b0.doorsTo.add(a2);

    b1.doorsTo.add(a0);

    assertTrue(Labyrinth.colorNotSuccessively(a0));

    assertTrue(Labyrinth.colorNotSuccessively(b1));

    assertFalse(Labyrinth.colorNotSuccessively(b0));
  }

  /** remove cycle tests **/

  @Test
  public void testRemoveCycle1() {
    Room a0 = new Room(0);
    Room a1 = new Room(0);
    Room a2 = new Room(0);
    Room a3 = new Room(1);
    Room a4 = new Room(1);

    a0.doorsTo.add(a1);
    a0.doorsTo.add(a2);
    a0.doorsTo.add(a4);

    a1.doorsTo.add(a2);

    a2.doorsTo.add(a3);
    a2.doorsTo.add(a4);

    a3.doorsTo.add(a4);

    Labyrinth.removeCycle(a0);

    assertDoorsTo(a0, a1, a2, a4);

    assertDoorsTo(a1, a2);

    assertDoorsTo(a2, a3, a4);

    assertDoorsTo(a3, a4);

    assertDoorsTo(a4);

    a1.doorsTo.add(a0);

    Labyrinth.removeCycle(a0);

    assertDoorsTo(a0, a2, a4);

    assertDoorsTo(a1, a2);

    assertDoorsTo(a2, a3, a4);

    assertDoorsTo(a3, a4);

    assertDoorsTo(a4);
  }

  @Test
  public void testRemoveCycle2() {
    Room a0 = new Room(0);
    Room a1 = new Room(0);
    Room a2 = new Room(0);
    Room a3 = new Room(0);
    Room a4 = new Room(0);
    Room a5 = new Room(0);
    Room a6 = new Room(0);
    Room a7 = new Room(0);
    Room a8 = new Room(0);

    a0.doorsTo.add(a1);

    a1.doorsTo.add(a2);
    a1.doorsTo.add(a3);

    a2.doorsTo.add(a3);

    a3.doorsTo.add(a4);
    a3.doorsTo.add(a8);

    a4.doorsTo.add(a5);

    a5.doorsTo.add(a6);

    a6.doorsTo.add(a7);

    a7.doorsTo.add(a8);
    a7.doorsTo.add(a4);

    Labyrinth.removeCycle(a0);

    assertDoorsTo(a0, a1);
    assertDoorsTo(a1, a2, a3);
    assertDoorsTo(a2, a3);
    assertDoorsTo(a3, a4, a8);
    assertDoorsTo(a4);
    assertDoorsTo(a5);
    assertDoorsTo(a6);
    assertDoorsTo(a7, a8);
  }

  @Test
  public void testRemoveCycle3() {
    Room a0 = new Room(0);
    Room a1 = new Room(1);
    Room a2 = new Room(2);
    Room a3 = new Room(3);

    a0.doorsTo.add(a1);
    a1.doorsTo.add(a2);
    a2.doorsTo.add(a3);
    a3.doorsTo.add(a0);

    Labyrinth.removeCycle(a0);

    assertDoorsTo(a0);
    assertDoorsTo(a1);
    assertDoorsTo(a2);
    assertDoorsTo(a3);
  }

  @Test
  public void testRemoveCycle4() {
    Room a0 = new Room(0);
    Room a1 = new Room(1);
    Room a2 = new Room(2);
    Room a3 = new Room(3);
    Room a4 = new Room(3);
    Room a5 = new Room(3);

    a0.doorsTo.add(a1);
    a0.doorsTo.add(a3);

    a1.doorsTo.add(a2);

    a3.doorsTo.add(a4);
    a3.doorsTo.add(a4);
    a3.doorsTo.add(a4);

    a4.doorsTo.add(a5);

    a5.doorsTo.add(a3);
    a5.doorsTo.add(a3);

    Labyrinth.removeCycle(a0);

    assertDoorsTo(a0, a1, a3);
    assertDoorsTo(a1, a2);
    assertDoorsTo(a2);

    assertDoorsToNum(a3, new int[] { 2 }, a4); //still two doors remaining
    assertDoorsTo(a4);
    assertDoorsTo(a5, a3); //still one door remaining
  }

  @Test
  public void testRemoveCycle5() {
    Room a0 = new Room(0);
    Room a1 = new Room(1);
    Room a2 = new Room(2);
    Room a3 = new Room(3);
    Room a4 = new Room(3);
    Room a5 = new Room(3);

    a0.doorsTo.add(a1);
    a0.doorsTo.add(a3);

    a1.doorsTo.add(a2);

    a2.doorsTo.add(a1);

    a3.doorsTo.add(a4);

    a4.doorsTo.add(a5);

    a5.doorsTo.add(a3);

    //cycles (a3, a4, a5) and (a1,a2)

    //only one cycle must be removed
    Labyrinth.removeCycle(a0);

    if (a1.doorsTo.size() == 1) {
      //cycle (a3, a4, a5) must have been removed
      assertDoorsTo(a0, a1, a3);
      assertDoorsTo(a1, a2);
      assertDoorsTo(a2, a1);

      assertDoorsTo(a3);
      assertDoorsTo(a4);
      assertDoorsTo(a5);
    } else {
      //cycle (a1, a2) must have been removed
      assertDoorsTo(a0, a1, a3);
      assertDoorsTo(a1);
      assertDoorsTo(a2);

      assertDoorsTo(a3, a4);
      assertDoorsTo(a4, a5);
      assertDoorsTo(a5, a3);
    }
  }

  // Checks whether exactly 'num' many doors exist from 'from' to 'to'
  private static void assertDoorFromTo(Room from, Room to, int num) {
    int count = 0;
    for (Room room : from.doorsTo) {
      if (room == to) {
        count += 1;
      }
    }
    assertEquals(num, count);
  }

  private static void assertDoorsTo(Room from, Room... toRooms) {
    assertEquals(toRooms.length, from.doorsTo.size());

    for (Room to : toRooms) {
      assertDoorFromTo(from, to, 1);
    }
  }

  // Checks whether the doors from 'from' correspond to 'toRooms' where the number of doors to the respective rooms is given 'num'
  private static void assertDoorsToNum(Room from, int[] num, Room... toRooms) {
    int count = Arrays.stream(num).sum();
    assertEquals(count, from.doorsTo.size());

    for (int i = 0; i < toRooms.length; i++) {
      assertDoorFromTo(from, toRooms[i], num[i]);
    }
  }
}
