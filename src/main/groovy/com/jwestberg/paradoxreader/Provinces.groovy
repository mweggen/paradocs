package com.jwestberg.paradoxreader

class Provinces extends AbstractGameObjectCollection<Province> implements Iterable<Province> {
    public Provinces(Game game, Map<String, Map<String, Object>> map) {
        super(game, map)
    }

    @Override
    Province toObject(String key, Map<String, Object> map) {
        return new Province(key, map)
    }

    class Province extends GameObject {
        def id

        public Province(String id, Map<String, Object> map) {
            super(map)
            this.id = Math.abs(Integer.parseInt(id))
        }

        public String getName() { storage['name'] }
        public String getTradeNode() { storage['trade'] }
        public String getTradeGoods() { storage['trade_goods'] }
        public String getTradePower() { storage['trade_power'] }
        public Countries.Country getController() { Provinces.this.game.getCountries()[storage['controller']] }
        public Countries.Country getOwner() { Provinces.this.game.getCountries()[storage['owner']] }
        public boolean isInHRE() { storage['hre'] }
        public String getReligion() { storage['religion'] }
        public String getCapital() { storage['capital'] }
        public boolean isCity() { storage['is_city'] }
    }
}
