package com.jwestberg.paradoxreader

/**
 * Created with IntelliJ IDEA.
 * User: joel
 * Date: 1/2/14
 * Time: 11:36 PM
 * To change this template use File | Settings | File Templates.
 */
abstract class AbstractGameObjectCollection<T> extends GameObject implements Iterable<T> {
    def game

    AbstractGameObjectCollection(Game game, Map<String, Map<String, Object>> objectMap) {
        super()
        this.game = game
        addObjects(objectMap)
    }

    def addObjects(Map<String, Map<String, Object>> map) {
        map.each {
            storage.put(it.key, toObject(it.key, it.value))
        }
    }

    public Iterator<T> iterator() {
        storage.values().iterator()
    }

    abstract T toObject(String key, Map<String, Object> map)
}
