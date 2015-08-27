# GridWorld2015
SYSU junior training

the organization of the project

#stage1 - project beginning
    HelloWorld - first java program, learn about ant, junit.
    Calculator - a simple java program, learn about ant, sonarQube
#stage2 - basic tasks
##Part2 - gets familiar to the GridWorld program
    CircleBug - extands the Bug class, making a bug trace an octagon.
    DancingBug - extands the Bug class, making a bug trace a certain trace the programmer has set.
    SpiralBug - extands the Bug class, making a bug drop flowers in a spiral pattern
    ZBug - extands the Bug class, making a bug trace the shape for only once.
##Part3
    Jumper - group activity. a jumper can move forward two cells in each move. It "jumps" over rocks and flowers
##Part4
    ModifiedChameleonCritter - extands the Critter class. darkens itself  if no neighbors actor.
    ChameleonKid - extands ModifiedCahmeleonCritter. a chameleon kid only modifies the actor in front of or at the back of itself.
    RockHound - extands the Critter class. removes rocks in its neighbors.
    BlusterCritter - extands the Critter class. a blust critter has a courage. if the number of critters in its neighbors is less than ` its courage, it brightens itself. else it darkens itself.
    QuickCrab - extands the CrabCritter class. moves 2 steps at an act if possible.
    KingCrab - extands the CrabCritter class. if any actor is in front of a king crab, the crab will push them out.
##Part5
    SparseGrid - extands the Grid class. contains 3 tasks.
      implements the SparseBoundedGrid class using a sparse array.
      implesments the SparseBoundedGrid2 class using a hashmap.
      implements the UnboundedGrid2 class using a 2-dimension array.
#stage3
  ImageProcessing - implements the IImageIO interface and the IImageProcessor by oneself.
  MazeBug - uses DFS algorithm  to implements a maze bug. framework is provided.
  N-puzzle - uses BFS algorithm to solve an N-puzzle problem. framework of Jigsaw is provided.
