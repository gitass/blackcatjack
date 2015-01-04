package blackjack.model;

import java.util.ArrayList;

public class Hand {
    private final PlayerKind _playerKind;
    private int _aces;
    private int _valueWithoutAces;
    private int _valueOfAcesOnly;
    private int _totalValue;
    private ArrayList<Card> _cards;

    public Hand(PlayerKind p, ArrayList<Card> cards) {
        _playerKind = p;
        _aces = 0;
        _valueWithoutAces = 0;
        _valueOfAcesOnly = 0;
        _totalValue = 0;
        _cards = new ArrayList<Card>();
        for (Card c : cards) this.addCard(c);
    }

    public Hand(PlayerKind p) {
        //this(p, new ArrayList<>());
        _playerKind = p;
        this._cards = new ArrayList<Card>();
    }
    
    public ArrayList<Card> visibleCards() {
        System.out.println(Card.cardsToString(_cards));
        if (_playerKind == PlayerKind.Player)
            return _cards;
        if (_playerKind == PlayerKind.Dealer)
            return new ArrayList<Card>(_cards.subList(1, _cards.size()));
        return null; // :(
    }
    
    public ArrayList<Card> allCards() {
        return _cards;
    }

    private boolean isDealer() {
        return _playerKind == PlayerKind.Dealer;
    }

    private boolean isPlayer() {
        return _playerKind == PlayerKind.Player;
    }

    /**
     * Add card and update _totalValue, etc.
     */
    public int addCard(Card c) {
        _cards.add(c);
    
        int n = c.number();
        System.out.println("number: " + n);
        if (n == 1) {
            _aces++;
            // First ace counts as 11, the rest as 1.
            if (this.isPlayer()) {
                if (_aces == 1) 
                    _valueOfAcesOnly += 11;
                else
                    _valueOfAcesOnly += 1;
            }
        }
        else if (11 <= n && n <= 13) {
            _valueWithoutAces += 10;
        }
        else {
            _valueWithoutAces += n;
        }
        
        _totalValue = _valueWithoutAces + _valueOfAcesOnly;
                

        // Adding a card to the dealer (any card, not just an ace),
        // may change the value of the aces, thus invalidating totalValue.
        // So we want to recalculate 'totalValue' in each 'addCard'.
        //
        // At most one ace will be counted as 11 because
        // if two are, 11 + 11 = 22 and the dealer busts,
        // and that would not coincide with the specs.
        if (this.isDealer()) {
            _totalValue = _valueWithoutAces + _aces;
            if (_aces > 0 && _totalValue + 10 <= 21)
                _totalValue += 10;  // Not 11 'cause we added '_aces'.
        }

        return _totalValue;
    }

    public int value() {
        return _totalValue;
    }

    public boolean isBusted() {
        return this.value() > 21;
    }
}
