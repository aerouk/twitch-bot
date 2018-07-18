# twitch-bot ![](https://travis-ci.com/aerouk/twitch-bot.svg?token=zf7QrH3hskS9v9YTakTV&branch=master)

Re-implementation of my twitch bot, previously running on python alongside sopel.

### Code Style

- Wrap comments and javadoc after 80 characters
- Any code that goes massively over 80 characters should be wrapped, as shown in the example below.
- If using multiple lines for parameters, insert a line break, as shown below.

```java
public void method(String param, int otherParam)
{
    if (condition) {
        doSomething();
    } else {
        otherMethod(
            param1, param2, param3, param4, param5,
            param6, param7, youGetTheGist
        );
    }
}
```
