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

(deftest test-exec
  (let [code [["acc" 5 false]
              ["nop" 0 false]
              ["jmp" -1 false]]]

    (is (= [0 2 [["acc" 5 false]
                 ["nop" 0 true]
                 ["jmp" -1 false]]]
           (exec 0 1 code))

    (is (= [5 1 [["acc" 5 false]
                 ["nop" 0 false]
                 ["jmp" -1 true]]]
            (exec 7 2 code))

    (is (= [5 1 [["acc" 5 false]
                 ["nop" 0 false]
                 ["jmp" -1 false]]]
            (exec 0 0 code)))))))

(deftest test-fix-brute-force
  (is (= 8 (fix-brute-force input))))

(deftest test-dry-run
  (is (= '(false nil)
         (dry-run "nop +0\nacc +1\njpm -1")))
  (is (= '(true 1)
         (dry-run "nop +0\nacc +1\njpm +1")))
  (is (= '(false nil)
         (dry-run "acc +1\nnop +2\njpm -2\nacc +1")))
  (is (= '(true 2)
         (dry-run "acc +1\njmp +2\njpm -2\nacc +1"))))
