package ray.droid.com.droidflappybird;

/**
 * Created by Robson on 20/12/2017.
 */

public class Jogador implements Comparable<Jogador>  {
    private String nome;
    private String pontos;

    public Jogador() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    @Override
    public int compareTo(Jogador jogador) {
        return  this.getPontos().compareTo(jogador.getPontos());
    }

}
