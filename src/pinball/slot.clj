(ns pinball.slot)

(defrecord Slot [name probability numballs])

(defn make ([n p] (Slot. n p 0)))

(defn default-make [p] (make (str "Slot with " p "% probability") p))

(defn add-ball [aslot]
  (Slot. (:name aslot) (:probability aslot) (+ 1 (:numballs aslot))))



          
