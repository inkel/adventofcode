package main

import (
	"fmt"
	"strconv"
	"testing"
)

var P1, P2 int

func BenchmarkAoC2022(b *testing.B) {
	for i := 1; i <= len(solutions); i++ {
		d := strconv.Itoa(i)
		fn := solutions[d]
		in, err := read(d)
		if err != nil {
			b.Fatalf("unexpected error benchmarking %s: %v", d, err)
		}

		b.Run(fmt.Sprintf("day%02s", d), func(b *testing.B) {
			b.ReportAllocs()
			var p1, p2 int
			for i := 0; i < b.N; i++ {
				p1, p2 = fn(in)
			}
			P1, P2 = p1, p2
		})
	}
}
