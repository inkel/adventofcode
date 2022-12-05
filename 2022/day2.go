package main

import "fmt"

func day2(in []string) (int, int) {
	var total1, total2 int

	for _, l := range in {
		total1 += rps_hand_score(l)
		total2 += rps_strategy(l)
	}

	return total1, total2
}

type rps rune

func (r rps) String() string {
	switch r {
	case rock:
		return "Rock"
	case paper:
		return "Paper"
	case scissors:
		return "Scissors"
	}
	panic("unknown")
}

const (
	rock     rps = 'R'
	paper    rps = 'P'
	scissors rps = 'S'
)

const (
	rps_won  = 6
	rps_lost = 0
	rps_draw = 3
)

func rps_hand(a, b rps) int {
	if a == b {
		return rps_draw
	}

	switch a {
	case rock:
		if b == paper {
			return rps_lost
		}
		return rps_won
	case paper:
		if b == rock {
			return rps_won
		}
		return rps_lost
	case scissors:
		if b == paper {
			return rps_won
		}
		return rps_lost
	}

	panic(fmt.Sprintf("shouldn't happen: %v %v", a, b))
}

var rps_code = map[rune]rps{
	'A': rock,
	'B': paper,
	'C': scissors,

	'X': rock,
	'Y': paper,
	'Z': scissors,
}

var rps_value = map[rps]int{
	rock:     1,
	paper:    2,
	scissors: 3,
}

func rps_hand_score(in string) int {
	b, a := rps_code[rune(in[0])], rps_code[rune(in[2])]
	return rps_hand(a, b) + rps_value[a]
}

var rps_strategy_map = map[rps]map[rune]rps{
	rock: map[rune]rps{
		'X': scissors,
		'Y': rock,
		'Z': paper,
	},
	paper: map[rune]rps{
		'X': rock,
		'Y': paper,
		'Z': scissors,
	},
	scissors: map[rune]rps{
		'X': paper,
		'Y': scissors,
		'Z': rock,
	},
}

func rps_strategy(in string) int {
	a, s := rps_code[rune(in[0])], rune(in[2])
	b := rps_strategy_map[a][s]
	return rps_hand(b, a) + rps_value[b]
}
