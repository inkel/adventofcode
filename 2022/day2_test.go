package main

import "testing"

func TestDay2(t *testing.T) {
	t.Run("rps_hand", func(t *testing.T) {
		tests := []struct {
			a, b rps
			exp  int
		}{
			{rock, paper, rps_lost},
			{rock, scissors, rps_won},
			{rock, rock, rps_draw},

			{paper, rock, rps_won},
			{paper, scissors, rps_lost},
			{paper, paper, rps_draw},

			{scissors, rock, rps_lost},
			{scissors, paper, rps_won},
			{scissors, scissors, rps_draw},
		}

		for _, tt := range tests {
			if got := rps_hand(tt.a, tt.b); got != tt.exp {
				t.Errorf("expecting %v - %v = %d, got %d", tt.a, tt.b, tt.exp, got)
			}
		}
	})

	t.Run("rps_hand_score", func(t *testing.T) {
		tests := map[string]int{
			"A Y": 8,
			"B X": 1,
			"C Z": 6,
		}

		for in, exp := range tests {
			if got := rps_hand_score(in); exp != got {
				t.Errorf("for hand %s expecting score %d, got %d", in, exp, got)
			}
		}
	})

	t.Run("rps_strategy", func(t *testing.T) {
		tests := map[string]int{
			"A X": rps_lost + rps_value[scissors],
			"A Y": rps_draw + rps_value[rock],
			"A Z": rps_won + rps_value[paper],

			"B X": rps_lost + rps_value[rock],
			"B Y": rps_draw + rps_value[paper],
			"B Z": rps_won + rps_value[scissors],

			"C X": rps_lost + rps_value[paper],
			"C Y": rps_draw + rps_value[scissors],
			"C Z": rps_won + rps_value[rock],
		}

		for in, exp := range tests {
			if got := rps_strategy(in); got != exp {
				t.Errorf("expecting strategy %v for %s, got %v", exp, in, got)
			}
		}
	})

	in := []string{"A Y", "B X", "C Z"}
	p1, p2 := day2(in)
	if exp1, exp2 := 15, 12; p1 != exp1 || p2 != exp2 {
		t.Errorf("expecting (%d, %d), got (%d, %d)", exp1, exp2, p1, p2)
	}
}
