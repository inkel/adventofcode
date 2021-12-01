(ns adventofcode-2020.day9-test
  (:require [clojure.test :refer :all]
            [adventofcode-2020.day9 :refer :all]))

(def ^:private input [35
                      20
                      15
                      25
                      47
                      40
                      62
                      55
                      65
                      95
                      102
                      117
                      150
                      182
                      127
                      219
                      299
                      277
                      309
                      576])

(def ^:private preamble-size 5)

(deftest test-find-invalid
  (is (= 127 (find-invalid input preamble-size))))
