(defproject kafka "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [fundingcircle/jackdaw "0.6.4"]
                 [org.apache.kafka/kafka-streams "2.1.0"]
                 [org.apache.kafka/kafka-streams-test-utils "2.1.0"]
                 [org.clojure/tools.logging "0.4.1"]
                 [clj-http "3.12.3"]
                 [cheshire "5.10.0"]]
  :main ^:skip-aot kafka.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
