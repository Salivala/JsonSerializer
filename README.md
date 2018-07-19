# JsonSerializer
Convert from POJO (Plain-Old-Java-Object) to a Json-formatted string

## Techniques used
* Recursive descent parsing
* Reflection

## Limitations
* Object should be a POJO (public constructor, with all public fields)
* Currently only supports POJO's arrays and arraylists

## Improvements that can be made
* Methods only return Strings from using StringBuilder.toString(). Ideally should return a StringBuilder object, to prevent multiple string entities in memory
