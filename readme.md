# 接口文档

[TOC]

## 端口模块


#### 接口说明 1、获取所有端口

- **请求URL**
> [/api/port/list](/api/port/list)


- **请求方式** 
>**POST**

- **请求参数**
> | 请求参数      |     参数类型 |   参数说明   |
>| :-------- | :--------| :------ |
>| pageNum|   int,不可为空|  页数|
>| pageSize|   int,不可为空|  单页数量|

- **返回**
> | 返回参数      |     参数类型 |   参数说明   |
>| :-------- | :--------| :------ |
>| code|   Integer|  执行结果code|
>|msg|varchar|执行信息|
>|data|varchar|内容|

- **返回示例**
>    
```json
{
    "code": "200",
    "msg": null,
    "data": {
        "content": [
            {
                "id": 5,
                "portNum": 8085,
                "status": 1,
                "portName": "port5",
                "portDescription": "1",
                "ip": "1",
                "new": false
            },
            {
                "id": 6,
                "portNum": 8084,
                "status": 1,
                "portName": "port8",
                "portDescription": "test",
                "ip": "1",
                "new": false
            }
        ],
        "pageable": {
            "sort": {
                "sorted": false,
                "unsorted": true
            },
            "offset": 3,
            "pageSize": 3,
            "pageNumber": 1,
            "unpaged": false,
            "paged": true
        },
        "last": true,
        "totalPages": 2,
        "totalElements": 5,
        "number": 1,
        "size": 3,
        "sort": {
            "sorted": false,
            "unsorted": true
        },
        "numberOfElements": 2,
        "first": false
    }
}
```

#### 接口说明 2、查询某个端口

- **请求URL**
> [/api/port/get](/api/port/get)


- **请求方式** 
>**POST**

- **请求参数**
> | 请求参数      |     参数类型 |   参数说明   |
>| :-------- | :--------| :------ |
>| portName|   varchar,不可为空|  端口名|
>| status|   int,不可为空|  状态|

- **请求示例**
```json
{
    "portName": "port2",
    "status":1
}
```

- **返回**
> | 返回参数      |     参数类型 |   参数说明   |
>| :-------- | :--------| :------ |
>| code|   Integer|  执行结果code|
>|msg|varchar|执行信息|
>|data|varchar|内容|

- **返回示例**
>    
```json
{
    "code": "200",
    "msg": null,
    "data": {
        "id": 2,
        "portNum": 8088,
        "status": 1,
        "portName": "port2",
        "portDescription": "1",
        "ip": "1",
        "new": false
    }
}
```

#### 接口说明 3、创建端口

- **请求URL**
> [/api/port/create](/api/port/create)


- **请求方式** 
>**POST**

- **请求参数**
> | 请求参数      |     参数类型 |   参数说明   |
>| :-------- | :--------| :------ |
>| ip|   varchar,不可为空|  ip|
>| portName|   varchar,不可为空|  端口名|
>| portNum|   int,不可为空|  端口号|
>| portDescription|   varchar,不可为空|  端口描述|
>| status|   int,不可为空|  是否可用|

- **请求示例**
```json
{
    "ip": "192.168.0.1",
    "portName" :"port8",
    "portNum":123,
    "portDescription":"test",
    "status":1
}
```

- **返回**
> | 返回参数      |     参数类型 |   参数说明   |
>| :-------- | :--------| :------ |
>| code|   Integer|  执行结果code|
>|msg|varchar|执行信息|
>|data|varchar|内容|

- **返回示例**
>    
```json
{
    "code": "200",
    "msg": null,
    "data": {
        "id": 7,
        "portNum": 123,
        "status": 1,
        "portName": "port8",
        "portDescription": "test",
        "ip": "192.168.0.1",
        "new": false
    }
}
```

#### 接口说明 4、删除端口

- **请求URL**
> [/api/port/delete](/api/port/delete)


- **请求方式** 
>**POST**

- **请求参数**
> | 请求参数      |     参数类型 |   参数说明   |
>| :-------- | :--------| :------ |
>| id|   long,不可为空|  id|

- **请求示例**
```json
{
    "id":1
}
```

- **返回**
> | 返回参数      |     参数类型 |   参数说明   |
>| :-------- | :--------| :------ |
>| code|   Integer|  执行结果code|
>|msg|varchar|执行信息|
>|data|varchar|内容|

- **返回示例**
>    
```json
{
    "code": "200",
    "msg": null,
    "data": null
}
```