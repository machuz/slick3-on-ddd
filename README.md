# slick3-on-ddd

# init 

```
$ sbt flywayMigrate
```

# test 
```
$ sbt test
```

# run
```
$ sbt run
```

# sample command

## create
$ curl -v -H "Content-type: application/json" -X POST -d '{"name":"aaaa"}'  http://localhost:9000/user/create

## update
$ curl -v -H "Content-type: application/json" -X PUT -d '{"userId": "63e5d2c0-0ca6-11e7-bb89-bea3e457679a", "name":"updated"}'  http://localhost:9000/user/update

## read
$ curl http://localhost:9000/user/list

## delete
$ curl -v -H "Content-type: application/json" -X POST -d '{"userId":"9ba8cbb0-0ca4-11e7-bb89-bea3e457679a"}'  http://localhost:9000/user/delete


ステータスコード,エラーメッセージ等細かい所は雑です。
テストも暇を見つけて追加します。


# 参考
https://github.com/j5ik2o/spetstore

https://github.com/yoskhdia/cleanArchSample
