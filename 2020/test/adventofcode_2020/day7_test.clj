(ns adventofcode-2020.day7-test
  (:require [adventofcode-2020.day7 :refer :all]
            [clojure.test :refer :all]))

(def ^:private input "light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.")

(deftest test-parse-rule
  (is (= { :light-red { :bright-white 1 :muted-yellow 2 } }
         (parse-rule "light red bags contain 1 bright white bag, 2 muted yellow bags.")))

  (is (= { :dark-orange {:bright-white 3 :muted-yellow 4 } }
         (parse-rule "dark orange bags contain 3 bright white bags, 4 muted yellow bags.")))

  (is (= { :bright-white { :shiny-gold 1 } }
         (parse-rule "bright white bags contain 1 shiny gold bag.")))

  (is (= { :muted-yellow { :shiny-gold 2 :faded-blue 9 } }
         (parse-rule "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.")))

  (is (= { :shiny-gold { :dark-olive 1 :vibrant-plum 2 } }
         (parse-rule "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.")))

  (is (= { :dark-olive { :faded-blue 3 :dotted-black 4 } }
         (parse-rule "dark olive bags contain 3 faded blue bags, 4 dotted black bags.")))

  (is (= { :vibrant-plum { :faded-blue 5 :dotted-black 6 } }
         (parse-rule "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.")))

  (is (= { :faded-blue {} }
         (parse-rule "faded blue bags contain no other bags.")))

  (is (= { :dotted-black {} }
         (parse-rule "dotted black bags contain no other bags."))))

(deftest test-parse-rules
  (is (= {:light-red { :bright-white 1 :muted-yellow 2 }
          :dark-orange {:bright-white 3 :muted-yellow 4 }
          :bright-white { :shiny-gold 1 }
          :muted-yellow { :shiny-gold 2 :faded-blue 9 }
          :shiny-gold { :dark-olive 1 :vibrant-plum 2 }
          :dark-olive { :faded-blue 3 :dotted-black 4 }
          :vibrant-plum { :faded-blue 5 :dotted-black 6 }
          :faded-blue {}
          :dotted-black {}}
         (parse-rules input))))
(deftest test-can-contain-bag
  (is (= #{:bright-white :muted-yellow :light-red :dark-orange}
         (can-contain-bag (parse-rules input) :shiny-gold)))

  (is (= #{:vibrant-plum :dark-olive :shiny-gold :bright-white :muted-yellow :light-red :dark-orange}
         (can-contain-bag (parse-rules input) :dotted-black)))

  (is (empty? (can-contain-bag (parse-rules input) :light-red))))

(deftest test-must-contain-n-bags
  (is (= 0 (must-contain-n-bags (parse-rules input) :dotted-black)))
  (is (= 0 (must-contain-n-bags (parse-rules input) :faded-blue)))

  (is (= 1 (must-contain-n-bags {:foo { :bar 1 } :bar {} } :foo )))
  (is (= 2 (must-contain-n-bags {:foo { :bar 1 :quux 1 } :bar {} :quux {} } :foo)))
  (is (= 2 (must-contain-n-bags {:foo { :bar 1 } :bar { :quux 1 } :quux {} } :foo)))
  (is (= 3 (must-contain-n-bags {:foo { :bar 1 } :bar { :quux 2 } :quux {} } :foo)))
  (is (= 4 (must-contain-n-bags {:foo { :bar 2 } :bar { :quux 1 } :quux {} } :foo)))

  (is (= 7 (must-contain-n-bags (parse-rules input) :dark-olive)))
  (is (= 11 (must-contain-n-bags (parse-rules input) :vibrant-plum)))

  (is (= 32 (must-contain-n-bags (parse-rules input) :shiny-gold)))

  (is (= 126 (must-contain-n-bags (parse-rules "shiny gold bags contain 2 dark red bags.
dark red bags contain 2 dark orange bags.
dark orange bags contain 2 dark yellow bags.
dark yellow bags contain 2 dark green bags.
dark green bags contain 2 dark blue bags.
dark blue bags contain 2 dark violet bags.
dark violet bags contain no other bags.") :shiny-gold))))
