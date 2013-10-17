(ns pinball.game
  (:import (java.awt Color Dimension)
           (javax.swing JPanel JFrame JOptionPane)
           (java.awt.event ActionListener KeyListener))
  )

(defn game-panel [frame]
  (proxy [JPanel ActionListener KeyListener] []
    (getPreferredSize []
      (Dimension. 1000 1000)
      )
    
    )
  )
  


(defn game-frame []
  (let [
        frame (JFrame. "Pinball")
        panel (game-panel frame)
        ]
    (doto panel
      (.setFocusable true))
    (doto frame
      (.add panel)
      (.pack)
      (.setVisible true)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE))))

(defn run-game [] (game-frame))
