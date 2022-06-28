
package br.pucrio.tecgraf.desafio.controler;

import br.pucrio.tecgraf.desafio.core.CalculadoraDV;
import br.pucrio.tecgraf.desafio.model.NrSerie;
import br.pucrio.tecgraf.desafio.util.Util;
import br.pucrio.tecgraf.desafio.view.ITela;
import br.pucrio.tecgraf.desafio.view.TelaGeraDV;
import br.pucrio.tecgraf.desafio.view.TelaRelatorio;
import br.pucrio.tecgraf.desafio.view.TelaVerificaDV;
import br.pucrio.tecgraf.desafio.view.resources.NrSerieCVerifDVTableModel;
import br.pucrio.tecgraf.desafio.view.resources.NrSerieTableModel;
import br.pucrio.tecgraf.desafio.view.resources.RelatorioTableModel;
import java.io.File;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe responsável por definir um controlador das janelas utilizadas no programa. Nela estão as ações executadas pelas telas
 * @author felip
 */
public class ControlerCalculadoraDV 
{
    //bean responsável pelo cálculo e verificação dos Digitos Verificadores. poderia ser utilizado um injetor de depêndencia como o CDI se estivéssemos usando JEE ou Spring
    private final CalculadoraDV calc = new CalculadoraDV();
    
    /**
     * Método responsável por tratar e solicitar ao utilitário a gravação do resultado
     * @param tela 
     */
    public void gravarResultado(ITela tela)
    {
        try
        {
            String conteudo = "";
            
            //verifica se estamos trabalhando com a tela de relatório ou outra tela
            if(tela instanceof TelaRelatorio)
            {
                //verifica se o conteúdo ddo arquivo de saída está definido
                if(tela.getSaidaRelatorio() != null && !tela.getSaidaRelatorio().isEmpty())
                {
                    //converte o HashMap para string de conteúdo que será utilizada na gravação do arquivo
                    for(Map.Entry<String, Integer> linha : tela.getSaidaRelatorio().entrySet())
                        conteudo += linha.getKey() + " - " + linha.getValue() + "\n";
                }
            }
            else
            {
                //verifica se o conteudo do arquivo de saída está defrinido
                if(tela.getSaida() != null && !tela.getSaida().isEmpty())
                {
                    //converte a lista de nr series em string para gravação no arquivo
                    for(NrSerie nr : tela.getSaida())
                        conteudo += nr + "\n";                    
                }
            }   
            
            //solicita ao utilitário a gravação do arquivo em disco no path de saida com o determinado conteúdo previamente tratado
            Util.gravarArquivo(tela.getPathSaida(), conteudo);
            
            JOptionPane.showMessageDialog((JFrame)tela, "Arquivo de saída gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog((JFrame)tela, "Aconteceu algum erro ao gravar o arquivo de saída: \n" + ex.getMessage() + "\n" + ex.getLocalizedMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Método responsável por solicitar a carga e tratar od dados carregados do arquivo de fonte dos nrs séries
     * @param tela 
     */
    public void carregaNrSeries(ITela tela)
    {        
        int returnVal = tela.getFileChoser().showOpenDialog((JFrame)tela);
        
        //verifica se o usuário escolheu um arquivo ou fechou a janela
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            //resgata o arquivo escolhido pelo usuário
            File file = tela.getFileChoser().getSelectedFile();
            try 
            {
                //define o texto do componente com o path do aquivo de entrada
                tela.getTfPath().setText(file.getAbsolutePath());
                
                //define o path do arquivo de saída
                tela.setPathSaida(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator)) 
                                    + File.separator + "resultado" 
                                    + File.separator + tela.getNOMEARQUIVO());
                
                //define o texto do componente com o path do aruivo de saida
                tela.getjTextField1().setText(tela.getPathSaida());
                
                //define o conteúdo do arquivo de entrada
                tela.setEntrada(Util.lerArquivo(file));
                
                //define o conteudo do componente que exibe os dados de entrada, instanciando o modelo responsável pela exibição dos dados
                tela.getTblLeitura().setModel(new NrSerieTableModel(tela.getEntrada()));
                
                //verifica se a tela em questão é a tela de relatório para a definição do conteúdo do arquivo de saída, bem como a definição do conteudo do componente de exibição dos dados de saída
                if(tela instanceof TelaRelatorio)    
                {
                    //verifica se a lista de paises foi definida primeiro do que os dados para a geração da exibição do relatório
                    if(((TelaRelatorio) tela).isGerar())
                    {
                        //define o conteudo dos dados de saída do relatório
                        tela.setSaidaRelatorio(this.calc.geraRelatorio(tela.getEntrada(), tela.getPaises()));//gera o relatório
                        
                        //define o conteudo do componente que exibe os dados de saída através do modelo responsável pela exibição dos dados
                        tela.getTblSaida().setModel(new RelatorioTableModel(tela.getSaidaRelatorio()));
                        
                        //define o gerar como falso para recomeçar o ciclo
                        ((TelaRelatorio) tela).setGerar(false);
                    }
                }
                else
                    //caso não seja a tela de relatório define o conteudo de saída, calculando sempre o digito verificador
                    tela.setSaida(this.calc.calcularDigitoVerificador(tela.getEntrada()));
                
                //com base na tela em questão define qual modelo de exibição do componente responsável por exibir na tela os dados de saída
                if(tela instanceof TelaVerificaDV)
                    tela.getTblSaida().setModel(new NrSerieCVerifDVTableModel(tela.getSaida()));
                else if(tela instanceof TelaGeraDV)
                    tela.getTblSaida().setModel(new NrSerieTableModel(tela.getSaida()));                    
            } 
            catch (Exception ex) 
            {
                JOptionPane.showMessageDialog((JFrame)tela, "Aconteceu algum erro ao calcular o dígito verificador: \n" + ex.getMessage() + "\n" + ex.getLocalizedMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        } 
    }
    
    /**
     * Método responsável por solicitar ao utilitário o carregamento do arquivo de paises e tratar os dados
     * @param tela 
     */
    public void carregaPaises(ITela tela)
    {
        int returnVal = tela.getFileChoser().showOpenDialog((JFrame)tela);
        
        //verifica se o usuário escolheu um arquivo ou fechou a janela
        if (returnVal == JFileChooser.APPROVE_OPTION) 
        {
            //resgata o arquivo escolhido pelo usuário
            File file = tela.getFileChoser().getSelectedFile();
            try 
            {
                tela.getTfPathPaises().setText(file.getAbsolutePath());
                tela.setPaises(Util.lerArquivoPaises(file));
                
                //se os dados de entrada com os nr séries já foram carregados 
                if(tela.getEntrada() != null && !tela.getEntrada().isEmpty())
                {
                    //gera o relatório e preenche o conteudo do arquivo de saida com os dados do relatório
                    tela.setSaidaRelatorio(this.calc.geraRelatorio(tela.getEntrada(), tela.getPaises()) );
                    
                    //define o modelo de exibição dos dados de saída
                    tela.getTblSaida().setModel(new RelatorioTableModel(tela.getSaidaRelatorio()));
                }
                else
                    //caso os dados de entrada dos nr séries não tenhasm sido carregados define que o relatório seja gerado quando ele for carregado
                    ((TelaRelatorio) tela).setGerar(true); //encarrega ao outro método de gerar o relatório
            } 
            catch (Exception ex) 
            {
                JOptionPane.showMessageDialog((JFrame) tela, "Aconteceu algum erro ao calcular o dígito verificador: \n" + ex.getMessage() + "\n" + ex.getLocalizedMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
