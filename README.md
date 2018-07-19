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

## How this was built
* Started with a grammar like this
>> JsonObject -> key , value
>> key -> (name of the field)
>> value -> String | primitive-type | container
>> container -> arrayList | array | JsonObject
>> arrayList & array -> value ... (0 or more)

Strings and primitive types are the endpoints ( where there are no recursive calls in my algorithm)

