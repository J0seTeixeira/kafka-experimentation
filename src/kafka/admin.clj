(ns kafka.admin
  (:require [jackdaw.admin :as ja]))

(def client (ja/->AdminClient {"bootstrap.servers" "localhost:29092"}))

(defn create-topic
  "creates a topic"
  [topic-name]
  (ja/create-topics! client [{:topic-name topic-name
                              :partition-count 1
                              :replication-factor 1
                              :topic-config {"cleanup.policy" "compact"}}]))

(defn list-topics
  "lists topics"
  []
  (ja/list-topics client))