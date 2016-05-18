package com.alexaitken.gildedrose;

public interface Updater {

  public static Updater get(Item item) {
    switch (item.getName()) {
      case "Aged Brie":
        return new BrieUpdater();
      case "Sulfuras, Hand of Ragnaros":
        return new SulfurasUpdater();
      case "Backstage passes to a TAFKAL80ETC concert":
        return new TicketUpdater();
    }
    return new DefaultUpdater();
  }

  void update(Item item);

  public static abstract class BaseUpdater implements Updater {
    protected void tickSellIn(Item item) {
      item.setSellIn(item.getSellIn() - 1);
    }

    protected void tickQuality(Item item, int incrementBy) {
      item.setQuality(Math.max(Math.min(item.getQuality() + incrementBy, 50), 0));
    }
  }

  public static class SulfurasUpdater implements Updater {
    @Override
    public void update(Item item) {
      // immutable
    }
  }

  public static class TicketUpdater extends BaseUpdater {
    @Override
    public void update(Item item) {
      tickSellIn(item);
      if (item.isExpired()) {
        item.setQuality(0);
      } else {
        int sellIn = item.getSellIn();
        int increaseBy = (sellIn < 5) ? 3 : (sellIn < 10) ? 2 : 1;
        tickQuality(item, increaseBy);
      }
    }
  }

  public static class BrieUpdater extends BaseUpdater {
    @Override
    public void update(Item item) {
      tickSellIn(item);
      tickQuality(item, 1);
    }
  }

  public static class DefaultUpdater extends BaseUpdater {
    @Override
    public void update(Item item) {
      tickSellIn(item);
      tickQuality(item, item.isExpired() ? -2 : -1);
    }
  }

}
