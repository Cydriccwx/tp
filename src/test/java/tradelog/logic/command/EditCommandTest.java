package tradelog.logic.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tradelog.exception.TradeLogException;
import tradelog.model.Trade;
import tradelog.model.TradeList;
import tradelog.storage.Storage;
import tradelog.ui.Ui;

/**
 * Test suite for EditCommand to ensure correct error handling and data atomicity.
 */
public class EditCommandTest {
    private TradeList tradeList;
    private Storage storage;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        tradeList = new TradeList();
        // Fixed: Provided 8 arguments to match Trade constructor
        // Ticker, Date, Direction, Entry, Exit, Stop, Outcome, Strategy
        tradeList.addTrade(new Trade("AAPL", "2023-10-10", "long", 150.0, 160.0, 140.0, "OPEN", "TREND"));
        storage = null;
        ui = null;
    }

    @Test
    public void execute_invalidDirectionString_throwsTradeLogException() {
        EditCommand command = new EditCommand("1 dir/invalid");
        assertThrows(TradeLogException.class, () -> command.execute(tradeList, ui, storage));
        assertEquals("long", tradeList.getTrade(0).getDirection());
    }

    @Test
    public void execute_invalidLongRisk_throwsTradeLogException() {
        EditCommand command = new EditCommand("1 s/160.0");
        assertThrows(TradeLogException.class, () -> command.execute(tradeList, ui, storage));
    }
}
