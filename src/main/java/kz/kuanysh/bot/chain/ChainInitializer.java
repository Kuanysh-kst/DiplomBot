package kz.kuanysh.bot.chain;

import kz.kuanysh.bot.chain.chains.*;

public class ChainInitializer {

    public static DialogStateChain initChain() {
//        DialogStateChain finishChain = new FinishDialogChain(null);
//        DialogStateChain resultChain = new ResultDialogChain(finishChain);
        DialogStateChain categoryChain = new GetNameDialogChain(null);
        DialogStateChain choiceChain = new ChoiceDialogChiang(categoryChain);
        DialogStateChain statusChain = new CategoryDialogChain(choiceChain);
        DialogStateChain startChain = new StartDialogChain(statusChain);
        return new StartDialogChain(startChain);
    }
}
