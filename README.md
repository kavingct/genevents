Gen Events
======
*A simple event management library*

Gen Events helps you to create listeners to custom events and trigger them when needed. You can attach as many listeners to an event and get them all notifed.

#### Usage:
###### Listening to an event

Listening to event is as simple as just annotating a method with `@Listener` and registering the object.

```java
class ListenerTest {
  @Listener(type="custom-event")
  public void execute(Event event) {
     //Do something here  
  }
}
```

You need to register an object of `ListenerTest` class to `EventMapper`.

```java
ListenerTest listener = new ListenerTest();
EventMapper.register(listener);
```

To create an event trigger, just instantiate a new `Event` class and call the `trigger()` method in it.
```java
new Event("custom-event").trigger();
```

All the listening methods will be notified about the event.

###### Passing custom values in event
You can attach a map of custom values to the event and get it in the listener.
```java
Map<String,Object> params = new LinkedHashMap<String,Object>();
params.put("user_id",10);
params.put("user_name","Test User");
new Event("custom-event", params).trigger();
```

And the listener method part will be as below.
```java
class ListenerTest {
  @Listener(type="custom-event")
  public void execute(Event event) {
     Map<String,Object> params = event.getExtras();
     //Do something with it  
  }
}
```

Note
====
* If you find any bug or need any new feature, please open an issue in Github. I will try my best to reply back as soon as possible.
* If you wish to contribute to the code, fork the repo, commit any changes and send pull request.

License
=======
The MIT License (MIT)

Copyright (c) 2013 Genzis

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
