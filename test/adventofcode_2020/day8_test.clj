(ns adventofcode-2020.day8-test
  (:require [adventofcode-2020.day8 :refer :all]
            [clojure.test :refer :all]))

(def ^:private input "nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6")

(deftest test-parse-code
  (is (= [["nop" 0 false]
          ["acc" 1 false]
          ["jmp" 4 false]
          ["acc" 3 false]
          ["jmp" -3 false]
          ["acc" -99 false]
          ["acc" 1 false]
          ["jmp" -4 false]
          ["acc" 6 false]]
         (parse-code input))))

(deftest test-run
  (is (= 5 (run input))))
