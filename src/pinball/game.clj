(ns pinball.game
  (:import (java.awt Color Dimension)
           (javax.swing JPanel JFrame Timer JOptionPane)
           (java.awt.event ActionListener KeyListener))


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
        (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)))))
 (defn game-frame [])
(defn run-game [] (game-frame))
