
package br.pucrio.tecgraf.desafio.view.resources;

import br.pucrio.tecgraf.desafio.model.NrSerie;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author felip
 */
public class NrSerieTableModel extends AbstractTableModel 
{
    private List<NrSerie> nrsSeries;
    private String colunas[] = {"Nr. Série", "DV"};
    private final int COLUNA_NRSERIE = 0;
    private final int COLUNA_DV = 1;
    
    public NrSerieTableModel(List<NrSerie> nrsSeries) 
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
            case COLUNA_NRSERIE:
                return String.class;            
            case COLUNA_DV:
                return String.class;            
            default:
                return String.class;
        }
    }

    //preenche cada célula da tabela
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NrSerie nrSerie = this.nrsSeries.get(rowIndex);

        switch (columnIndex) {
            case COLUNA_NRSERIE:
                return nrSerie.getNumero();
            case COLUNA_DV:
                return nrSerie.getDV();
        }
        return null;
    }
    
    //altera o valor do objeto de acordo com a célula editada
    //e notifica a alteração da tabela, para que ela seja atualizada na tela
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //o argumento recebido pelo método é do tipo Object
        //mas como nossa tabela é de funcionários, é seguro(e até recomendável) fazer o cast de suas propriedades
        NrSerie nrString = this.nrsSeries.get(rowIndex);
        //de acordo com a coluna, ele preenche a célula com o valor
        //respectivo do objeto de mesmo indice na lista
        switch (columnIndex) {
            case COLUNA_NRSERIE:
                nrString.setNumero(aValue.toString());
                break;      
            case COLUNA_DV:
                nrString.setDV(aValue.toString());
                break;   
        }
        //este método é que notifica a tabela que houve alteração de dados
        fireTableDataChanged();
    }
    
}
