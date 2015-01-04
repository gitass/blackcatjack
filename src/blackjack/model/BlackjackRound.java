package blackjack.model;

public class BlackjackRound {
    //private int _score;
    private Deck.DeckIterator _deckIterator;
    private Hand _dealerHand;
    private Hand _playerHand;
    // Contains true if the player has won and false if the dealer has won.
    // if the game is in progress it is null :(
    private Boolean _won;

    public BlackjackRound(Deck.DeckIterator deckIterator) {
        _deckIterator = deckIterator;

        _dealerHand = new Hand(PlayerKind.Dealer);
        _dealerHand.addCard(deckIterator.next());
        _dealerHand.addCard(deckIterator.next());

        _playerHand = new Hand(PlayerKind.Player);
        _playerHand.addCard(deckIterator.next());
        _playerHand.addCard(deckIterator.next());
        
        if (_playerHand.value() == 21) {
            _won = true;
        }
        else {
            _won = null;
        }
    }
    
    public Hand dealerHand() {
        return _dealerHand;
    }
    
    public Hand playerHand() {
        return _playerHand;
    }

    // Hit and return `_won`.
    public Boolean hit() {
        //this stops the player from commiting suicide
        if(_playerHand.value()==21){
            _won = true;
            return _won;
        }
        Card card = _deckIterator.next();
        _playerHand.addCard(card);
        if (_playerHand.isBusted()) {
            _won = false;
        }
        else if (_playerHand.value() == 21) {
            _won = true;
        }
        return _won;
    }

    // Return True if the player have won.
    // Always ends the round.
    public Boolean stand() {
        int d = _dealerHand.value();
        int p = _playerHand.value();
                
        // soft 17
        while (d < 17 || (p <= 21 && d < p && d < 21)) {
            _dealerHand.addCard(_deckIterator.next());
            d = _dealerHand.value();
            p = _playerHand.value();
        }
        
        _won = _dealerHand.isBusted() || _playerHand.value() > _dealerHand.value();
        return _won;
    }

    // Return True if the player have won.
    public Boolean isWon() {
        return _won;
    }
    
    // Return the player's score. If it's a losing round, in the negative.
    // This is later multiplied by some arbitrary numbers for reasons uncertain.
    public int score() {
        int win = isWon() ? 1 : -1;
        return win * _playerHand.value();
    }
}
