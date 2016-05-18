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

  public void setName(String name) {
    this.name = name;
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
    if (quality > 0) {
      quality = quality - 1;
    }
  }

  public void increaseQualityIfPossible() {
    if (quality < 50) {
      quality = quality + 1;
    }
  }

  public boolean uselessWhenOver() {
    return "Backstage passes to a TAFKAL80ETC concert".equals(name);
  }

  public boolean getsBetterWithAge() {
    return "Aged Brie".equals(name) || "Backstage passes to a TAFKAL80ETC concert".equals(name);
  }

  public boolean neverChanges() {
    return "Sulfuras, Hand of Ragnaros".equals(name);
  }

  public void setQuality(int quality) {
    this.quality = quality;
  }
}
