package main

import "testing"

func TestDay3(t *testing.T) {
	tests := map[string]struct {
		r rune
		p int
	}{
		"vJrwpWtwJgWrhcsFMMfFFhFp":         {'p', 16},
		"jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL": {'L', 38},
		"PmmdzqPrVvPwwTWBwg":               {'P', 42},
		"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn":   {'v', 22},
		"ttgJtRGJQctTZtZT":                 {'t', 20},
		"CrZsJsPPZsGzwwsLwLmpwMDw":         {'s', 19},
	}

	for in, tt := range tests {
		r, p := day3_repeated(in)
		if r != tt.r {
			t.Errorf("expecting %c, got %c", tt.r, r)
		}
		if p != tt.p {
			t.Errorf("expecting %c priority %d, got %d", r, tt.p, p)
		}
	}

	tests2 := []struct {
		in []string
		b  rune
	}{
		{[]string{"vJrwpWtwJgWrhcsFMMfFFhFp", "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL", "PmmdzqPrVvPwwTWBwg"}, 'r'},
		{[]string{"wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn", "ttgJtRGJQctTZtZT", "CrZsJsPPZsGzwwsLwLmpwMDw"}, 'Z'},
	}

	for _, tt := range tests2 {
		if b := day3_badge(tt.in); b != tt.b {
			t.Errorf("expecting badge %c, got %c (%v)", tt.b, b, tt.in)
		}
	}

	in := append(tests2[0].in, tests2[1].in...)
	p1, p2 := day3(in)
	if exp := 157; p1 != exp {
		t.Errorf("expecting %d, got %d", exp, p1)
	}
	if exp := 70; p2 != exp {
		t.Errorf("expecting %d, got %d", exp, p2)
	}
}
