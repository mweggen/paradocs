package com.jwestberg.paradoxreader

public abstract class GameObject {

    Map<String, Object> storage

    public GameObject() {
        storage = [:]
    }

    public GameObject(Map<String, Object> map) {
        storage = map
    }

    public def propertyMissing(String name) { storage[name] }
    public void setProperty(String name, value) { storage[name] = value }

    def getStorage() { storage }
    void setStorage(Map<String, Object> map) { storage = map }

    String toString() { storage.toString() }
}
