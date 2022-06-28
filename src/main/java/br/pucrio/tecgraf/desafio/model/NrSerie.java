
package br.pucrio.tecgraf.desafio.model;

import java.util.Objects;

/**
 *
 * @author felip
 */
public class NrSerie 
{
    private String numero;
    private String DV;
    private String verificacao;

    public NrSerie() {
    }

    public NrSerie(String numero) {
        this.numero = numero;
    }
    
    public NrSerie(String numero, String DV) {
        this.numero = numero;
        this.DV = DV;
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDV() {
        return DV;
    }

    public void setDV(String DV) 
    {
        if(this.DV != null && this.DV.equals(DV))
            this.verificacao = "VERDADEIRO";
        else if(this.DV != null && !this.DV.equals(DV))
            this.verificacao = "FALSO";
            
        this.DV = DV;
    }

    public String getVerificacao() {
        return verificacao;
    }

    public void setVerificacao(String verificacao) {
        this.verificacao = verificacao;
    }

    @Override
    public String toString() {
        return  numero + ((DV != null && !DV.trim().isEmpty()) ? "-" + DV : "") + ((verificacao != null && !verificacao.trim().isEmpty()) ? " " + verificacao : "");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.numero);
        hash = 29 * hash + Objects.hashCode(this.DV);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NrSerie other = (NrSerie) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return Objects.equals(this.DV, other.DV);
    }
}
