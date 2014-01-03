package com.jwestberg.paradoxreader

import com.google.common.io.Resources
import org.joda.time.DateTime
import org.junit.BeforeClass
import org.junit.Test

import static org.junit.Assert.*

import java.nio.charset.Charset

class ActualGameTest {
    static Game gameUnderTest

    @BeforeClass
    static void setUp() {
        gameUnderTest = GameParser.getGame(Resources.toString(Resources.getResource("autosave.eu4"), Charset.forName('UTF-8')))
    }

    @Test
    public void canGetTag() {
        assertEquals('CRE', gameUnderTest.player)
        assertEquals(new DateTime('1450-1-1'), gameUnderTest.date)
    }

    @Test
    public void canGetProvinces() {
        Provinces.Province stockholm = gameUnderTest.getProvinces().find { it.getId() == 1 }

        assertEquals("Stockholm", stockholm.name)
        assertEquals("Stockholm", stockholm.capital)
        assertEquals("SWE", stockholm.getController().tag)
        assertEquals("grain", stockholm.tradeGoods)
        assertFalse(stockholm.isInHRE())
        assertTrue(stockholm.isCity())
    }
}
