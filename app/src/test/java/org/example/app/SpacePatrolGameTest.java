/*
 * Test case for SpaceShip Game
 */
package org.SpaceShipGame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpacePatrolGameTest {
    @Test void testGetMessage() {
        assertEquals("Hello      World!", MessageUtils.getMessage());
    }
}
