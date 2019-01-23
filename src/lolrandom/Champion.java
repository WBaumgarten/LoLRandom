package lolrandom;

import java.awt.image.BufferedImage;

public class Champion {
    
    private String name;
    private BufferedImage pic, smallPic;
    private boolean ad, ap, melee, ranged, top, jungle, mid, supp, adc, owned;
    
    public Champion(String name, BufferedImage pic, BufferedImage smallPic, boolean ad, boolean ap, boolean melee, boolean ranged, boolean top, boolean jungle, boolean mid, boolean supp, boolean adc, boolean owned){
        this.name = name;
        this.pic = pic;
        this.smallPic = smallPic;
        this.ad = ad;
        this.ap = ap;
        this.melee = melee;
        this.ranged = ranged;
        this.top = top;
        this.jungle = jungle;
        this.mid = mid;
        this.supp = supp;
        this.adc = adc;
        this.owned = owned;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPic(BufferedImage pic) {
        this.pic = pic;
    }

    public void setSmallPic(BufferedImage smallPic) {
        this.smallPic = smallPic;
    }

    public void setAd(boolean ad) {
        this.ad = ad;
    }

    public void setAp(boolean ap) {
        this.ap = ap;
    }

    public void setMelee(boolean melee) {
        this.melee = melee;
    }

    public void setRanged(boolean ranged) {
        this.ranged = ranged;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public void setJungle(boolean jungle) {
        this.jungle = jungle;
    }

    public void setMid(boolean mid) {
        this.mid = mid;
    }

    public void setSupp(boolean supp) {
        this.supp = supp;
    }

    public void setAdc(boolean adc) {
        this.adc = adc;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getPic() {
        return pic;
    }

    public BufferedImage getSmallPic() {
        return smallPic;
    }

    public boolean isOwned() {
        return owned;
    }

    public boolean isAd() {
        return ad;
    }

    public boolean isAp() {
        return ap;
    }

    public boolean isMelee() {
        return melee;
    }

    public boolean isRanged() {
        return ranged;
    }

    public boolean isTop() {
        return top;
    }

    public boolean isJungle() {
        return jungle;
    }

    public boolean isMid() {
        return mid;
    }

    public boolean isSupp() {
        return supp;
    }

    public boolean isAdc() {
        return adc;
    }
    
    
}