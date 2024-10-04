public class ArvoreMorse {
    private No raiz;

    public ArvoreMorse() {
        raiz = new No(""); // A raiz é inicializada
        construirArvore();
    }

    public void adicionar(String valor, String codigoMorse) {
        No atual = raiz;
        for (char c : codigoMorse.toCharArray()) {
            if (c == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new No(""); // Cria um novo nó se não existir
                }
                atual = atual.esquerda; // Move para o filho à esquerda
            } else if (c == '-') {
                if (atual.direita == null) {
                    atual.direita = new No(""); // Cria um novo nó se não existir
                }
                atual = atual.direita; // Move para o filho à direita
            }
        }
        atual.valor = valor; // Atribui o valor no final do código Morse
    }

    private void construirArvore() {
        adicionar("A", ".-");
        adicionar("B", "-...");
        adicionar("C", "-.-.");
        adicionar("D", "-..");
        adicionar("E", ".");
        adicionar("F", "..-.");
        adicionar("G", "--.");
        adicionar("H", "....");
        adicionar("I", "..");
        adicionar("J", ".---");
        adicionar("K", "-.-");
        adicionar("L", ".-..");
        adicionar("M", "--");
        adicionar("N", "-.");
        adicionar("O", "---");
        adicionar("P", ".--.");
        adicionar("Q", "--.-");
        adicionar("R", ".-.");
        adicionar("S", "...");
        adicionar("T", "-");
        adicionar("U", "..-");
        adicionar("V", "...-");
        adicionar("W", ".--");
        adicionar("X", "-..-");
        adicionar("Y", "-.--");
        adicionar("Z", "--..");
        adicionar("1", ".----");
        adicionar("2", "..---");
        adicionar("3", "...--");
        adicionar("4", "....-");
        adicionar("5", ".....");
        adicionar("6", "-....");
        adicionar("7", "--...");
        adicionar("8", "---..");
        adicionar("9", "----.");
        adicionar("0", "-----");
    }

    public No getRaiz() {
        return raiz; // Método para acessar a raiz da árvore
    }

    public boolean validarCodigoMorse(String codigo) {
        for (char c : codigo.toCharArray()) {
            if (c != '.' && c != '-' && c != '/') {
                return false; // Verifica se o código Morse é válido
            }
        }
        return true;
    }

    public String decodificarLetra(String codigoMorse) {
        if (!validarCodigoMorse(codigoMorse)) {
            return "Código Morse inválido!";
        }

        No atual = raiz;

        for (char c : codigoMorse.toCharArray()) {
            if (c == '.') {
                atual = atual.esquerda; // Move para a esquerda
            } else if (c == '-') {
                atual = atual.direita; // Move para a direita
            }

            if (atual == null) {
                return "Código Morse inválido!";
            }
        }

        return !atual.valor.isEmpty() ? atual.valor : "Código Morse inválido!";
    }

    public String decodificarFrase(String codigoMorse) {
        StringBuilder fraseDecodificada = new StringBuilder();
        String[] palavras = codigoMorse.split(" / ");

        for (String palavra : palavras) {
            String[] letras = palavra.split(" ");

            for (String letra : letras) {
                fraseDecodificada.append(decodificarLetra(letra));
            }

            fraseDecodificada.append(" "); // Adiciona um espaço entre palavras
        }

        return fraseDecodificada.toString().trim();
    }
}
