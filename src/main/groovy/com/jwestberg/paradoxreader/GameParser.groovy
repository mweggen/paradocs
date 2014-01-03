package com.jwestberg.paradoxreader

import com.google.common.collect.Lists
import com.google.common.collect.Maps
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.apache.commons.io.IOUtils
import org.joda.time.DateTime

public class GameParser {
    public static Map<String, Object> parse(String input) {
        parse(IOUtils.toInputStream(input))
    }

    public static Map<String, Object> parse(InputStream input) {
        walk(new ParadoxParser(
                new CommonTokenStream(
                        new ParadoxLexer(new ANTLRInputStream(input)
                        )
                )
        ).root().game())

    }

    private static Map<String, Object> walk(ParadoxParser.GameContext game) {
        Map map = Maps.newHashMap()
        game.pair().each {
            addPair(map, it)
        }

        return map
    }

    static def addPair(Map map, ParadoxParser.PairContext pairContext) {
        if (pairContext.value() == null) {
            println pairContext.getText()
        }
        map.put(getKey(pairContext.key()), getValue(pairContext.value()))
    }

    static def getKey(ParadoxParser.KeyContext keyContext) {
        if (keyContext.DATE() != null) {
            keyContext.getText().replace('.', '-')
        } else {
            keyContext.getText()
        }
    }

    static def getValue(ParadoxParser.ValueContext valueContext) {
        if (valueContext.number() != null) {
            try {
                Integer.parseInt(valueContext.getText())
            }
            catch (NumberFormatException e) {
                Double.parseDouble(valueContext.getText())
            }
        } else if (valueContext.string() != null) {
            stripQuotes(valueContext.getText())
        } else if (valueContext.object() != null) {
            Map map = Maps.newHashMap()
            valueContext.object().pair().each {
                addPair(map, it)
            }
            map
        } else if (valueContext.array() != null) {
            List list = Lists.newArrayList()
            valueContext.array().value().each {
                list.add(getValue(it))
            }
            list
        } else if (valueContext.enumerated() != null) {
            valueContext.getText()
        } else if (valueContext.date() != null) {
            new DateTime(stripQuotes(valueContext.getText()).replace('.', '-'))
        } else if (valueContext.bool() != null) {
            valueContext.bool().getText().equals('yes')
        }
    }

    static def stripQuotes(String s) {
        if(s.startsWith('"') && s.endsWith('"')) {
            s.substring(1, s.length() - 1)
        } else {
            s
        }
    }
}
