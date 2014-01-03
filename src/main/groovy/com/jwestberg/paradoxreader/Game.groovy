package com.jwestberg.paradoxreader

import org.joda.time.DateTime

class Game extends GameObject {
    private String game
    private Provinces provinces
    private Countries countries

    public Game(String game, Map<String, Object> data) {
        super(data)
        this.game = game
    }

    public String getPlayer() { storage['player'] }
    public DateTime getDate() { storage['date'] }

    public Provinces getProvinces() {
        if(provinces == null) {
            provinces = new Provinces(this, getStorage()['provinces'])
        }
        return provinces
    }

    public Countries getCountries() {
        if(countries == null) {
            countries = new Countries(this, getStorage()['countries'])
        }
        return countries
    }

    public String toString() {
        ['game':game, 'player':player, 'date':date].toString()
    }
}
