package ray.droid.com.droidflappybird;

/**
 * Created by Robson on 20/12/2017.
 */

public class Jogador implements Comparable<Jogador>  {
    private String nome;
    private int pontos;

    public Jogador() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public int compareTo(Jogador jogador) {
        return  this.getPontos() - jogador.getPontos();
    }

}
