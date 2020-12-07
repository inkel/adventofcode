(ns adventofcode-2020.day3-test
  (:require  [clojure.test :refer :all]
             [adventofcode-2020.day3 :refer :all]))

(def input ["..##......."
            "#...#...#.."
            ".#....#..#."
            "..#.#...#.#"
            ".#...##..#."
            "..#.##....."
            ".#.#.#....#"
            ".#........#"
            "#.##...#..."
            "#...##....#"
            ".#..#...#.#"])

(deftest test-next-pos
  (is (= '(4 2) (next-pos 1 1 10 10)))
  (is (= '(1 2) (next-pos 1 1 3 3)))
  (is (nil? (next-pos 4 2 10 2))))

(deftest test-count-trees
  (is (= 7 (count-trees input))))
