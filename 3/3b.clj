(require '[clojure.string :as str])

(defn calculateItemWorth [item]
  (if (> (int item) 96)
    (- (int item) 96)
    (- (int item) 38)))

(defn searchRucksack [rucksack found iteration]
  (cond
    (empty? rucksack) {}
    (= iteration 0) (assoc (searchRucksack (rest rucksack) found iteration) (first rucksack) 1 )
    (= (get found (first rucksack)) 1) (assoc (searchRucksack (rest rucksack) found iteration) (first rucksack) 1)
    :else (searchRucksack (rest rucksack) found iteration)
    )
  )

(defn checkRucksack [rucksack found] 
  (cond 
    (empty? rucksack) 0 
    (= (get found (first rucksack)) 1) (calculateItemWorth (first rucksack))
    :else (checkRucksack (rest rucksack) found)
   ))

(defn solve [input found iteration]
  (cond 
    (empty? input) 0
    (= iteration 2) (+ (checkRucksack (first input) found) (solve (rest input) {} 0) )
    :else (solve (rest input) (searchRucksack (first input) found iteration) (+ iteration 1) ))
  )


;; Test input
(solve (str/split (slurp "/home/arkmal/Kod/aoc2022/3/3a-example.input") #"\n") {} 0)

;; Real input
(solve (str/split (slurp "/home/arkmal/Kod/aoc2022/3/3a.input") #"\n") {} 0)