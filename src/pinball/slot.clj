(ns pinball.slot)

(defrecord Slot [id name probability numballs])

; Makes a keyword to store as id of the slot
(defn to-id [name] (keyword (clojure.string/replace name #" " "_")))

(defn make ([n p] (Slot. (to-id n) n p 0)))

(defn default-make [p] (make (str "Slot with " p "% probability") p))

; Adds a ball to slot
(defn add-ball [aslot]
  (let [name (:name aslot)]
    (Slot. (to-id name) name (:probability aslot) (+ 1 (:numballs aslot))))
  )



          
