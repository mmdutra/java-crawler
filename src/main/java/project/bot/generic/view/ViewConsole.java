package influenzer.bot.generic.view;


public class ViewConsole implements IView {

    private boolean imprimir;

    public ViewConsole(boolean imprimir) {
        this.imprimir = imprimir;
    }

    @Override
    public void msgComum(String msg) {
        if (imprimir) {
            System.out.println(msg);
        }
    }

    @Override
    public void msgErro(String msg) {
        if (imprimir) {
            System.err.println(msg);
        }
    }

    @Override
    public void msgComum(Object obj) {
        if (imprimir) {
            System.out.println(obj.toString());
        }
    }

    @Override
    public void msgErro(Object msg) {
        if (imprimir) {
            System.err.println(msg.toString());
        }

    }

    @Override
    public void msgChamativa(Object msg) {
        if(imprimir){
            imprimeLinhaPadrao(100, "0");
            imprimeLinhaPadrao(100, "0");
            System.out.println(msg);
            imprimeLinhaPadrao(100, "0");
            imprimeLinhaPadrao(100, "0");
        }
    }

    private void imprimeLinhaPadrao(int qtdRepeticoes,String el){
        for (int i = 0; i < qtdRepeticoes; i++) {
            System.out.print(el);
        }
        System.out.println("");
    }
    @Override
    public void msgChamativa(String msg) {
        msgChamativa((Object)msg);
    }
    
    
}
