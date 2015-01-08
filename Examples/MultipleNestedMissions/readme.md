Multiple Nested Missions Example
 =======

Matt Luckcuck 15th of December 2014
-----------------------------------

### Feature: Nested Mission Sequencer with Mulitple Missions

This example shows one nested `MissionSequencer` holding two `Mission` classes. Each `Mission` has a single `ManagedThread` which runs and terminates. Once the second `Mission`'s `ManagedThread` has terminated, the application terminates.
