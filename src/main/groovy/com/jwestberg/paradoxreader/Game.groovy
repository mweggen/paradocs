package com.jwestberg.paradoxreader

class Game extends GameObject {

    public Game(Map<String, Object> data) {
        super(data)
    }

    String getPlayer() { player }
    String getDate() { date }
}
