
# Mancala

Mancala is a game where players "sow" and "capture" seeds.The objective is to capture more stones than your opponent.

## Rules
Setup:

 Each player controls the six pits on their side of the board and the Mancala to their right. Place four stones or seeds in each of the 12 small pits.

Game Play:

- Players take turns.
- On a player's turn, they choose one of their pits with stones or seeds and  distribute them counterclockwise, placing one stone in each pit.
- If the last stone lands in the player's Mancala, they get another turn.
- If the last stone is dropped in an empty pit on the player's side, they capture that stone and any stones in the pit directly opposite, placing them in their Mancala. If the opposite pit is empty, no stones are captured.
- The game ends when one player's side is empty. The remaining stones on the other player's side are captured by that player.
- The winner is the player with the most stones in their Mancala.

Additional Rules:

- If a player's last stone lands in their Mancala, they get another turn.
- Players cannot capture stones from their opponent's Mancala.
- If a player has no stones in any of their pits, their opponent must place one of their stones in an empty pit on their side to keep the game going.




## Installation

option1: Use maven as build system and run below command

```bash
  mvn clean install test
  cd target
  java -jar mancala-0.0.1-SNAPSHOT.jar
```
option2: use docker-compose
```docker-compose
docker-compose up -d
```
## Api specification

```bash
http://localhost:8080/api/v2/swagger-ui/index.html
```



## API Reference

#### step1: create game setup with players

```http
  POST /api/games/setup
```
Request Body
```json
{
  "gameSetting": {
    "totalPits": 12,
    "totalStores": 2,
    "totalSeedPerPit": 4,
    "playerCount": 2,
    "totalPitPerPlayer": 6
  },
  "players": [
    {
      "userName": "test_82fcb78ba471"
    },
    {
      "userName": "test_82fcb78ba471"
    }
  ]
}
```
Response Body
```json
{
    "gameId": "5fc6d529-cfd6-4fe9-aab1-9b358c7cde6f",
    "status": "NOT_STARTED",
    "players": [
        {
            "userName": "test_82fcb78ba471",
            "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
        },
        {
            "userName": "test_82fcb78ba471",
            "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
        }
    ],
    "gameSetting": {
        "totalPits": 12,
        "totalStores": 2,
        "totalSeedPerPit": 4,
        "playerCount": 2,
        "totalPitPerPlayer": 6,
        "totalSeeds": 48
    },
    "board": {
        "activePlayer": null,
        "pits": [
            {
                "index": 0,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 1,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 2,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 3,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 4,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 5,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 6,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
                },
                "seedCount": 0,
                "store": true
            },
            {
                "index": 7,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 8,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 9,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 10,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 11,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 12,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
                },
                "seedCount": 4,
                "store": false
            },
            {
                "index": 13,
                "player": {
                    "userName": "test_82fcb78ba471",
                    "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
                },
                "seedCount": 0,
                "store": true
            }
        ]
    }
}
```
#### step2: Pick a random player to start the game

```http
  POST /api/players/pick/{gameId}
```

Response Body

```json
{
    "gameId": "5fc6d529-cfd6-4fe9-aab1-9b358c7cde6f",
    "player": {
        "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66",
        "userName": "test_82fcb78ba471"
    },
    "status": "RUNNING"
}
```

#### step3: Pick a pit index with gameId and play

```http
  PUT /api/plays/{gameId}
```
Request Body

```json
{
  "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66",
  "pitIndex": 7
}
```
Response Body

```json
{
  "nextPlayer": {
    "userName": "test_82fcb78ba471",
    "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
  },
  "champion": {
    "player": {
      "playerId": "4e36ddd1-04d4-4dac-8960-6c559b72d7d1",
      "userName": "test_6d4b1f711beb"
    },
    "totalSeed": 42
  },
  "boardOverview": {
    "activePlayer": {
      "userName": "test_82fcb78ba471",
      "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
    },
    "pits": [
      {
        "index": 0,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
        },
        "seedCount": 4,
        "store": false
      },
      {
        "index": 1,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
        },
        "seedCount": 4,
        "store": false
      },
      {
        "index": 2,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
        },
        "seedCount": 4,
        "store": false
      },
      {
        "index": 3,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
        },
        "seedCount": 4,
        "store": false
      },
      {
        "index": 4,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
        },
        "seedCount": 4,
        "store": false
      },
      {
        "index": 5,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
        },
        "seedCount": 4,
        "store": false
      },
      {
        "index": 6,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "369eca75-b55e-4f74-b6b1-a60e03e0609b"
        },
        "seedCount": 0,
        "store": true
      },
      {
        "index": 7,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
        },
        "seedCount": 0,
        "store": false
      },
      {
        "index": 8,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
        },
        "seedCount": 5,
        "store": false
      },
      {
        "index": 9,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
        },
        "seedCount": 5,
        "store": false
      },
      {
        "index": 10,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
        },
        "seedCount": 5,
        "store": false
      },
      {
        "index": 11,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
        },
        "seedCount": 5,
        "store": false
      },
      {
        "index": 12,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
        },
        "seedCount": 4,
        "store": false
      },
      {
        "index": 13,
        "player": {
          "userName": "test_82fcb78ba471",
          "playerId": "90bf0a87-3f01-44ea-98f6-009f2a6b4d66"
        },
        "seedCount": 0,
        "store": true
      }
    ]
  }
}
```

## Language and framework

- Springboot v3.2.6
- spring v6.1.1
- OpenJdk 17 or higher
- build system Maven

## design patterns and methods

- Command, Mvc
- IOC, Dependency Injection
- Rest API, AOP
- Logging
- Unit Test

## Technical Features

- Spring Open API Doc (swagger)
- 100% java pure
- cross-platform
- Custom and meaning full exception handling
- DTO level validation with AOP
- Comments and code references
- Logging on important actions
- 48 unit test on methods and controllers