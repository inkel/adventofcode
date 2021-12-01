package main

import "testing"

func TestDay1_1(t *testing.T) {
	input := []int{199, 200, 208, 210, 200, 207, 240, 269, 260, 263}
	exp := 7

	if got := day1_1(input); got != exp {
		t.Fatalf("expecting %d, got %d", exp, got)
	}
}

func TestDay1_2(t *testing.T) {
	input := []int{199, 200, 208, 210, 200, 207, 240, 269, 260, 263}

	t.Run("windows", func(t *testing.T) {
		exp := []int{607, 618, 618, 617, 647, 716, 769, 792}
		got := day1_2_windows(input)

		if g, e := len(got), len(exp); g != e {
			t.Fatalf("expecting %d windows, got %d", e, g)
		}

		for i, e := range exp {
			if g := got[i]; g != e {
				t.Errorf("expecting window %d to be %d, got %d", i, e, g)
			}
		}
	})

	t.Run("solution", func(t *testing.T) {
		exp := 5
		if got := day1_2(input); got != exp {
			t.Fatalf("expecting %d, got %d", exp, got)
		}
	})
}
