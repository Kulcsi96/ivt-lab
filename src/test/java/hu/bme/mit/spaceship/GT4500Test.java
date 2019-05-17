package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary;
  private  TorpedoStore secondary;

  @BeforeEach
  public void init(){

    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);
    this.ship = new GT4500(primary,secondary);
  }

  @Test
  public void fire_Torpedo_Primary(){
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);

    ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary,times(0)).fire(1);
    verify(secondary,times(1)).fire(1);
  }

  @Test
  public void fire_Torpedo_Secondary(){
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);

    ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary,times(1)).fire(1);
    verify(secondary,times(0)).fire(1);
  }

  @Test
  public void fire_Torpedo_Zero(){
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);

    ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary,times(0)).fire(1);
    verify(secondary,times(0)).fire(1);
  }

  @Test
  public void fire_Torpedo_Both(){
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);

    ship.fireTorpedo(FiringMode.ALL);

    verify(primary,times(1)).fire(1);
    verify(secondary,times(1)).fire(1);
  }

  @Test
  public void fire_Torpedo_All_Primary(){
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);

    ship.fireTorpedo(FiringMode.ALL);

    verify(primary,times(1)).fire(1);
    verify(secondary,times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary,times(1)).fire(1);

    // Assert
    //assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

}
