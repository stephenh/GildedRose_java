package com.alexaitken.gildedrose;

/**
 * Updates the quality of items, with item-specific business rules.
 */
public interface Updater {

  /** @return an {@code Updater} for the given {@code item}. */
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

  /** Updates {@code item}'s quality, given that a day has passed. */
  void update(Item item);

  /** A base updater with some helper methods. */
  static abstract class BaseUpdater implements Updater {
    protected void decrementSellIn(Item item) {
      item.setSellIn(item.getSellIn() - 1);
    }

    /** Updates quality by {@code decrementBy}, but bounded by [0, 50]. */
    protected void decrementQuality(Item item, int decrementBy) {
      item.setQuality(Math.max(Math.min(item.getQuality() - decrementBy, 50), 0));
    }

    /** Updates quality by {@code incrementBy}, but bounded by [0, 50]. */
    protected void incrementQuality(Item item, int incrementBy) {
      decrementQuality(item, incrementBy * -1);
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
      decrementSellIn(item);
      if (item.isExpired()) {
        item.setQuality(0);
      } else {
        int sellIn = item.getSellIn();
        int increaseBy = (sellIn < 5) ? 3 : (sellIn < 10) ? 2 : 1;
        incrementQuality(item, increaseBy);
      }
    }
  }

  public static class BrieUpdater extends BaseUpdater {
    @Override
    public void update(Item item) {
      decrementSellIn(item);
      incrementQuality(item, 1);
    }
  }

  public static class DefaultUpdater extends BaseUpdater {
    @Override
    public void update(Item item) {
      decrementSellIn(item);
      decrementQuality(item, item.isExpired() ? 2 : 1);
    }
  }

}
