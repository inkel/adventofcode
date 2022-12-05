package main

import "testing"

func TestDay4(t *testing.T) {
	tests := map[string]struct{ contained, overlap bool }{
		"2-4,6-8": {false, false},
		"2-3,4-5": {false, false},
		"5-7,7-9": {false, true},
		"2-8,3-7": {true, true},
		"6-6,4-6": {true, true},
		"2-6,4-8": {false, true},
		"7-8,1-2": {false, false},
	}

	for in, exp := range tests {
		c, o := day4_eval(in)
		if c != exp.contained {
			t.Errorf("pair %s contains %t, got %t", in, exp.contained, c)
		}
		if o != exp.overlap {
			t.Errorf("pair %s overlaps %t, got %t", in, exp.overlap, o)
		}
	}

	in := []string{"2-4,6-8", "2-3,4-5", "5-7,7-9", "2-8,3-7", "6-6,4-6", "2-6,4-8"}
	p1, p2 := day4(in)
	if exp := 2; p1 != exp {
		t.Errorf("part1 expecting %d, got %d", exp, p1)
	}
	if exp := 4; p2 != exp {
		t.Errorf("part1 expecting %d, got %d", exp, p2)
	}
}

func BenchmarkDay4(b *testing.B) {
	in, _ := read("4")
	var p1, p2 int
	for i := 0; i < b.N; i++ {
		p1, p2 = day4(in)
	}
	P1, P2 = p1, p2
}
