package com.squarebear.web.util;

import org.openqa.selenium.Dimension;

public enum Device {
    DESKTOP(1366, 768),
    IPHONE_6(375, 667, 2.0, "Mozilla/5.0 (iPhone; CPU iPhone OS 9_2 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13C75 Safari/601.1"),
    IPHONE_6PLUS(414, 736, 3.0, "Mozilla/5.0 (iPhone; CPU iPhone OS 9_2 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13C75 Safari/601.1"),
    IPAD(768, 1024, 2.0, "Mozilla/5.0 (iPad; CPU OS 9_0 like Mac OS X) AppleWebKit/601.1.16 (KHTML, like Gecko) Version/8.0 Mobile/13A171a Safari/600.1.4");

    private int width;
    private int height;
    private boolean mobile;
    private double pixelRatio;
    private String userAgent;

    Device(int width, int height) {
        this(width, height, false, 1.0, null);
    }

    Device(int width, int height, double pixelRatio, String userAgent) {
        this(width, height, true, pixelRatio, userAgent);
    }

    Device(int width, int height, boolean mobile, double pixelRatio, String userAgent) {
        this.width = width;
        this.height = height;
        this.mobile = mobile;
        this.pixelRatio = pixelRatio;
        this.userAgent = userAgent;
    }

    public Dimension getScreenSize() {
        return new Dimension(width, height);
    }

    public boolean isMobile() {
        return mobile;
    }

    public double getPixelRatio() {
        return pixelRatio;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
