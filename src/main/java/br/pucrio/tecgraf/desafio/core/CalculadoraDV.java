
package br.pucrio.tecgraf.desafio.core;

import br.pucrio.tecgraf.desafio.model.NrSerie;
import br.pucrio.tecgraf.desafio.util.Util;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author felip
 */
public class CalculadoraDV 
{
    /**
     * Valida e Calcula um digito verificador para o número de série informado
     * @param nrserie
     * @return
     * @throws Exception 
     */
    public String calcularDigitoVerificador(String nrserie) throws Exception
    {
        //verifica se a string está preenchida com a quantidade min de caracteres
        if(!Util.isPreenchidoExato(nrserie, 14))
            throw new Exception("O número de série informado (" + nrserie + ") não possui a quantidade de caracteres nescessários!");
        
        //verifica se a string possui a composição definida
        if(!Util.isComposicaoCorreta(nrserie))
            throw new Exception("O número de série informado (" + nrserie + ") não segue a composição correta!");
        
        //converte todo o nrserie e converte em array com seus correspondentes am cod ascii
        byte[] nrserieASCII = nrserie.getBytes(StandardCharsets.US_ASCII);
        
        //realiza a soma dos valores ascii
        int soma = 0;        
        for(int i = 0; i < 14; i++)
            soma += nrserieASCII[i];
        
        int resto = -1;
        
        if(soma > 0)
            resto = soma % 16; //pega o resto da divisão
        
        //converte o resto e retorna seu valor em Hexa
        return Integer.toHexString(resto).toUpperCase();
    }
    
    /**
     * Troca os numeros informados na lista sem DV pelo seu correspondente com DV
     * @param nrsSeries
     * @return
     * @throws Exception 
     */
    public List<NrSerie> calcularDigitoVerificador(List<NrSerie> nrsSeries) throws Exception
    {
        if(nrsSeries == null || nrsSeries.isEmpty())
            throw new Exception("Favor informar pelo menos 1 número de série para que a ação seja efetuada!");
        
        List<NrSerie> nrsSeriesCDV = new ArrayList<>();
        
        int qtdNrs = nrsSeries.size();
        for(int i = 0; i< qtdNrs; i++)
        {
            NrSerie numero = nrsSeries.get(i);
            
            //instancia novo objeto para calcular o DV, todavia no seta no costrutor o DV anterior para que ao setar o calculado verifique se o anteriror estava ou não correto
            NrSerie numeroCalculado = new NrSerie(numero.getNumero(), numero.getDV());
            numeroCalculado.setDV(calcularDigitoVerificador(numero.getNumero()));
            
            nrsSeriesCDV.add(numeroCalculado);
        }
        return nrsSeriesCDV;
    }
    
    /**
     * Método responsável por gerar o relatório 
     * @param nrSeries
     * @param paises
     * @return
     * @throws Exception 
     */
    public HashMap<String, Integer> geraRelatorio(List<NrSerie> nrSeries, HashMap<String, String> paises) throws Exception
    {
        if(nrSeries == null || nrSeries.isEmpty())
            throw new Exception("Para a geração do relatório deve ser informado pelo menos 1 número de série!");
        
        if(paises == null || paises.isEmpty())
            throw new Exception("Para a geração do relatório deve ser informado pelo menos 1 pais!");
        
        HashMap<String, Integer> relatorio = new HashMap<>();
        
        for(NrSerie numero : nrSeries)
        {
            String sigla = numero.getNumero().substring(4, 7);
        
            //verificar se é automovel
            if(numero.getNumero().charAt(9) == 'A')
            {   
                if(paises.containsKey(sigla))
                {
                    String pais = paises.get(sigla);

                    if(relatorio.containsKey(pais))
                    {
                        relatorio.replace(pais, relatorio.get(pais) + 1);
                    } 
                    else
                        relatorio.put(pais, 1);
                }
            }
        }
        
        return relatorio;
    }
}
