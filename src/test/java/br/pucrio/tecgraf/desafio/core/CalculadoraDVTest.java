
package br.pucrio.tecgraf.desafio.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author felip
 */
public class CalculadoraDVTest 
{
    final CalculadoraDV calc = new CalculadoraDV();
    
    public CalculadoraDVTest() {
    }

    @Test
    public void testCalcularDigitoVerificador_StringComposicaoCorreta() throws Exception 
    {
        final String nr =  "2122BRAXXA3348";//apos o teste a string foi corrigida
        final String esperado = "F";
        final String obtido = calc.calcularDigitoVerificador(nr);
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testCalcularDigitoVerificador_StringComposicaoINCorretaQtdCaracterMaior() throws Exception 
    {
        final String nr =  "2122BRAXXA3348";//apos o teste a string foi corrigida
        final String esperado = "F";
        final String obtido = calc.calcularDigitoVerificador(nr);
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testCalcularDigitoVerificador_StringComposicaoINCorretaQtdCaracterMenor() throws Exception 
    {
        final String nr =  "2122BRAXXA3348";//apos o teste a string foi corrigida
        final String esperado = "F";
        final String obtido = calc.calcularDigitoVerificador(nr);
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testCalcularDigitoVerificador_StringComposicaoINCorreta() throws Exception 
    {
        final String nr =  "2122BRAXXA3348";//apos o teste a string foi corrigida
        final String esperado = "F";
        final String obtido = calc.calcularDigitoVerificador(nr);
        assertEquals(esperado, obtido);
    }

    @Test
    public void testCalcularDigitoVerificador_List() throws Exception 
    {
        
    }

    @Test
    public void testGeraRelatorio() throws Exception {
    }
    
}
