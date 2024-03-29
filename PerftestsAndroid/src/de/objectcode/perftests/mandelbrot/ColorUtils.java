package de.objectcode.perftests.mandelbrot;

public class ColorUtils {

  public static int HSBtoRGB(double hue, double saturation, double brightness) {
    int r = 0, g = 0, b = 0;
    if (saturation == 0) {
        r = g = b = (int) (brightness * 255.0f + 0.5f);
    } else {
        double h = (hue - Math.floor(hue)) * 6.0;
        double f = h - java.lang.Math.floor(h);
        double p = brightness * (1.0 - saturation);
        double q = brightness * (1.0 - saturation * f);
        double t = brightness * (1.0 - (saturation * (1.0f - f)));
        switch ((int) h) {
        case 0:
            r = (int) (brightness * 255.0f + 0.5f);
            g = (int) (t * 255.0f + 0.5f);
            b = (int) (p * 255.0f + 0.5f);
            break;
        case 1:
            r = (int) (q * 255.0f + 0.5f);
            g = (int) (brightness * 255.0f + 0.5f);
            b = (int) (p * 255.0f + 0.5f);
            break;
        case 2:
            r = (int) (p * 255.0f + 0.5f);
            g = (int) (brightness * 255.0f + 0.5f);
            b = (int) (t * 255.0f + 0.5f);
            break;
        case 3:
            r = (int) (p * 255.0f + 0.5f);
            g = (int) (q * 255.0f + 0.5f);
            b = (int) (brightness * 255.0f + 0.5f);
            break;
        case 4:
            r = (int) (t * 255.0f + 0.5f);
            g = (int) (p * 255.0f + 0.5f);
            b = (int) (brightness * 255.0f + 0.5f);
            break;
        case 5:
            r = (int) (brightness * 255.0f + 0.5f);
            g = (int) (p * 255.0f + 0.5f);
            b = (int) (q * 255.0f + 0.5f);
            break;
        }
    }
    return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
}

}
