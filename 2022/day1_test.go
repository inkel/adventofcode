package main

import (
	"strings"
	"testing"
)

func TestDay1(t *testing.T) {
	in := strings.Split(`1000
2000
3000

4000

5000
6000

7000
8000
9000

10000
`, "\n")

	got, _ := day1(in)
	if exp := 24000; got != exp {
		t.Errorf("expecting %d, got %d", exp, got)
	}

	_, got = day1(in)
	if exp := 45000; got != exp {
		t.Errorf("expecting %d, got %d", exp, got)
	}
}

var P1, P2 int

func BenchmarkDay1(b *testing.B) {
	in, _ := read("1")
	var p1, p2 int
	for i := 0; i < b.N; i++ {
		p1, p2 = day1(in)
	}
	P1, P2 = p1, p2
}
