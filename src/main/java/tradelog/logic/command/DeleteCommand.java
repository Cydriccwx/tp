package tradelog.logic.command;

import tradelog.exception.TradeLogException;
import tradelog.model.Trade;
import tradelog.model.TradeList;
import tradelog.storage.Storage;
import tradelog.ui.Ui;

/**
 * Represents a command to delete an existing trade from the TradeLog.
 * Handles parsing, strict validation of the trade index, and executing the deletion.
 */
public class DeleteCommand extends Command {
    private final int targetIndex;

    /**
     * Represents a command to delete an existing trade from the TradeLog.
     * Execution assumes the provided index has already been parsed and validated.
     */
    /**
     * * Represents a command to delete an existing trade from the TradeLog.
     * * Execution assumes the provided index has already been parsed and validated.
     *
     */
    /**
     * Constructs a DeleteCommand with a parsed target index.
     *
     * @param targetIndex The zero-based index of the trade to delete.
     */
    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the delete command by removing the trade and displaying the UI message.
     *
     * @param tradeList The current list of trades.
     * @param ui        The UI handler for output.
     * @param storage   The storage handler for persistence.
     * @throws TradeLogException If the index provided does not exist in the list.
     */
    @Override
    public void execute(TradeList tradeList, Ui ui, Storage storage) {
        try {
            Trade deletedTrade = tradeList.deleteTrade(targetIndex);

            ui.showLine();
            System.out.println("Trade successfully deleted.");
            System.out.println(deletedTrade.toSummaryString());
            ui.showLine();

        } catch (IndexOutOfBoundsException e) {
            throw new TradeLogException("Error: Trade index does not exist!");
        }
    }
}
