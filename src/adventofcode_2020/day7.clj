(ns adventofcode-2020.day7)

(require '[clojure.string :as s]
         '[clojure.java.io :as io])

(defn parse-rule [rule]
  (let [rule (-> rule (s/replace #"([,.]| bags?( contain)?| no other)" "") (s/split #" "))
        make-bag (fn [[x y]] (keyword (s/join [x "-" y])))
        outer (make-bag (take 2 rule))
        inner (drop 2 rule)]
    (loop [acc {}
           inner inner]
      (if (empty? inner)
        { outer acc }
        (let [[n & color] (take 3 inner)]
          (recur (conj acc [(make-bag color) (Integer/parseInt n)])
                 (drop 3 inner)))))))

(defn parse-rules [input]
  (->> input
       (s/split-lines)
       (map parse-rule)
       (reduce conj)))

(defn can-contain-bag [rules bag]
  (letfn [(contains-bag-fn [bag]
            (fn [acc [outer inner]]
              (if (contains? inner bag)
                (conj acc outer)
                acc)))
          (contains-bag [bag]
            (reduce (contains-bag-fn bag) [] rules))
          (append-bags [bags more] bags
            (if (empty? more)
              bags
              (apply conj bags more)))]
    (let [bags (contains-bag bag)]
      (loop [acc #{}
             bag (first bags)
             bags (rest bags)]
        (if (empty? bags)
          acc
          (recur (conj acc bag)
                 (first bags)
                 (append-bags (rest bags) (contains-bag bag))))))))

(def ^:private input
  (->> "day7.txt"
       io/resource
       slurp))

(defn part1 []
  (count (can-contain-bag (parse-rules input) :shiny-gold)))
