(ns au.com.directcommunications.trello-job.trello
  (:require [ring.util.response :as resp]
            [trellolib.core :as trello]))

(defn sub-str
  "Takes takes the first n chars from a string and makes a new string
  from them."
  [string n]
  (reduce str "" (take n string)))

(defn join-model-serial
  "Joins the groups of serial numbers and models"
  [models serials]
  (reduce str (map #(str %1 " Sn: " %2 "\n") models serials)))

(defn get-name
  "Generates the name for the Trello card"
  [jobno name to-do]
  (str jobno
       " - " name " - "
       (if (< 30 (count to-do))
         (str
          (sub-str to-do 30)
          "...")
         to-do)))

(defn get-desc
  "Generates the description for the Trello card"
  [items po to-do]
  (str items
       "\n"
       (if-not (= "" po)
         (str "\nPurchase Order: "
              po
              "\n\n"))
       to-do))

(def hard-client {})

(defn handle-post-card
  "This function is called when the user makes a POST request on
  /job"
  [params]
  (let [to-do (params "to-do")
        po (params "customer-po")
        name (params "customer-name")
        models (params "model[]")
        serials (params "serialno[]")
        jobno (params "jobno")
        items (if (instance? clojure.lang.PersistentVector models)
                (join-model-serial models serials)
                (str models " Sn: " serials "\n"))
        card {:name (get-name jobno name to-do)
              :desc (get-desc items po to-do)
              :idList "53c8c8207dfb7da6d039cb95"}]
    (trello/post-card card hard-client))
  (resp/redirect "/job"))
