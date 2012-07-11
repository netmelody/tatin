[![][logo]][website] *tasty*
Tatin
=====

A light-weight in-memory HTTP state machine: *tatin* lets you __GET__ what you __PUT__... tat in, tat out.

Purpose
-------
Tatin aims to provide a trivial record-and-serve service over HTTP.  Essentially, it is a read/write web-server intended for small amounts of dynamic, volatile, information.  If you put lots of data in, it will use lots of memory.  If you bounce it, all your data will be forgotten.

Implementation
--------------
Tatin is implemented in pure Java, and is packaged as a single, standalone, executable jar -- weighing in at around 240K.  Internally, tatin is a few tens of lines of code (yes, that's all) written against the [SimpleWeb] light-weight HTTP framework.

Installation and start-up
-------------------------
tatin is distributed via maven central, so you can [download] from there.
```bash
$ wget -O tatin.jar http://search.maven.org/remotecontent?filepath=org/netmelody/tatin/tatin/0.0.2/tatin-0.0.2.jar
$ java -jar tatin.jar 8888
tatin started on port 8888
```
N.B. tatin does not daemonise by default.

Usage
-----
Once started, tatin operates as a HTTP webserver on the specified port:
```bash
$ curl --request PUT --data "hello, world" http://localhost:8888/greet
$ curl http://localhost:8888/greet
hello, world

$ curl --request PUT --header "Content-Length: 0" http://localhost:8888/greet
$ curl localhost:8888/greet

```

Headers
-------
Currently, tatin serves content with a basic set of HTTP headers.  The "Content-Type" header is currently hard-coded to `text/plain`, but a future enhancement may allow for the served content type to match that of the PUT request that created the content.

License
-------
Copyright (C) 2012 Tom Denley

Distributed under the Apache 2.0 license.

[logo]: https://raw.github.com/netmelody/tatin/master/tatin.png
[website]: http://netmelody.org/tatin
[download]: http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.netmelody.tatin%22
[SimpleWeb]: http://www.simpleframework.org
