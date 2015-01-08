Mission+MT+OSEH Example
=======

Matt Luckcuck <ml881@york.ac.uk> 2014
-----------------------------------

### Features:
	* Mission with Multiple Schedulable Objects
	* Schedulable Object calilng `requestTermination` on its `Mission`
	* Use of `ManagedThread`
	* Use of `OneShotEventHandler`

This example program has a single `Mission` that contains a `ManagedThread` and a `OneShotEventHandler`. The `ManagedThread` runs, performs some systemActions, then terminates. The `OneShotEventHandler` waits for its start time offset, is released, and calls `requestTermination` on its mission.


