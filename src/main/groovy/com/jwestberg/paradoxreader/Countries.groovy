package com.jwestberg.paradoxreader

class Countries extends AbstractGameObjectCollection<Country> {
    Countries(Game game, Map<String, Map<String, Objects>> map) {
        super(game, map)
    }

    @Override
    Country toObject(String key, Map<String, Object> map) {
        return new Country(key, map)
    }

    public class Country extends GameObject {
        def tag
        private Country(String tag, Map<String, Object> map) {
            super(map)
            this.tag = tag
        }
    }
}
