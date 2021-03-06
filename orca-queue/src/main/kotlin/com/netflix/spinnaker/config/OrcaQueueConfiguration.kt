/*
 * Copyright 2017 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.config

import com.netflix.spinnaker.q.NoopQueue
import com.netflix.spinnaker.q.Queue
import java.time.Clock
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@ComponentScan(basePackages = [
  "com.netflix.spinnaker.orca.q",
  "com.netflix.spinnaker.orca.q.handler"
])
@EnableScheduling
class OrcaQueueConfiguration {
  @Bean
  @ConditionalOnMissingBean(Clock::class)
  fun systemClock(): Clock = Clock.systemDefaultZone()

  @Bean
  @ConditionalOnMissingBean(Queue::class)
  fun queue(): Queue = NoopQueue()
}
