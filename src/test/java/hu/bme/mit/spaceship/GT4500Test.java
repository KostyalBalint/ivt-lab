package hu.bme.mit.spaceship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GT4500Test {

    private GT4500 ship;

    private TorpedoStore mockPrimaryTorpedoStore;
    private TorpedoStore mockSecondaryTorpedoStore;

    @BeforeEach
    public void init() {
        mockPrimaryTorpedoStore = mock(TorpedoStore.class);
        mockSecondaryTorpedoStore = mock(TorpedoStore.class);

        when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockSecondaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockPrimaryTorpedoStore.isEmpty()).thenReturn(false);
        when(mockSecondaryTorpedoStore.isEmpty()).thenReturn(false);

        this.ship = new GT4500(mockPrimaryTorpedoStore, mockSecondaryTorpedoStore);
    }

    @Test
    public void fireTorpedo_Single_Success() {
        // Arrange

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(mockPrimaryTorpedoStore, times(1)).fire(1);
    }

    @Test
    public void fireTorpedo_All_Success() {
        // Arrange

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        verify(mockPrimaryTorpedoStore, times(1)).fire(1);
        verify(mockSecondaryTorpedoStore, times(1)).fire(1);
    }

  /*
  ### Test case 1
  - Given: Both primary and secondary torpedo stores are full.
  - Given: Firing mode is SINGLE
  - When: Fire is called.
  - Then: Primary torpedo store is fired successfully.
  - Then: Secondary torpedo store is not fired.
  */

    @Test
    public void fireTorpedo_Single_Primary_Success() {
        // Arrange
        when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockSecondaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockPrimaryTorpedoStore.isEmpty()).thenReturn(false);
        when(mockSecondaryTorpedoStore.isEmpty()).thenReturn(false);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(mockPrimaryTorpedoStore, times(1)).fire(1);
        verify(mockSecondaryTorpedoStore, times(0)).fire(1);
    }

  /*
  ### Test case 2
  - Given: Both primary and secondary torpedo stores are full.
  - Given: Firing mode is SINGLE.
  - When: Primary torpedo store is fired successfully.
  - When: Fire is called.
  - Then: Secondary torpedo store is fired successfully.
  */

    @Test
    public void fireTorpedo_Both_Success() {
        // Arrange
        when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockSecondaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockPrimaryTorpedoStore.isEmpty()).thenReturn(false);
        when(mockSecondaryTorpedoStore.isEmpty()).thenReturn(false);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);
        result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(mockPrimaryTorpedoStore, times(1)).fire(1);
        verify(mockSecondaryTorpedoStore, times(1)).fire(1);
    }

    /*
    ### Test case 3

    - Given: Primary torpedo store is empty.
    - Given: Secondary torpedo store is full.
    - Given: Firing mode is SINGLE.
    - When: Fire is called.
    - Then: Primary torpedo store is not fired.
    - Then: Secondary torpedo store is fired successfully.
    */

    @Test
    public void fireTorpedo_Secondary_Success() {
        // Arrange
        when(mockPrimaryTorpedoStore.fire(1)).thenReturn(false);
        when(mockSecondaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockPrimaryTorpedoStore.isEmpty()).thenReturn(true);
        when(mockSecondaryTorpedoStore.isEmpty()).thenReturn(false);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(mockPrimaryTorpedoStore, times(0)).fire(1);
        verify(mockSecondaryTorpedoStore, times(1)).fire(1);
    }

    /*
    ### Test case 4

    - Given: Both primary and secondary torpedo stores are empty.
    - Given: Firing mode is SINGLE.
    - When: Fire is called.
    - Then: Primary torpedo store is not fired.
    - Then: Secondary torpedo store is not fired.
    - Then: FireTorpedos returns false.
    */

    @Test
    public void fireTorpedo_Both_Empty() {
        // Arrange
        when(mockPrimaryTorpedoStore.fire(1)).thenReturn(false);
        when(mockSecondaryTorpedoStore.fire(1)).thenReturn(false);
        when(mockPrimaryTorpedoStore.isEmpty()).thenReturn(true);
        when(mockSecondaryTorpedoStore.isEmpty()).thenReturn(true);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        // Assert
        verify(mockPrimaryTorpedoStore, times(0)).fire(1);
        verify(mockSecondaryTorpedoStore, times(0)).fire(1);
        assertFalse(result);
    }

  /*
  ### Test case 5

  - Given: Both primary and secondary torpedo stores are full.
  - Given: Firing mode is ALL.
  - When: Fire is called.
  - Then: Primary torpedo store is fired successfully.
  - Then: Secondary torpedo store is fired successfully.
 */

    @Test
    public void fireTorpedo_Both_Full_All() {
        // Arrange
        when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockSecondaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockPrimaryTorpedoStore.isEmpty()).thenReturn(false);
        when(mockSecondaryTorpedoStore.isEmpty()).thenReturn(false);

        // Act
        boolean result = ship.fireTorpedo(FiringMode.ALL);

        // Assert
        verify(mockPrimaryTorpedoStore, times(1)).fire(1);
        verify(mockSecondaryTorpedoStore, times(1)).fire(1);
        assertTrue(result);
    }

    /*
        Test implemented based on the sourcecode of the original fireTorpedo method.
     */
    @Test
    public void fireTorpedo_Both_Empty_All() {
        when(mockPrimaryTorpedoStore.fire(1)).thenReturn(false);
        when(mockSecondaryTorpedoStore.fire(1)).thenReturn(false);
        when(mockPrimaryTorpedoStore.isEmpty()).thenReturn(true);
        when(mockSecondaryTorpedoStore.isEmpty()).thenReturn(true);

        boolean result = ship.fireTorpedo(FiringMode.ALL);

        verify(mockPrimaryTorpedoStore, times(0)).fire(1);
        verify(mockSecondaryTorpedoStore, times(0)).fire(1);
        assertFalse(result);
    }

    @Test
    public void fireLaser() {
        // Act
        boolean result = ship.fireLaser(FiringMode.SINGLE);
        // Assert
        assertFalse(result);
    }


    @Test
    void fireTorpedo_Twice_Secondary_Empty(){
        when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockSecondaryTorpedoStore.fire(1)).thenReturn(false);
        when(mockPrimaryTorpedoStore.isEmpty()).thenReturn(false);
        when(mockSecondaryTorpedoStore.isEmpty()).thenReturn(true);

        boolean result = ship.fireTorpedo(FiringMode.SINGLE);
        boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

        verify(mockPrimaryTorpedoStore, times(2)).fire(1);
        verify(mockSecondaryTorpedoStore, times(0)).fire(1);
        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    void fireTorpido_No_Mode(){
        when(mockPrimaryTorpedoStore.fire(1)).thenReturn(true);
        when(mockSecondaryTorpedoStore.fire(1)).thenReturn(false);
        when(mockPrimaryTorpedoStore.isEmpty()).thenReturn(false);
        when(mockSecondaryTorpedoStore.isEmpty()).thenReturn(true);

        assertThrows(NullPointerException.class, () -> {
            ship.fireTorpedo(null);
        });
    }

}
