SCJ L2--Circus 
=======

Matt Luckcuck <ml881@york.ac.uk> 2014
-----------------------------------

### Computer Science PhD Work at The University of York, UK

Converting SCJ Level 2 Programs in my model of SCJ Level2, written in [*Circus*](http://www.cs.york.ac.uk/circus); a formal notation combining Z, CSP, and a refinement calculus.

The models in this project are compatible with the draft version of the SCJ language specification verson 0.96, published on the 9th of October 2014.

Within these models the following abbreviations are often used:
* S = `Safelet`
* TLMS = Top-Level `MissionSequencer` (the flavour of mission sequencer that is started by the `Safelt`
* M = `Mission`
* PEH = `PeriodicEventHandler`
* APEH = `AperiodicEventHandler`
* OSEH = `OneShotEventHandler`
* MT = `ManagedThread`
* SMS = Schedulable `MissionSequencer` (the flvaour of mission sequencer that is started by a 		`Mission`)


When describing the structure of our SCJ examples we note the use of the following terminology:
* Cluster
  - A cluster is a `Mission` and the schedulable objects that it registers
* Tier
  - A tier is all of the clusters at the same depth in the program hierarchy
  - We define the *SafeletTier* to be the `Safelet` and the Top-Level `MissionSequencer`
  - Tier0 (which every program will have) consistes of at least one cluster, collecting the missions that may be returned by the Top-Level `MissionSequencer` and their respective schedulables
 - More deeply nested tiers will be the result of using nested mission sequencers, which are only availabl at SCJ Level 2
	

