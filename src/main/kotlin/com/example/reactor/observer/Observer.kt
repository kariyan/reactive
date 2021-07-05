package com.example.reactor.observer

import com.example.reactor.shared.logger

class Observer(val name: String) {
    fun notify(event: Event) {
        logger().info("[$name] event received : $event")
    }
}
