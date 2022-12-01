(require '[clojure.string :as str])

(defn parseElement
  [element]
  (if (clojure.string/blank? element) 0 (read-string element)))


(defn insertIfHigher [list item]
  (cond 
    (empty? list) list
    (< (first list) item) (conj (insertIfHigher (rest list) (first list)) item)
    :else (conj  (insertIfHigher (rest list) item) (first list))
    ))


(defn sumList [list]
  (if (empty? list)
    0
    (+ (first list) (sumList (rest list)))))


(defn solve
  [input highest acc]
  
  (cond 
    (empty? input)  (sumList (insertIfHigher highest acc))
    (= (first input) 0) (solve (rest input) (insertIfHigher highest acc) 0) 
    
    :else (solve (rest input) highest (+ acc (first input))) 
    ))



;; Test input
(solve
 (map parseElement  (str/split (slurp "/home/arkmal/Kod/aoc2022/1/1a-example.input") #"\n"))
 '(0 0 0)
 0)

;; Real input
(solve
 (map parseElement  (str/split (slurp "/home/arkmal/Kod/aoc2022/1/1a.input") #"\n"))
 '(0 0 0)
 0)

