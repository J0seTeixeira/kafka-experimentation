(ns http.crimes
  (:require [clj-http.client :as client]
            [cheshire.core :refer :all]))

(declare get-all-crime-categories parse-all-crime-categories crime-of query-crimes)

(defn get-all-crime-categories
  "gets all crime categories"
  []
  (client/get "https://data.police.uk/api/crime-categories?date=2011-08"))

(defn parse-all-crime-categories
  "parses all crime categories"
  [b]
  (parse-string-strict (:body b) true))


(defn all-crimes
  "retrieves a set with all-crimes"
  []
  (into [] (parse-all-crime-categories (get-all-crime-categories))))

(defn cycle-thru-crimes
  []
  (let [all-crimes-set (all-crimes)]
    (loop [crime-rest all-crimes-set]
      (let [curr-crime-head (first crime-rest)]
        (if (empty? crime-rest)
          true
          (do (println (crime-of (:url curr-crime-head)))
              (recur (rest crime-rest))))))))

(defn query-crimes
  [crime-type-url]
  (println (format "https://data.police.uk/api/crimes-street/%s?lat=52.629729&lng=-1.131592" crime-type-url))
  (client/get
    (format "https://data.police.uk/api/crimes-street/%s?lat=52.629729&lng=-1.131592" crime-type-url)))

(defn crime-of
  [crime-type-url]
  (into [] (parse-all-crime-categories (query-crimes crime-type-url))))