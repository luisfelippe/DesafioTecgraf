
package br.pucrio.tecgraf.desafio.view;

import br.pucrio.tecgraf.desafio.model.NrSerie;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interface que define os métodos necessários as telas para que possa ser utilizado um único controlador
 * @author felip
 */
public interface ITela 
{
    /**
     * Retorna o valor da constante da tela que contem o nome do arquivo de saida gravado
     * @return 
     */
    String getNOMEARQUIVO();
    
    /**
     * retorna o path de saida do arquivo gravado
     * @return 
     */
    String getPathSaida();
    
    /**
     * define o path de saida do arquivo gravado
     * @param pathSaida 
     */
    void setPathSaida(String pathSaida);
    
    /**
     * retorna a coleção de entrada de dados carregada do arquivo
     * @return 
     */
    List<NrSerie> getEntrada();
    
    /**
     * define a coleção de entrada de dados carregada do arquivo
     * @param entrada 
     */
    void setEntrada(List<NrSerie> entrada);
    
    /**
     * retorna o componente de tela de escolha/busca de arquivo
     * @return 
     */
    JFileChooser getFileChoser();
    
    /**
     * define o compornente de tela de escolha/busca de arquivo
     * @param fileChoser 
     */
    void setFileChoser(JFileChooser fileChoser);
    
    /**
     * retorna o componente de tela jTextField1 que exibe o path do arquivo de saida
     * @return 
     */
    JTextField getjTextField1();
    
    /**
     * define o componente de tela jTextField1 que exibe o path do arquivo de saida
     * @param jTextField1 
     */
    void setjTextField1(JTextField jTextField1);
    
    /**
     * retorna o compolente de tela que contem a exibição dos dados de entrada
     * @return 
     */
    JTable getTblLeitura();
    
    /**
     * define o compolente de tela que contem a exibição dos dados de entrada 
     * @param tblLeitura
     */
    void setTblLeitura(JTable tblLeitura);
    
    /**
     * retorna o compolente de tela que contem a exibição dos dados de saída, ou seja os dados tratados conforme a tela
     * @return 
     */
    JTable getTblSaida();
    
    /**
     * define o compolente de tela que contem a exibição dos dados de saída, ou seja os dados tratados conforme a tela 
     * @param tblSaida
     */
    void setTblSaida(JTable tblSaida);
    
    /**
     * retorna o compolente de tela que contem o path do arquivo de entrada
     * @return 
     */
    JTextField getTfPath();
    
    /**
     * define o compolente de tela que contem o path do arquivo de entrada 
     * @param tfPath
     */
    void setTfPath(JTextField tfPath);
    
    /**
     * retorna a coleção de dados, tratada, de paises informado no arquivo de entrada de dados de paises
     * @return 
     */
    HashMap<String, String> getPaises();
    
    /**
     * define a coleção de dados, tratada, de paises informado no arquivo de entrada de dados de paises 
     * @param paises
     */
    void setPaises(HashMap<String, String> paises);
    
    /**
     * retorna a coleção de dados, tratada, de saída do relatório
     * @return 
     */
    HashMap<String, Integer> getSaidaRelatorio();
    
    /**
     * define a coleção de dados, tratada, de saída do relatório 
     * @param saida
     */
    void setSaidaRelatorio(HashMap<String, Integer> saida);
    
    /**
     * retorna a coleção de nr série de saída
     * @return 
     */
    List<NrSerie> getSaida();
    
    /**
     * define a coleção de nr série de saída 
     * @param saida
     */
    void setSaida(List<NrSerie> saida);
    
    /**
     * retorna o componente de tela encarregado de exibir o path do arquivo de dados dos paises
     * @return 
     */
    JTextField getTfPathPaises();
    
    /**
     * define o componente de tela encarregado de exibir o path do arquivo de dados dos paises 
     * @param tfPathPaises
     */
    void setTfPathPaises(JTextField tfPathPaises);
}
