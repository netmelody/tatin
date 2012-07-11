[![][logo]][website] *tasty*
Tatin
=====

A light-weight in-memory HTTP state machine: *tatin* lets you __GET__ what you __PUT__... tat in, tat out.

__Installation and start-up__
tatin is distributed via maven central, so you can [download] from there.
```bash
$ wget -O tatin.jar http://search.maven.org/remotecontent?filepath=org/netmelody/tatin/tatin/0.0.2/tatin-0.0.2.jar
$ java -jar tatin.jar 8888
tatin started on port 8888
```

__Demo__
```bash
$ curl --request PUT --data "hello, world" http://localhost:8888/greet
$ curl http://localhost:8888/greet
hello, world

$ curl --request PUT --header "Content-Length: 0" http://localhost:8888/greet
$ curl localhost:8888/greet

```

[logo]: https://raw.github.com/netmelody/tatin/master/tatin.png
[website]: http://netmelody.org/tatin
[download]: http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.netmelody.tatin%22
