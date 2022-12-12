(require '[clojure.string :as str])


(defn solve2 [input searchMap counter length]

  (cond
    (= (count searchMap) length) counter
    (empty? input) nil
    (get searchMap (first input)) nil
    :else (recur (rest input) (assoc searchMap (first input) 1) (+ counter 1) length)))

(defn solve [input  counter length answer]
  (cond
    (empty? input) nil
    (number? answer) answer
    :else (recur (rest input) (+ counter 1) length (solve2 input {} counter length))))

;; Test input
(solve  (first (str/split (slurp "/home/arkmal/Kod/aoc2022/6/6a-example.input") #"\n")) 0 14 nil)

;; Real input
(solve  (first (str/split (slurp "/home/arkmal/Kod/aoc2022/6/6a.input") #"\n")) 0 4 nil)

