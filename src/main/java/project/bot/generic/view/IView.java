/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package influenzer.bot.generic.view;

/**
 *
 * @author mpcsj
 */
public interface IView {
    public void msgComum(String msg);
    public void msgComum(Object obj);
    public void msgErro(String msg);
    public void msgErro(Object msg);
    public void msgChamativa(String msg);
    public void msgChamativa(Object msg);
}
