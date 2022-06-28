/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.pucrio.tecgraf.desafio.view.resources;

import br.pucrio.tecgraf.desafio.model.NrSerie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author felip
 */
public class RelatorioTableModel extends AbstractTableModel 
{
    private HashMap<String, Integer> nrsSeries;
    private String colunas[] = {"País", "Quantidade"};
    private final int COLUNA_PAIS = 0;
    private final int COLUNA_QTD = 1;
    
    public RelatorioTableModel(HashMap<String, Integer> nrsSeries) 
    {
        this.nrsSeries = nrsSeries;
    }   
    
    //retorna se a célula é editável ou não
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    //retorna o total de itens(que virarão linhas) da nossa lista
    @Override
    public int getRowCount() 
    {
        return this.nrsSeries.size();
    }

    //retorna o total de colunas da tabela
    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    //retorna o nome da coluna de acordo com seu indice
    @Override
    public String getColumnName(int indice) {
        return colunas[indice];
    }

    //determina o tipo de dado da coluna conforme seu indice
    @Override
    public Class<?> getColumnClass(int columnIndex) 
    {
        switch (columnIndex) {
            case COLUNA_PAIS:
                return String.class;            
            case COLUNA_QTD:
                return Integer.class;   
            default:
                return String.class;
        }
    }

    //preenche cada célula da tabela
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {        
        int i = 0;        
        Entry<String, Integer> row = null;
        for(Entry<String, Integer> linha : this.nrsSeries.entrySet())
        {
            if(rowIndex == i)
            {
                row = linha;
                break;
            }
            
            i++;
        }

        switch (columnIndex) 
        {
            case COLUNA_PAIS:
                return (row != null) ? row.getKey() : null;
            case COLUNA_QTD:
                return (row != null) ? row.getValue() : null;
        }
        return null;
    }
    
    //altera o valor do objeto de acordo com a célula editada
    //e notifica a alteração da tabela, para que ela seja atualizada na tela
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
    }
    
}
