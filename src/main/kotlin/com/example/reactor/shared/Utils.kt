package com.example.reactor.shared

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

fun <T: Any> T.logger(): Logger = getLogger(javaClass)
