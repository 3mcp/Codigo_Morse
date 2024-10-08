import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Stack;

public class FloodFill {

    public static void floodFillPilha(BufferedImage image, int x, int y, Color newColor) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        int targetColor = image.getRGB(x, y);

        if (targetColor == newColor.getRGB()) return;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});

        int pixelsProcessed = 0;
        int saveInterval = 560;
        int step = 1;

        File outputDir = new File("resources");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        while (!stack.isEmpty()) {
            int[] point = stack.pop();
            int px = point[0];
            int py = point[1];

            if (px < 0 || px >= width || py < 0 || py >= height) continue;
            if (image.getRGB(px, py) != targetColor) continue;

            image.setRGB(px, py, newColor.getRGB());
            pixelsProcessed++;

            stack.push(new int[]{px + 1, py});
            stack.push(new int[]{px - 1, py});
            stack.push(new int[]{px, py + 1});
            stack.push(new int[]{px, py - 1});

            if (pixelsProcessed % saveInterval == 0) {
                File output = new File(outputDir, "pilhaPasso" + step + ".png");

                ImageIO.write(image, "png", output);
                System.out.println("Imagem salva como passo" + step + ".png");

                step++;
            }
        }

        File finalOutput = new File(outputDir, "pilhaPasso" + step + ".png");
        ImageIO.write(image, "png", finalOutput);
        System.out.println("Imagem final salva como passo" + step + ".png");
    }

    public static void main(String[] args) {
        try {
            File input = new File("C:\\Users\\evely\\OneDrive - Grupo Marista\\BES\\4P\\ESTRUTURA DE DADOS\\FloodFill\\resources/imagem5.png");
            BufferedImage image = ImageIO.read(input);

            Color newColor = Color.RED;

            int centerX = image.getWidth() / 2;
            int centerY = image.getHeight() / 2;

            floodFillPilha(image, centerX, centerY, newColor);

            System.out.println("Processo de pintura concluído!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
