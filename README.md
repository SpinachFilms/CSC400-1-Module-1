# CSC400-1

# Pseudocode 
ALGORITHM Bag<T> (multiset implemented with a frequency map)

INTERNAL STATE:
  - freqMap : HashMap<T, Integer>   // maps item -> occurrence count
  - totalSize : Integer             // total number of items including duplicates

METHOD add(item: T) -> void
  IF item is null THEN
     RAISE IllegalArgumentException("Null items are not allowed")
  ENDIF
  current ← freqMap.getOrDefault(item, 0)
  freqMap.put(item, current + 1)
  totalSize ← totalSize + 1
END

METHOD remove(item: T) -> void
  current ← freqMap.get(item)     // may be null
  IF current == null THEN
     RETURN                       // item not present; nothing to remove
  ENDIF
  IF current > 1 THEN
     freqMap.put(item, current - 1)
  ELSE
     freqMap.remove(item)
  ENDIF
  totalSize ← totalSize - 1
END

METHOD contains(item: T) -> boolean
  RETURN freqMap.containsKey(item)
END

METHOD count(item: T) -> integer
  RETURN freqMap.getOrDefault(item, 0)
END

METHOD size() -> integer
  RETURN totalSize
END

METHOD isEmpty() -> boolean
  RETURN (totalSize == 0)
END

METHOD toString() -> String
  // Build a string like: { apple x 3, banana x 2, orange x 1 }
  IF freqMap is empty THEN
     RETURN "{ }"
  ENDIF
  result ← "{ "
  first ← true
  FOR EACH (key, value) IN freqMap
     IF NOT first THEN
        result ← result + ", "
     ENDIF
     result ← result + key + " x " + value
     first ← false
  ENDFOR
  result ← result + " }"
  RETURN result
END


// ================= DEMONSTRATION PROGRAM =================

PROGRAM Main
  DECLARE bag : Bag<String>
  bag ← new Bag<String>()

  // Add elements (with duplicates)
  bag.add("apple")
  bag.add("banana")
  bag.add("apple")
  bag.add("orange")
  bag.add("banana")
  bag.add("apple")

  PRINT "Initial bag: " + bag.toString()
  PRINT "Total size: " + bag.size()

  // Test contains
  PRINT "Contains 'apple'? " + bag.contains("apple")
  PRINT "Contains 'grape'? " + bag.contains("grape")

  // Test count
  PRINT "Count('apple') = " + bag.count("apple")
  PRINT "Count('banana') = " + bag.count("banana")
  PRINT "Count('grape')  = " + bag.count("grape")

  // Remove one occurrence
  bag.remove("banana")

  // Print again and retest
  PRINT "After removing one 'banana': " + bag.toString()
  PRINT "Total size: " + bag.size()

  PRINT "Contains 'banana'? " + bag.contains("banana")
  PRINT "Count('banana') = " + bag.count("banana")
END
