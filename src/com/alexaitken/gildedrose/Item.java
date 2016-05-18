/**
 * 
 */
package com.alexaitken.gildedrose;

public class Item {

  private String name;
  private int sellIn;
  private int quality;

  public Item(String name, int sellIn, int quality) {
    super();
    this.name = name;
    this.sellIn = sellIn;
    this.quality = quality;
  }

  public String getName() {
    return name;
  }

  public boolean isExpired() {
    return sellIn <= 0;
  }

  public int getSellIn() {
    return sellIn;
  }

  public void setSellIn(int sellIn) {
    this.sellIn = sellIn;
  }

  public int getQuality() {
    return quality;
  }

  public void reduceQualityIfPossible() {
    int decrementBy = isExpired() ? 2 : 1;
    quality = Math.max(quality - decrementBy, 0);
  }

  public void increaseQualityIfPossible() {
    if (!getsBetterWithAge()) {
      throw new UnsupportedOperationException();
    }
    if (isExpired() && uselessWhenOver()) {
      quality = 0;
      return;
    }
    int increaseBy = 1;
    if ("Backstage passes to a TAFKAL80ETC concert".equals(name)) {
      if (sellIn < 10) {
        increaseBy++;
      }
      if (sellIn < 5) {
        increaseBy++;
      }
    }
    quality = Math.min(quality + increaseBy, 50);
  }

  private boolean uselessWhenOver() {
    return "Backstage passes to a TAFKAL80ETC concert".equals(name);
  }

  public boolean getsBetterWithAge() {
    return "Aged Brie".equals(name) || "Backstage passes to a TAFKAL80ETC concert".equals(name);
  }

  public boolean neverChanges() {
    return "Sulfuras, Hand of Ragnaros".equals(name);
  }
}
