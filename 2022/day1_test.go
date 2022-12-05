package main

import (
	"strconv"
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

func TestAtoi(t *testing.T) {
	tests := []string{"0", "1", "12", "123", "1234"}
	for _, s := range tests {
		exp, _ := strconv.Atoi(s)
		got := atoi(s)
		if exp != got {
			t.Errorf("%q expecting %d, got %d", s, exp, got)
		}
	}
}
