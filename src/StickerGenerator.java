import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class StickerGenerator {

    public void create(String receivedUrl, String fileName) throws Exception {
        // Read image
        InputStream inputStream = new URL(receivedUrl).openStream();
        BufferedImage imageReceived = ImageIO.read(inputStream);

        // Create translucent image on memory
        int widthSticker = imageReceived.getWidth();
        int heightSticker = imageReceived.getHeight();
        heightSticker += (int) (0.05 * heightSticker);

        BufferedImage sticker = new BufferedImage(widthSticker, heightSticker, BufferedImage.TRANSLUCENT);

        // Copy received image to translucent image
        Graphics2D graphics = (Graphics2D) sticker.getGraphics();
        graphics.drawImage(imageReceived, 0, 0, null);

        // Configure font
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, (int) (0.05 * widthSticker));
        graphics.setColor(Color.BLACK);
        graphics.setFont(font);

        // Write message
        graphics.drawString(fileName, (int) (0.05 * widthSticker), heightSticker - 20);

        // Write image in a file .png
        ImageIO.write(sticker, "png", new File("output/" + fileName.replace(" ", "_")));
    }
}
