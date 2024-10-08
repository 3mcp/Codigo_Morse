import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class FloodFill1 {

    public static class Fila<T> {
        private int top = -1;
        private int base = 0;
        private T[] data;


        public Fila(int size) {
            this.data = (T[]) new Object[size];
        }


        public void add(T s) {
            if (isFull()) {
                throw new RuntimeException("Fila cheia");
            }
            top = (top + 1) % data.length;
            data[top] = s;
        }

        public T remove() {
            if (isEmpty()) {
                throw new RuntimeException("Fila vazia");
            }
            T item = data[base];
            base = (base + 1) % data.length;
            return item;
        }

        public void clear() {
            top = -1;
            base = 0;
            data = (T[]) new Object[data.length];
        }

        public boolean isFull() {
            return (top + 1) % data.length == base && data[base] != null;
        }

        public boolean isEmpty() {
            return top == -1 || (base == (top + 1) % data.length && data[base] == null);
        }
    }

    public static void floodFillWithQueue(BufferedImage image, int x, int y, Color newColor) {
        int width = image.getWidth();
        int height = image.getHeight();

        int targetColor = image.getRGB(x, y);
        int replacementColor = newColor.getRGB();

        if (targetColor == replacementColor) {
            return;
        }

        Fila<int[]> queue = new Fila<>(width * height);
        queue.add(new int[]{x, y});

        int pixelsProcessed = 0;
        int saveInterval = 560;
        int step = 1;

        File outputDir = new File("resources");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        while (!queue.isEmpty()) {
            int[] currentPoint = queue.remove();
            int cx = currentPoint[0];
            int cy = currentPoint[1];

            if (cx < 0 || cx >= width || cy < 0 || cy >= height) {
                continue;
            }

            if (image.getRGB(cx, cy) != targetColor) {
                continue;
            }

            image.setRGB(cx, cy, replacementColor);
            pixelsProcessed++;

            queue.add(new int[]{cx - 1, cy});
            queue.add(new int[]{cx + 1, cy});
            queue.add(new int[]{cx, cy - 1});
            queue.add(new int[]{cx, cy + 1});

            if (pixelsProcessed % saveInterval == 0) {
                File output = new File(outputDir, "filaPasso" + step + ".png");
                try {
                    ImageIO.write(image, "png", output);
                    System.out.println("Imagem salva como passo" + step + ".png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                step++;
            }
        }

        File finalOutput = new File(outputDir, "filaPasso" + step + ".png");
        try {
            ImageIO.write(image, "png", finalOutput);
            System.out.println("Imagem final salva como passo" + step + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\evely\\OneDrive - Grupo Marista\\BES\\4P\\ESTRUTURA DE DADOS\\FloodFill\\resources/cacto.png"));

            int startX = image.getWidth() / 2;
            int startY = image.getHeight() / 2;
            Color newColor = Color.yellow;

            floodFillWithQueue(image, startX, startY, newColor);

            System.out.println("Processo de pintura concluído!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
