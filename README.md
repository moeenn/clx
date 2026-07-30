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

### Script

```js
(() => {
	const selectors = ["a.ss-image", ".article__gallery-images a", "dt a"]
	for (const selector of selectors) {
		const urls = [...document.querySelectorAll(selector)].map(a => a.href)		
		if (!urls.length) continue
		fetch("http://localhost:5000/download", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify({urls}),
		})
	}
})()
```