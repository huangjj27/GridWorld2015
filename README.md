# GridWorld2015
* YSU junior training


## the organization of the project
* tage1 - project beginning
	* elloWorld - first java program, learn about ant, junit.
	* alculator - a simple java program, learn about ant, sonarQube
* tage2 - basic tasks
	* art2 - gets familiar to the GridWorld program
		* ircleBug - extands the Bug class, making a bug trace an octagon.
		* ancingBug - extands the Bug class, making a bug trace a certain trace the programmer has set.
		* piralBug - extands the Bug class, making a bug drop flowers in a spiral pattern
		* Bug - extands the Bug class, making a bug trace the shape for only once.
	* art3
		* umper - group activity. a jumper can move forward two cells in each move. It "jumps" over rocks and flowers
	* art4
		* odifiedChameleonCritter - extands the Critter class. darkens itself	if no neighbors actor.
		* hameleonKid - extands ModifiedCahmeleonCritter. a chameleon kid only modifies the actor in front of or at the back of itself.
		* ockHound - extands the Critter class. removes rocks in its neighbors.
		* lusterCritter - extands the Critter class. a blust critter has a courage. if the number of critters in its neighbors is less than ` its courage, it brightens itself. else it darkens itself.
		* uickCrab - extands the CrabCritter class. moves 2 steps at an act if possible.
		* ingCrab - extands the CrabCritter class. if any actor is in front of a king crab, the crab will push them out.
	* art5
		* parseGrid - extands the Grid class. contains 3 tasks.
			* mplements the SparseBoundedGrid class using a sparse array.
			* mplesments the SparseBoundedGrid2 class using a hashmap.
			* mplements the UnboundedGrid2 class using a 2-dimension array.
* tage3
	* mageProcessing - implements the IImageIO interface and the IImageProcessor by oneself.
	* azeBug - uses DFS algorithm	to implements a maze bug. framework is provided.
	* -puzzle - uses BFS algorithm to solve an N-puzzle problem. framework of Jigsaw is provided.
