package com.puxiansheng.www.common;

/**
 *
 */
public class ColorInfo {
    private String imgUrl;
//    private int vibrantColor = 0xFF999999;
//    private int vibrantDarkColor = 0xFF999999;
//    private int vibrantLightColor = 0xFF999999;
//    private int mutedColor = 0xFF999999;
//    private int mutedDarkColor = 0xFF999999;
//    private int mutedLightColor = 0xFF999999;

    private int vibrantColor = 0xFFFFFFFF;
    private int vibrantDarkColor = 0xFFFFFFFF;
    private int vibrantLightColor = 0xFFFFFFFF;
    private int mutedColor = 0xFFFFFFFF;
    private int mutedDarkColor = 0xFFFFFFFF;
    private int mutedLightColor = 0xFFFFFFFF;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public int getVibrantColor() {
        return vibrantColor;
    }

    public void setVibrantColor(int vibrantColor) {
        this.vibrantColor = vibrantColor;
    }

    public int getVibrantDarkColor() {
        return vibrantDarkColor;
    }

    public void setVibrantDarkColor(int vibrantDarkColor) {
        this.vibrantDarkColor = vibrantDarkColor;
    }

    public int getVibrantLightColor() {
        return vibrantLightColor;
    }

    public void setVibrantLightColor(int vibrantLightColor) {
        this.vibrantLightColor = vibrantLightColor;
    }

    public int getMutedColor() {
        return mutedColor;
    }

    public void setMutedColor(int mutedColor) {
        this.mutedColor = mutedColor;
    }

    public int getMutedDarkColor() {
        return mutedDarkColor;
    }

    public void setMutedDarkColor(int mutedDarkColor) {
        this.mutedDarkColor = mutedDarkColor;
    }

    public int getMutedLightColor() {
        return mutedLightColor;
    }

    public void setMutedLightColor(int mutedLightColor) {
        this.mutedLightColor = mutedLightColor;
    }
}
