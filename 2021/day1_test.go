package main

import "testing"

func TestDay1_1(t *testing.T) {
	input := `
199
200
208
210
200
207
240
269
260
263
`
	exp := 7

	if got := day1_1(input); got != exp {
		t.Fatalf("expecting %d, got %d", exp, got)
	}
}
