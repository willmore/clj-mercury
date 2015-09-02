(ns mercury.acceptance.roundtrip
  (:require [midje.sweet :refer :all]
            [mercury.mq :as mq]
            [mercury.queue :as queue]))

(mq/start-mq!)

(fact "mercury can exchange a message over a queue"

      (fact "can send a single message"
            (let [q (mq/create-queue! "my-queue")]
              (queue/send! q "Hello!")
              (queue/receive! q) => "Hello!"))

      (fact "can send two messages"
            (let [q (mq/create-queue! "my-queue")]
              (queue/send! q "Hello!")
              (queue/receive! q) => "Hello!"
              (queue/send! q "Tere!"
              (queue/receive! q) => "Tere!"))))
