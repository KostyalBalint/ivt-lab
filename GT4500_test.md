
```
  Tries to fire the torpedo stores of the ship.
  
  @param firingMode how many torpedo bays to fire
  	SINGLE: fires only one of the bays.
  			- For the first time the primary store is fired.
  			- To give some cooling time to the torpedo stores, torpedo stores are fired alternating.
  			- But if the store next in line is empty, the ship tries to fire the other store.
  			- If the fired store reports a failure, the ship does not try to fire the other one.
  	ALL:	tries to fire both of the torpedo stores.
  
  @return whether at least one torpedo was fired successfully
```

## Unit test cases for GT4500 class

### Test case 1

- Given: Both primary and secondary torpedo stores are full.
- Given: Firing mode is SINGLE
- When: Fire is called.
- Then: Primary torpedo store is fired successfully.
- Then: Secondary torpedo store is not fired.

### Test case 2

- Given: Both primary and secondary torpedo stores are full.
- Given: Firing mode is SINGLE.
- When: Primary torpedo store is fired successfully.
- When: Fire is called.
- Then: Secondary torpedo store is fired successfully.


### Test case 3

- Given: Primary torpedo store is empty.
- Given: Secondary torpedo store is full.
- Given: Firing mode is SINGLE.
- When: Fire is called.
- Then: Primary torpedo store is not fired.
- Then: Secondary torpedo store is fired successfully.

### Test case 4

- Given: Both primary and secondary torpedo stores are empty.
- Given: Firing mode is SINGLE.
- When: Fire is called.
- Then: Primary torpedo store is not fired.
- Then: Secondary torpedo store is not fired.
- Then: FireTorpedos returns false.

### Test case 5

- Given: Both primary and secondary torpedo stores are full.
- Given: Firing mode is ALL.
- When: Fire is called.
- Then: Primary torpedo store is fired successfully.
- Then: Secondary torpedo store is fired successfully.