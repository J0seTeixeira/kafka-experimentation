(ns kafka.producer
    (:require
      [jackdaw.client :as jc]))

(def producer-config
  {"bootstrap.servers" "localhost:29092"
   "key.serializer" "org.apache.kafka.common.serialization.StringSerializer"
   "value.serializer" "org.apache.kafka.common.serialization.StringSerializer"
   "acks" "all"
   "client.id" "foo"})


(defn produce-message
  "produces a message to a topic"
  [topic-name]
  (with-open [my-producer (jc/producer producer-config)]
    @(jc/produce! my-producer {:topic-name topic-name} "1" "hi mom!")))

