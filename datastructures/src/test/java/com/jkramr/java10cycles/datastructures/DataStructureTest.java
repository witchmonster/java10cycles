package com.jkramr.java10cycles.datastructures;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class DataStructureTest {

  private DataStructure<String> structure;

  @Test
  public void shouldReturnCorrectSize()
          throws Exception {
    structure = createEmpty();
    assertEquals(true, structure.isEmpty());
    assertEquals(0, structure.size());
    structure.add("one");
    structure.add("two");
    assertEquals(2, structure.size());
  }

  @Test
  public void shouldContainAdded()
          throws Exception {
    structure = createEmpty();
    structure.add("one");
    structure.add("two");
    structure.add(null);
    assertEquals(true, structure.contains("two"));
    assertEquals(true, structure.contains(null));
  }

  @Test
  public void shouldRemove()
          throws Exception {
    structure = createEmpty();
    assertEquals(false, structure.contains("one"));
    structure.add("one");
    assertEquals(true, structure.contains("one"));
    assertEquals(1, structure.size());
    structure.remove("one");
    assertEquals(false, structure.contains("one"));
    assertEquals(true, structure.isEmpty());
    assertEquals(0, structure.size());
  }

  protected abstract DataStructure<String> createEmpty();
}
