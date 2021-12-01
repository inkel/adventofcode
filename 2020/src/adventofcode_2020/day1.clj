(ns adventofcode-2020.day1
  (:gen-class))

(require '[clojure.java.io :as io]
         '[clojure.string :as str])

(defn sum-2020?
  ([a b] (= 2020 (+ a b)))
  ([a b c] (= 2020 (+ a b c))))

(defn find-pair-sum-2020 [a others]
  (first (filter (fn [x] (sum-2020? a x)) others)))

(defn find-pairs-sum-2020 [values]
  (let [a (first values)
        others (rest values)]
    (let [b (find-pair-sum-2020 a others)]
      (if b
        (list a b)
        (recur others)))))

(defn- input []
  (->> "day1.txt"
       io/resource
       slurp
       str/split-lines
       (map (fn [x] (Integer/parseInt x)))))

(defn part1 []
  (let [pair (find-pairs-sum-2020 (input))]
    (apply * pair)))

;; Part 2
(defn find-triple-sum-2020
  ([a b input]
   (loop [c (first input)
          input (rest input)]
     (if (nil? c) ;(empty? input))
       nil
       (if (= 2020 (+ a b c))
         (list a b c)
         (recur (first input) (rest input))))))

  ([a input]
   (let [b (first input)
         input (rest input)
         abc (find-triple-sum-2020 a b input)]
     (cond
       (list? abc) abc
       (not-empty input) (recur a input))))

  ([input]
   (let [abc (find-triple-sum-2020 (first input) (rest input))]
     (cond
       (list? abc) abc
       (not-empty input) (recur (rest input))))))



(defn part2 [] (apply * (find-triple-sum-2020 (input))))
