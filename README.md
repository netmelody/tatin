[![][logo]][website] *tasty*
Tatin
=====

A stuff drawer implemented in http, tatin lets you __GET__ what you __PUT__... tat in, tat out.

```bash
$ java -jar tatin.jar 8888
tatin started on port 8888
```

```bash
$ curl localhost:8888/demo
$ echo "hello" > hello.txt
$ curl --upload-file hello.txt localhost:8888/demo
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     6    0     0    0     6      0    355 --:--:-- --:--:-- --:--:--   400
$ curl localhost:8888/demo
hello
$
```

```bash
$ curl --request PUT --header "Content-Length: 0" localhost:8888/bob
$ curl localhost:8888/demo
$
```

[logo]: https://raw.github.com/netmelody/tatin/master/tatin.png
[website]: http://netmelody.org/tatin
