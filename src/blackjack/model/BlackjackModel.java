package blackjack.model;

import java.util.ArrayList;

// A session of numerous blackjack rounds.
public class BlackjackModel {

    // SEVEN ROUNDS
    private ArrayList<BlackjackRound> _rounds;
    private Deck _deck;
    private int _totalScore;

    public BlackjackModel() {
        _rounds = new ArrayList<>();
        _deck = new Deck();
        _totalScore = 0;
    }
    
    public BlackjackRound currentRound() {
        return _rounds.get(_rounds.size() - 1);
    }
    
    public BlackjackRound newRound() {
        if (!_rounds.isEmpty()) {
            // if even round multiply by 3, else by 2.
            int mult = (_rounds.size() % 2 == 0 ? 3 : 2);
            _totalScore += mult * currentRound().score();
        }
        // shuffle deck and construct new round.
        _deck.shuffle();
        BlackjackRound round = new BlackjackRound(_deck.iterator());
        _rounds.add(round);
        return round;
    }
    
    public int totalScore() {
        return _totalScore;
    }
}
