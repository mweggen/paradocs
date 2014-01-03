package com.jwestberg.paradoxreader

class TradeNodes extends AbstractGameObjectCollection<TradeNode> {

    public TradeNodes(Game game, Map<String, Map<String, Object>> map) {
        super(game, map)
    }

    @Override
    TradeNode toObject(String key, Map<String, Object> map) {
        return new TradeNode(key, map)
    }

    class TradeNode extends GameObject {
        def name
        TradeNode(String name, Map<String, Object> map) {
            super(map)
            this.name = name
        }
    }
}
