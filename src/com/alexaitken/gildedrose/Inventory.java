package com.alexaitken.gildedrose;

public class Inventory {

  private Item[] items;

  public Inventory(Item[] items) {
    this.items = items;
  }

  public Inventory() {
    items = new Item[] {
      new Item("+5 Dexterity Vest", 10, 20),
      new Item("Aged Brie", 2, 0),
      new Item("Elixir of the Mongoose", 5, 7),
      new Item("Sulfuras, Hand of Ragnaros", 0, 80),
      new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
      new Item("Conjured Mana Cake", 3, 6) };
  }

  public void updateQuality() {
    for (int i = 0; i < items.length; i++) {
      Item item = items[i];
      updateItem(item);
    }
  }

  private void updateItem(Item item) {
    if (item.neverChanges()) {
      return;
    }

    if (item.getsBetterWithAge()) {
      item.increaseQualityIfPossible();
    } else {
      item.reduceQualityIfPossible();
    }

    item.setSellIn(item.getSellIn() - 1);

    // if sellIn is negative, we are out of time
    if (item.getSellIn() < 0) {
      if (item.getsBetterWithAge()) {
        if (item.uselessWhenOver()) {
          item.setQuality(0);
        } else {
          item.increaseQualityIfPossible();
        }
      } else {
        item.reduceQualityIfPossible();
      }
    }
  }
}
