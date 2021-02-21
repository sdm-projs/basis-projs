#ifndef CARD_H
#define CARD_H

#include <iostream>
using std::ostream;

// Enum type that represents the card suits
enum suit {clubs, hearts, spades, diamonds};

class Card
{
public:
    //default constructor - required
	Card();

    //constructor that takes a card's face (represented an integer) and suit
	// card face example: Ace=0, 2=1, 3=2, ... Q=11, K=12 or some other ordering
	Card (int face, suit st);

    // overload the << operator to display the card
    friend ostream& operator << (ostream& os, const Card& cd);

    // compare and return true if *this has a lesser point value than cd, false otherwise
	bool operator < (const Card& cd) const;

    // compare and return true if *this has a larger point value than cd, false otherwise
	bool operator > (const Card& cd) const;

    // compare and return true if *this has the same point value as cd, false otherwise
	bool operator== (const Card& cd) const;

    // return the point value of the card: Ace: 15, Faces: 10, Numbers: the number
	int getPointValue() const;

private:
	suit	cardSuit;		// card's suit
	int     cardFace;		// card's face
	int     pointValue;		// card's point value (derived from face)
};
#endif