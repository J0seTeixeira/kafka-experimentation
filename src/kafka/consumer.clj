(ns kafka.consumer
    (:require
      [jackdaw.client :as jc]
      [jackdaw.client.log :as jl])
    (:import
      (org.apache.kafka.common.serialization Serdes)))

(def consumer-config
  {"bootstrap.servers" "localhost:29092"
   "group.id"  "com.foo.my-consumer"
   "key.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"
   "value.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"})


(defn consume-topic
  "consumes from a topic"
  [topic-name]
  (with-open [my-consumer (-> (jc/consumer consumer-config)
                              (jc/subscribe [{:topic-name topic-name}]))]
    (doseq [{:keys [key value partition timestamp offset]} (jl/log my-consumer 500)]
      (println "key: " key)
      (println "value: " value)
      (println "partition: " partition)
      (println "timestamp: " timestamp)
      (println "offset: " offset))))
