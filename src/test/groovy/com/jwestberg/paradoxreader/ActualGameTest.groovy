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
        gameUnderTest = new Game(GameParser.parse(Resources.toString(Resources.getResource("autosave.eu4"), Charset.defaultCharset())))

        println gameUnderTest.getStorage().keySet()
    }

    @Test
    public void canGetTag() {

        assertEquals('CRE', gameUnderTest.player)
        assertEquals(new DateTime('1450-1-1'), gameUnderTest.date)
    }
}
