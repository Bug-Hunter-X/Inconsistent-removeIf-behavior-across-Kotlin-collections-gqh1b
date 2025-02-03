The inconsistency stems from how `removeIf` interacts with the iterators of each collection type.  Lists iterate sequentially, enabling in-place removal. Sets and Maps, however, don't guarantee a specific iteration order, and attempting to modify the collection during iteration can lead to unpredictable results.  To ensure consistent and safe removal across all collection types, it's recommended to iterate over a copy of the collection or to use a different approach that doesn't rely on modifying the collection while iterating:

```kotlin
fun main() {
    val list = mutableListOf(1, 2, 3, 4, 5)
    list.removeAll { it > 2 }
    println(list) // Output: [1, 2]

    val set = mutableSetOf(1, 2, 3, 4, 5)
    val setToRemove = set.filter { it > 2 }.toSet()
    set.removeAll(setToRemove)
    println(set) // Output: [1, 2]

    val map = mutableMapOf(1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five")
    val keysToRemove = map.filter { it.key > 2 }.map { it.key }.toSet()
    keysToRemove.forEach { map.remove(it) }
    println(map) // Output: {1=one, 2=two}
}
```

This solution uses `removeAll` for lists and creates a new set containing elements to remove for sets and maps, preventing concurrent modification during iteration.