
package br.pucrio.tecgraf.desafio.util;

import br.pucrio.tecgraf.desafio.model.NrSerie;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe utilitária para facilitação de tarefas
 * @author felip
 */
public abstract class Util 
{
    private static final int QTDMIN = 3;
    
    /**
     * Verifica se o texto possui exatamente o mínimo de caracteres informado
     * @param texto
     * @param qtdMin
     * @return 
     */
    public static boolean isPreenchidoExato(String texto, int qtdMin)
    {
        return (texto != null && texto.trim().length() == qtdMin);
    }
    
    /**
     * Verifica se o texto possui pelo menos o mínimo de caracteres informado
     * @param texto
     * @param qtdMin
     * @return 
     */
    public static boolean isPreenchido(String texto, int qtdMin)
    {
        return (texto != null && texto.trim().length() >= qtdMin);
    }
    
    /**
     * Verifica se o texto possui o mínimo de 3 caracteres
     * @param texto
     * @return 
     */
    public static boolean isPreenchido(String texto)
    {
        return isPreenchido(texto, QTDMIN);
    }
    
    /**
     * Verifica se a string possui a composição definida na documentação, levando em consideração que o preenchimento já foi previaamente verificado.
     * Neste método utilizo 2 formas distintas para verificar se uma sequencia é numerica ou não
     * @param texto
     * @return 
     */
    public static boolean isComposicaoCorreta(String texto)
    {
        //verifica se a primeira sequencia de 4 caracteres corresponde a um numérico utilizando regex
        //fonte: https://www.delftstack.com/pt/howto/java/how-to-check-if-a-string-is-a-number-in-java/#:~:text=Resultado%3A%20true-,Verifique%20se%20uma%20String%20%C3%A9%20um%20n%C3%BAmero%20utilizando%20a%20biblioteca,se%20contiver%20uma%20seq%C3%BC%C3%AAncia%20num%C3%A9rica.
        if(!texto.substring(0, 4).matches("[+-]?\\d*(\\.\\d+)?"))
            return false;     
        
        //verifica se o próximo trecho de 3 caracteres são letras, sendo essa verificação CASE SENSITIVE
        if(!texto.substring(4, 7).matches("[A-Z]*"))
            return false;
        
        //verifica se o trecho reservado bate com a configuração
        if(!texto.substring(7, 9).equals("XX"))
            return false;
        
        //extrai o caracter de tipo para a verificação
        Character tipo = texto.charAt(9);
        
        //verifica o tipo
        if(!tipo.equals('A') && !tipo.equals('M') && !tipo.equals('C'))
            return false;
        
        //verifica se a primeira sequencia de 4 caracteres corresponde a um numérico utilizando funcionalidades do Java 8
        //fonte: https://www.delftstack.com/pt/howto/java/how-to-check-if-a-string-is-a-number-in-java/#:~:text=Resultado%3A%20true-,Verifique%20se%20uma%20String%20%C3%A9%20um%20n%C3%BAmero%20utilizando%20a%20biblioteca,se%20contiver%20uma%20seq%C3%BC%C3%AAncia%20num%C3%A9rica.        
        return texto.substring(10, 13).chars().allMatch( Character::isDigit );
    }

    /**
     * Método responsável por fazer a leitura do arquivo de texto do nr de séries e retorná-lo como uma lista de objetos de nrseries
     * @param modelo
     * @return
     * @throws IOException 
     */
    public static List<NrSerie> lerArquivo(File modelo) throws IOException 
    {
        List<NrSerie> linhas = new ArrayList<>();
        String linha = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(modelo)));

        while ((linha = in.readLine()) != null) 
        {
            NrSerie numero = new NrSerie();
            
            int i = linha.lastIndexOf("-");
            if(i >= 0 )
            {
                //System.out.println("Entrei");
                numero.setNumero(linha.substring(0, i));                
                numero.setDV(linha.substring(i+1, linha.length()));
            }
            else
                numero.setNumero(linha);
            
            linhas.add(numero);
        }

        in.close();

        return linhas;
    }
    
    /**
     * Método responsável por fazer a leitura do arquivo de texto de paises e retorná-lo como um dicionário
     * @param modelo
     * @return
     * @throws IOException 
     */
    public static HashMap<String, String> lerArquivoPaises(File modelo) throws IOException 
    {
        HashMap<String, String> linhas = new HashMap<>();
        String linha = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(modelo)));

        while ((linha = in.readLine()) != null) 
        {
            String[] split = linha.split(";");
            linhas.put(split[0], split[1]);
        }

        in.close();

        return linhas;
    }
    
    /**
     * Método responsável por gravar um arquivo (de texto) com o conteúdo informado no destino informado
     * @param path
     * @param conteudo
     * @return
     * @throws IOException 
     */
    public static File gravarArquivo(String path, String conteudo) throws IOException 
    {        
        //verifica se o diretorio de destino existe, caso contrário cria-o           
        verificaExistenciaArquivoPasta(new File(path.substring(0, path.lastIndexOf(File.separator))), true);            
        
        File file = new File(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write(conteudo);
        writer.flush();
        writer.close();
        
        return file;
    }
    
    /**
     * Método responsável por verificar se um determinado diretório ou arquivo. Caso o diretório não exista tenta criá-lo se assim estiver definido no parametro criar
     * @param file
     * @param criar
     * @return 
     */
    public static boolean verificaExistenciaArquivoPasta(File file, boolean criar)
    {        
        if (!file.exists()) 
        {
            if(criar)
            {
                file.mkdirs(); //cria a cadeia de diretórios necessária para armazenamento do arquivo
                return true;
            }
            
            return false;
        }   
        
        return true;
    }
}
