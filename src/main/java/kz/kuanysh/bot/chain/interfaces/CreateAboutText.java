package kz.kuanysh.bot.chain.interfaces;

import kz.kuanysh.bot.model.User;

@FunctionalInterface
public interface CreateAboutText {
    String getAboutText(User user);
}
