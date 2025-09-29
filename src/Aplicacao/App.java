// Imports
package Aplicacao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class App {
    // Atributos para redirecionamento de I/O;
    private Scanner in = new Scanner(System.in);  // Atributo para entrada padrão (teclado);
    private final String nomeArquivoEntrada = "dadosin.txt";  // Nome do arquivo de entrada de dados;
    private final String nomeArquivoSaida = "dadosout.txt";  // Nome do arquivo de saída de dados;

    public App() {
        redirecionaEntrada();    // Redireciona Entrada para arquivos;
        redirecionaSaida();    // Redireciona Saída para arquivos;
    }

    // Responsável pela utilização e ordenação da aplicação correta dos métodos;
    // Chame esse método para executar o código na Main.java;
    public void executar() {

    }

    // Redireciona Entrada de dados para arquivos em vez de teclado;
    // Chame este método para redirecionar a leitura de dados para arquivos;
    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            in = new Scanner(streamEntrada);   // Usa como entrada um arquivo;
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal;
        in.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal;
    }

    // Redireciona Saída de dados para arquivos em vez da tela (terminal);
    // Chame este método para redirecionar a escrita de dados para arquivos;
    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saída um arquivo;
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal;
    }


}