package kz.kuanysh.bot.chain.interfaces;

import java.util.List;

@FunctionalInterface
public interface CheckListToNull<T> {
    List<T> isNullList(List<T> list);
}
