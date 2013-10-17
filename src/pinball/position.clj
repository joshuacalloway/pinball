(ns pinball.position)

(defstruct position :x :y)

(defn make
  ([]
    (struct position 0 0))
  ([x y]
    (struct position x y))
  )
