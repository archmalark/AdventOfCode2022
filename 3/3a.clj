(require '[clojure.string :as str])



(defn calculateItemWorth [item]
  (if (> (int item) 96)
    (- (int item) 96)
    (- (int item) 38)
    )
  )



(defn searchCompartment [item rc2 searched]
 (cond 
   (empty? rc2) 0
   (= (get searched item) 1) 0
   (= item (first rc2)) (calculateItemWorth item)
   :else (searchCompartment item (rest rc2) searched)))

(defn searchRucksack [rc1 rc2 searched]
  (if (empty? rc1)
    0
    (+ (searchCompartment (first rc1) rc2 searched) (searchRucksack (rest rc1) rc2 (assoc searched (first rc1) 1)))))


(defn getCompartmentOne [rucksackString]
  (subvec (vec rucksackString) 0 (/ (count (vec rucksackString)) 2))
  )
(defn getCompartmentTwo [rucksackString]
  (subvec (vec rucksackString) (/ (count (vec rucksackString)) 2)))
  


(defn solve [input]
  (if (empty? input)
    0
    (+ (searchRucksack (getCompartmentOne (first input)) (getCompartmentTwo (first input)) {} )
       (solve (rest input)))))


;; Test input
(solve (str/split (slurp "/home/arkmal/Kod/aoc2022/3/3a-example.input") #"\n"))


;; Real input
(solve (str/split (slurp "/home/arkmal/Kod/aoc2022/3/3a.input") #"\n"))
