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
