
import influenzer.bot.cetecop.controle.HuxleyBot;
import influenzer.bot.cetecop.controle.UriBot;
import influenzer.bot.generic.controle.AbstractBot;
import java.util.Scanner;

public class Programa {
        
    public static void main(String[] args) {    
        Scanner in = new Scanner(System.in);

        InstagramBot bot;
        AbstractBot uribot;

        System.out.println("Digita a Opção Desejada\n"
                + "Atualizar Infomações do Banco\n"
                + "10 - Cetecop - URIBot\n"
                + "11 - Cetecop - The Huskley\n"
                + "100 - Faz algum teste especifico no Instagram Bot\n"
                + "0 - Reiniciar o Banco\n"
        );
        int x;

        try {
            x = Integer.parseInt(InfluUtil.getPropriedade(InfluUtil.AUTO_LOAD));

            if (x <= 0) {
                throw new Exception();
            }

            System.out.println("\n***************************"
                    + "\nEscolha automática opção: " + x
                    + "\n***************************\n");
        } catch (Exception e) {
            x = in.nextInt();
        }

        switch (x) {
            case 1:
                uribot = new UriBot();
                uribot.executa();
                break;
            case 2:
                uribot = new HuxleyBot();
                uribot.executa();
                break;
            case 100:
                bot = new InstagramBot();
                bot.testes();
                break;
            case 0:
                DaoProfile dao = new DaoProfile();
                break;
            default:
                break;
        }

        Runtime.getRuntime().exit(0);
    }

}
