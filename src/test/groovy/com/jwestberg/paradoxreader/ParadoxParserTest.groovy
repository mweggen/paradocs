package com.jwestberg.paradoxreader

import org.apache.commons.io.IOUtils
import org.joda.time.DateTime

import static GameParser.*
import static org.junit.Assert.*
import org.junit.Test

class ParadoxParserTest {
    @Test
    public void canParseKeyValue() {
        def map = parse(IOUtils.toInputStream('string="value x y z"\nint=2\nf=3.1'))

        assertEquals('value x y z', map.get('string'))
        assertEquals(2, map.get('int'))
        assertEquals(3.1, map.get('f'), 0.001)
    }

    @Test
    public void canParseKeyObject() {
        def map = parse(IOUtils.toInputStream('map={\n\tstring="value"\n\tint=2\n\tf=3.1}'))

        assertTrue(map.containsKey('map'))
        assertFalse(map.containsKey('value'))

        def subMap = (Map) map.get('map')
        assertEquals('value', subMap.get('string'))
        assertEquals(2, subMap.get('int'))
        assertEquals(3.1, subMap.get('f'), 0.001)
    }

    @Test
    public void canParseMultiLineArray() {
        def map = parse(IOUtils.toInputStream('list={"value" 2 3.1 }'))
        assertTrue(map.containsKey('list'))

        def subList = (List) map.get('list')
        assertEquals('value', subList.get(0))
        assertEquals(2, subList.get(1))
        assertEquals(3.1, subList.get(2), 0.001)
    }

    @Test
    public void readsEnumsAsStrings() {
        def map = parse(IOUtils.toInputStream('string=value int=2 float=3.1 '))

        assertEquals('value', map.get('string'))
    }

    @Test
    public void readsDates() {
        def map = parse(IOUtils.toInputStream('lutzen=1632.11.6 hastings="1066.10.14"'))

        assertEquals(new DateTime('1632-11-6'), map.get('lutzen'))
        assertEquals(new DateTime('1066-10-14'), map.get('hastings'))
    }

    @Test
    public void readsNegativeKeys() {
        def map = parse(IOUtils.toInputStream('-3="x y z"'))

        assertEquals('x y z', map.get('-3'))
    }

    @Test
    public void readsEmptyObjectAsNull() {
        def map = parse(IOUtils.toInputStream('x={ }'))

        assertNull(map.get('x'))
    }

    @Test
    public void readsYesNoAsBoolean() {
        def map = parse(IOUtils.toInputStream('x=yes y=no'))

        assertTrue(map.get('x'))
        assertFalse(map.get('y'))
    }
}
