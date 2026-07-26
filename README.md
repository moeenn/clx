## clx

### Commands

```bash
# run in development mode.
$ gradle run

# build for production.
$ gradle build

# run jar.
$ java -jar ./build/libs/clx-0.0.1.jar
```

### Scripts

```js
// MBTB
(() => {
	const selector = "dt a"
	const urls = [...document.querySelectorAll(selector)].map(a => a.href)
	fetch("http://localhost:5000/download", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify({ urls }),
	})
})()


// PC
(() => {
	const urls = [...document.querySelectorAll(".article__gallery-images a")].map(a => a.href)
	fetch("http://localhost:5000/download", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify({ urls }),
	})
})()
```